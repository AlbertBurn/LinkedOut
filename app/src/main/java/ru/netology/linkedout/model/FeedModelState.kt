package ru.netology.linkedout.model

data class FeedModelState(
    val loading: Boolean = false,
    val refreshing: Boolean = true,
    val error: Boolean = false,
)
