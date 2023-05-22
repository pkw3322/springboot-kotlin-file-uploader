package com.example.fileuploader.service

import com.example.fileuploader.domain.Content
import com.example.fileuploader.domain.ContentImage
import com.example.fileuploader.dto.ContentResponseDTO
import com.example.fileuploader.dto.ContentUpdateDTO
import com.example.fileuploader.repository.ContentImageRepository
import com.example.fileuploader.repository.ContentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.lang.IllegalArgumentException
import java.util.UUID

@Service
class ContentService (val contentRepository: ContentRepository,val contentImageRepository: ContentImageRepository){
    val uploadDir = "../"

    fun getContents():MutableIterable<Content>{
        return contentRepository.findAll()
    }

    @Transactional
    fun saveContent(title:String,body:String,file:MultipartFile?){
        file?.let{
            val originalFileName = file.originalFilename ?: UUID.randomUUID().toString()
            val savedFileName = getSaveFileName(originalFileName)

            println(originalFileName)
            println(savedFileName)
            val fullPath = uploadDir+savedFileName
            println(fullPath)
            file.transferTo(File(fullPath))
            val contentImage = ContentImage(savedFileName,originalFileName)
            contentImageRepository.save(contentImage)
            val content = Content(title,body,contentImage.id)
            contentRepository.save(content)
        }
    }

    @Transactional
    fun getContent(contentId: UUID): ContentResponseDTO {
        var content: Content =
            contentRepository.findByIdOrNull(contentId) ?: throw IllegalArgumentException("존재하지 않는 게시물 입니다.")
        var contentImage: ContentImage? = contentImageRepository.findByIdOrNull(content.imageId)
        return ContentResponseDTO(content.title, content.body, contentImage?.saveFileName?:"파일 없음")
    }

    @Transactional
    fun updateContent(contentId:UUID,contentUpdateDTO:ContentUpdateDTO){
        var content: Content = contentRepository.findByIdOrNull(contentId)?: throw IllegalArgumentException("존재하지 않는 게시물 입니다.")
        content.title = contentUpdateDTO.title
        content.body = contentUpdateDTO.body
    }

    @Transactional
    fun deleteContent(contentId:UUID){
        if(contentRepository.existsContentById(contentId)){
            contentRepository.deleteById(contentId)
        }
    }

    private fun getSaveFileName(originalFileName:String):String{
        val extPosIndex: Int? = originalFileName?.lastIndexOf(".")
        val ext = originalFileName?.substring(extPosIndex?.plus(1) as Int)

        return UUID.randomUUID().toString() + "." + ext
    }
}