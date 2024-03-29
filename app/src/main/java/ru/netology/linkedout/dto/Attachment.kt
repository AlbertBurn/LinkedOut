package ru.netology.linkedout.dto

data class Attachment(
    val url: String,
    val type: AttachmentType
)

enum class AttachmentType {
    IMAGE,
    VIDEO,
    AUDIO
}