package ru.netology.linkedout.repository

import okhttp3.MultipartBody
import ru.netology.linkedout.apiservice.ApiService
import ru.netology.linkedout.dao.EventDao
import ru.netology.linkedout.dto.AttachmentType
import ru.netology.linkedout.dto.EventRequest
import ru.netology.linkedout.dto.MediaResponse
import ru.netology.linkedout.entity.EventEntity
import ru.netology.linkedout.error.ApiError
import ru.netology.linkedout.error.NetworkError
import java.io.IOException
import javax.inject.Inject

interface NewEventRepository {
    suspend fun addPictureToTheEvent(
        attachmentType: AttachmentType,
        image: MultipartBody.Part
    ): MediaResponse

    suspend fun addEvent(event: EventRequest)
    suspend fun getEvent(id: Int): EventRequest
}


class NewEventRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: EventDao
) : NewEventRepository {

    override suspend fun addPictureToTheEvent(
        attachmentType: AttachmentType,
        image: MultipartBody.Part
    ): MediaResponse {
        try {
            val response = apiService.uploadMedia(image)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw NetworkError
        }
    }

    override suspend fun addEvent(event: EventRequest) {
        try {
            val response = apiService.addEvent(event)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            } else {
                val body = response.body() ?: throw ApiError(response.code(), response.message())
                dao.insert(EventEntity.fromDto(body))
            }
        } catch (e: IOException) {
            throw NetworkError
        }
    }

    override suspend fun getEvent(id: Int): EventRequest {
        try {
            val response = apiService.getEvent(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            } else {
                val body = response.body() ?: throw ApiError(response.code(), response.message())
                return EventRequest(
                    id = body.id,
                    content = body.content,
                    datetime = body.datetime,
                    coords = body.coords,
                    type = body.type,
                    attachment = body.attachment,
                    link = body.link,
                    speakerIds = body.speakerIds
                )
            }
        } catch (e: IOException) {
            throw NetworkError
        }
    }
}