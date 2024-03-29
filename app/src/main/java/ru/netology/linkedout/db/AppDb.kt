package ru.netology.linkedout.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.netology.linkedout.dao.*
import ru.netology.linkedout.entity.*

@Database(entities = [PostEntity::class, PostRemoteKeyEntity::class, UserEntity::class, JobEntity::class, EventEntity::class, EventRemoteKeyEntity::class], version = 7, exportSchema = false)
@TypeConverters(InstantConverter::class, ListConverter::class, UserMapConverter::class, CoordinatesConverter::class)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao() : PostDao
    abstract fun postRemoteKeyDao() : PostRemoteKeyDao
    abstract fun userDao() : UserDao
    abstract fun jobDao() : JobDao

    abstract fun eventDao() : EventDao

    abstract fun eventRemoteKeyDao() : EventRemoteKeyDao
}