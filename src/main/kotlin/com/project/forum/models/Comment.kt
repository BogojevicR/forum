package com.project.forum.models

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

class Comment (
    var comment: String,
    var created: LocalDateTime,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
){
}