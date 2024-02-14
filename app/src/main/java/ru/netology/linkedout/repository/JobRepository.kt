package ru.netology.linkedout.repository


import kotlinx.coroutines.flow.Flow
import ru.netology.linkedout.dto.Job

interface JobRepository {
    val data: Flow<List<Job>>
    suspend fun getUserJobs(id: Int)
    suspend fun saveJob(job: Job)
    suspend fun removeJobById(id: Int)
    suspend fun getMyJobs()
}