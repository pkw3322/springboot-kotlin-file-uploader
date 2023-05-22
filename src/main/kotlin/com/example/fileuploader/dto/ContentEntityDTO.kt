package com.example.fileuploader.dto

import java.time.LocalDateTime
import java.util.*

data class ContentEntityDTO(
    var createdTime: LocalDateTime,

    var lastEditedTime: LocalDateTime,

    var title: String,

    var body: String,

    var imageId: UUID,

    var id: UUID
)