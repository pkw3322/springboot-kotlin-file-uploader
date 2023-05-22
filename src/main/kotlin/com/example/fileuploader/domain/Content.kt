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
@Table(name = "content")
data class Content(

    @CreationTimestamp
    @Column(name="created_time", nullable = false, updatable = false)
    val createdTime: LocalDateTime,

    @CreationTimestamp
    @Column(name="last_edited_time", nullable = false)
    val lastEditedTime: LocalDateTime,

    @Column(name="title", nullable = false)
    var title: String,

    @Column(name="body", nullable = false)
    var body: String,

    @Column(name="image_id", nullable = false)
    var imageId: UUID,

    @Id
    @Column(name="id", updatable = false, nullable=false)
    var id: UUID

): RepresentationModel<Content>()
{
        constructor(title:String,body:String,imageId: UUID):this(
            createdTime = LocalDateTime.now(),
            lastEditedTime = LocalDateTime.now(),
            title = title,
            body = body,
            imageId = imageId,
            id = UlidCreator.getUlid().toRfc4122().toUuid()
        )
}