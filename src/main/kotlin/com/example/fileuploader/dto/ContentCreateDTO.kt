package com.example.fileuploader.dto

import java.util.*

data class ContentCreateDTO(
    var title : String,
    var body : String,
    var imageId : UUID
)