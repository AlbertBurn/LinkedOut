package ru.netology.linkedout.repository

interface AuthRepository {
    suspend fun signIn(
        login: String,
        pass: String,
    ): AuthState

    suspend fun registerNewUser(
        login: String,
        pass: String,
        name: String,
    ): AuthState

    suspend fun registerWithPhoto(
        login: String,
        pass: String,
        name: String,
        media: MediaUpload,
    ): AuthState

}