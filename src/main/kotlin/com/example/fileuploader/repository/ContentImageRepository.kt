package com.example.fileuploader.repository

import com.example.fileuploader.domain.ContentImage
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ContentImageRepository :CrudRepository<ContentImage,UUID>{
    fun existsContentImageById(id:UUID):Boolean
}