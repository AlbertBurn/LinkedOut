package ru.netology.linkedout.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.netology.linkedout.apiservice.ApiService
import ru.netology.linkedout.dao.UserDao
import ru.netology.linkedout.dto.User
import ru.netology.linkedout.entity.UserEntity
import ru.netology.linkedout.entity.toDto
import ru.netology.linkedout.entity.toEntity
import ru.netology.linkedout.error.ApiError
import ru.netology.linkedout.error.NetworkError
import java.io.IOException
import javax.inject.Inject

val emptyUser = User()

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) : UserRepository {
    override val data: MutableLiveData<List<User>> =
        userDao.getAllUsers().map(List<UserEntity>::toDto)  as MutableLiveData<List<User>>
    override val userData: MutableLiveData<User>
            = MutableLiveData(emptyUser)

    override suspend fun getAllUsers() {
        try {
            val response = apiService.getUsers()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            data.postValue(body)
            userDao.insert(body.toEntity())
        } catch (e: IOException) {
            throw NetworkError
        }
    }

    override suspend fun getUserById(id: Int) {
        try {
            val response = apiService.getUserById(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            userData.postValue(body)
        } catch (e: IOException) {
            throw NetworkError
        }
    }

}