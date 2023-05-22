package com.example.fileuploader.dto

import java.time.LocalDateTime
import java.util.*

data class ContentImageEntityDTO(
    var createdTime: LocalDateTime,

    var lastEditedTime: LocalDateTime,

    var saveFileName: String,

    var originalFileName: String,

    var id: UUID
)