package com.example.fileuploader.controller

import com.example.fileuploader.domain.Content
import com.example.fileuploader.domain.ContentImage
import com.example.fileuploader.repository.ContentImageRepository
import com.example.fileuploader.repository.ContentRepository
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity.status
import org.springframework.mock.web.MockMultipartFile
import org.springframework.mock.web.MockPart
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.multipart
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.transaction.annotation.Transactional
import java.nio.charset.StandardCharsets
import java.util.UUID


@Transactional
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentControllerTest {
    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var contentRepository: ContentRepository
    @Autowired lateinit var contentImageRepository: ContentImageRepository

    @Test
    @DisplayName("파일 업로드 테스트")
    fun storeFile(){
        val imagePart = MockMultipartFile("file","test.txt" , "text/plain" , "hello file".byteInputStream(StandardCharsets.UTF_8))

        mockMvc.multipart("/file/post")
        {
                file(imagePart)
                .part(MockPart("title", "title1".toByteArray(StandardCharsets.UTF_8)))
                .part(MockPart("body", "body1".toByteArray(StandardCharsets.UTF_8)))
        }
            .andDo {
                print()
            }
            .andExpect {
                status {
                    isOk()
                }
            }

        val contentImage : Iterable<ContentImage> = contentImageRepository.findAll()

        Assertions.assertEquals(1, contentImage.count())
        Assertions.assertEquals("test.txt", contentImage.elementAt(0).originalFileName)
    }

    @Test
    @DisplayName("파일 조회 테스트")
    fun seeFile(){
        val file1 = MockMultipartFile("file", "test1.txt", "text/plain", "test1 - hello".byteInputStream(StandardCharsets.UTF_8))
        val file2 = MockMultipartFile("file", "test2.txt", "text/plain", "test2 - hello".byteInputStream(StandardCharsets.UTF_8))

        mockMvc.multipart("/file/post")
        {
            file(file1)
                .part(MockPart("title", "title1".toByteArray(StandardCharsets.UTF_8)))
                .part(MockPart("body", "body1".toByteArray(StandardCharsets.UTF_8)))
        }
            .andDo {
                print()
            }
            .andExpect {
                status {
                    isOk()
                }
            }
        mockMvc.multipart("/file/post")
        {
            file(file2)
                .part(MockPart("title", "title2".toByteArray(StandardCharsets.UTF_8)))
                .part(MockPart("body", "body2".toByteArray(StandardCharsets.UTF_8)))
        }
            .andDo {
                print()
            }
            .andExpect {
                status {
                    isOk()
                }
            }
        val content:Iterable<Content> = contentRepository.findAll()
        val contentId:UUID = content.elementAt(0).id
        val contentImage:Iterable<ContentImage> = contentImageRepository.findAll()
        println(contentImage.toString())
        Assertions.assertEquals(2, contentImage.count())
        Assertions.assertEquals("test1.txt", contentImage.elementAt(0).originalFileName)
        Assertions.assertEquals("test2.txt", contentImage.elementAt(1).originalFileName)

        mockMvc.get("/file/{contentId}", contentId)
        {
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
        }
    }
}