package ru.netology.linkedout.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ru.netology.linkedout.apiservice.ApiService
import ru.netology.linkedout.dao.PostDao
import ru.netology.linkedout.dao.PostRemoteKeyDao
import ru.netology.linkedout.db.AppDb
import ru.netology.linkedout.entity.PostEntity
import ru.netology.linkedout.entity.PostRemoteKeyEntity
import ru.netology.linkedout.entity.toEntity
import ru.netology.linkedout.error.ApiError
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val postDao : PostDao,
    private val postRemoteKeyDao : PostRemoteKeyDao,
    private val db : AppDb
) : RemoteMediator<Int, PostEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        try {
            val pageSize = state.config.pageSize
            val response = when (loadType) {

                LoadType.PREPEND -> {
                    val id = postRemoteKeyDao.max() ?: return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
                    apiService.getAfterPosts(id, pageSize)
                }

                LoadType.APPEND -> {
                    val id = postRemoteKeyDao.min() ?: return MediatorResult.Success(
                        endOfPaginationReached = false
                    )

                    apiService.getBeforePosts(id, pageSize)
                }


                LoadType.REFRESH -> apiService.getLatestPosts(pageSize)

            }

            if (!response.isSuccessful) {
                throw  ApiError(response.code(), response.message())
            }

            val body = response.body() ?: throw ApiError(
                response.code(),
                response.message())

            db.withTransaction {
                when (loadType) {
                    LoadType.REFRESH -> {
                        postRemoteKeyDao.removeAllPosts()
                        postRemoteKeyDao.insert(
                            listOf(
                                PostRemoteKeyEntity(
                                    PostRemoteKeyEntity.KeyType.AFTER,
                                    body.first().id
                                ),
                                PostRemoteKeyEntity(
                                    PostRemoteKeyEntity.KeyType.BEFORE,
                                    body.last().id
                                )
                            )
                        )

                        postDao.removeAllPosts()
                    }
                    LoadType.PREPEND -> {
                        postRemoteKeyDao.insert(
                            PostRemoteKeyEntity(
                                PostRemoteKeyEntity.KeyType.AFTER,
                                body.first().id
                            )
                        )
                    }
                    LoadType.APPEND -> {
                        postRemoteKeyDao.insert(
                            PostRemoteKeyEntity(
                                PostRemoteKeyEntity.KeyType.BEFORE,
                                body.last().id
                            )
                        )
                    }
                }

                postDao.insert(body.toEntity())
            }

            return MediatorResult.Success(endOfPaginationReached = body.isEmpty())



        } catch (e : Exception) {
            return MediatorResult.Error(e)
        }
    }
}