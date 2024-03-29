package ru.netology.linkedout.entity


import androidx.room.*
import ru.netology.linkedout.dao.InstantConverter
import ru.netology.linkedout.dao.ListConverter
import ru.netology.linkedout.dao.UserMapConverter
import ru.netology.linkedout.dto.Attachment
import ru.netology.linkedout.dto.AttachmentType
import ru.netology.linkedout.dto.Post
import ru.netology.linkedout.dto.UserPreview
import java.time.Instant

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val authorId: Int,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    val content: String,
    @TypeConverters(InstantConverter::class)
    val published: Instant,
    val link: String?,
    @TypeConverters(ListConverter::class)
    val likeOwnerIds: List<Int>,
    @TypeConverters(ListConverter::class)
    val mentionIds: List<Int>,
    val mentionedMe: Boolean,
    val likedByMe: Boolean,
    @Embedded
    val attachment: AttachmentEmbedded?,
    val ownedByMe: Boolean,
    @TypeConverters(UserMapConverter::class)
    val users: Map<Int, UserPreview>,
) {
    fun toDto() = Post(
        id,
        authorId,
        author,
        authorAvatar,
        authorJob,
        content,
        published,
        link,
        likeOwnerIds,
        mentionIds,
        mentionedMe,
        likedByMe,
        attachment?.toDto(),
        ownedByMe,
        users
    )

    companion object {
        fun fromDto(dto: Post) = PostEntity(
            dto.id,
            dto.authorId,
            dto.author,
            dto.authorAvatar,
            dto.authorJob,
            dto.content,
            dto.published,
            dto.link,
            dto.likeOwnerIds,
            dto.mentionIds,
            dto.mentionedMe,
            dto.likedByMe,
            AttachmentEmbedded.fromDto(dto.attachment),
            dto.ownedByMe,
            dto.users
        )
    }
}

fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity.Companion::fromDto)

data class AttachmentEmbedded(
    var url: String,
    var typeAttach: AttachmentType,
) {
    fun toDto() = Attachment(url, typeAttach)

    companion object {
        fun fromDto(dto: Attachment?) = dto?.let {
            AttachmentEmbedded(it.url, it.type)
        }
    }
}