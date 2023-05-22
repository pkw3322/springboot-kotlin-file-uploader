package com.example.fileuploader.controller

import com.example.fileuploader.domain.Content
import com.example.fileuploader.dto.ContentResponseDTO
import com.example.fileuploader.mapper.Mapper
import com.example.fileuploader.service.ContentService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping(value = ["/file"])
class ContentController (val contentService: ContentService,val mapper: Mapper){

    @GetMapping
    fun getContents(): ResponseEntity<MutableIterable<Content>>{
        return ResponseEntity.ok(contentService.getContents())
    }

    @GetMapping(value = ["/{contentId}"])
    fun getContent(
        @PathVariable("contentId") @Validated @org.hibernate.validator.constraints.UUID contentIdString: String
    ):ResponseEntity<ContentResponseDTO>{
        val contentId : UUID = UUID.fromString(contentIdString)
        return ResponseEntity.ok(contentService.getContent(contentId))
    }

    @PostMapping(value = ["/post"])
    fun postBoard(
        @RequestParam title: String,
        @RequestParam body: String,
        @RequestParam(required = false) file: MultipartFile?): ResponseEntity<String> {
        contentService.saveContent(title, body, file)

        return ResponseEntity.ok().body("저장완료")
    }

}