package com.project.forum.repositories

import com.project.forum.models.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
}