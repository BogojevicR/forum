package com.project.forum

import com.project.forum.models.Moderator
import com.project.forum.models.Topic
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime

@SpringBootApplication
class ForumApplication

fun main(args: Array<String>) {
	runApplication<ForumApplication>(*args)
}
