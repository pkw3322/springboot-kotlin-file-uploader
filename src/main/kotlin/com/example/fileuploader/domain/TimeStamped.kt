package com.example.fileuploader.domain


import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class TimeStamped {
    @CreationTimestamp
    @Column(name="created_time", nullable = false, updatable = false)
    val createdTime = LocalDateTime.now()

    @CreationTimestamp
    @Column(name="last_edited_time", nullable = false)
    val lastEditedTime: LocalDateTime = LocalDateTime.now()
}