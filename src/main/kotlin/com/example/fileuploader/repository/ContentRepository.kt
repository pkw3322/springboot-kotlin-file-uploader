package com.example.fileuploader.repository

import com.example.fileuploader.domain.Content
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ContentRepository:CrudRepository<Content, UUID>{
    fun existsContentById(id:UUID):Boolean

}