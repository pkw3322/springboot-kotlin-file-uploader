package com.example.fileuploader.domain

import com.github.f4b6a3.ulid.UlidCreator
import org.hibernate.annotations.CreationTimestamp
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "content_image")
data class ContentImage(
    @CreationTimestamp
    @Column(name="created_time", nullable = false, updatable = false)
    val createdTime: LocalDateTime,

    @CreationTimestamp
    @Column(name="last_edited_time", nullable = false)
    val lastEditedTime: LocalDateTime,

    @Column(name="title", nullable = false)
    var saveFileName: String,

    @Column(name="body", nullable = false)
    var originalFileName: String,

    @Id
    @Column(name="id", updatable = false, nullable=false)
    var id: UUID

): RepresentationModel<ContentImage>()
{
    constructor(saveFileName: String,originalFileName: String):this(
        createdTime = LocalDateTime.now(),
        lastEditedTime = LocalDateTime.now(),
        saveFileName = saveFileName,
        originalFileName = originalFileName,
        id = UlidCreator.getUlid().toRfc4122().toUuid()
    )
}