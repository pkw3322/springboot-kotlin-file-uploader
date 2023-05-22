package com.example.fileuploader.mapper

import com.example.fileuploader.domain.Content
import com.example.fileuploader.domain.ContentImage
import com.example.fileuploader.dto.ContentCreateDTO
import com.example.fileuploader.dto.ContentImageCreateDTO
import org.springframework.stereotype.Component

@Component
class Mapper {
    fun toContent(contentCreateDTO: ContentCreateDTO):Content{
        return Content(
            contentCreateDTO.title,
            contentCreateDTO.body,
            contentCreateDTO.imageId
        )
    }
    fun toContentCreateDTO(content : Content):ContentCreateDTO{
        return ContentCreateDTO(
            content.title,
            content.body,
            content.imageId
        )
    }
    fun toContentImage(contentImageCreateDTO: ContentImageCreateDTO):ContentImage{
        return ContentImage(
            contentImageCreateDTO.saveFileName,
            contentImageCreateDTO.originalFileName
        )
    }
    fun toContentImageCreateDTO(contentImage: ContentImage):ContentImageCreateDTO{
        return ContentImageCreateDTO(
            contentImage.saveFileName,
            contentImage.originalFileName
        )
    }

}