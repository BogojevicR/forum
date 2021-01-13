package com.project.forum.repositories

import com.project.forum.models.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>{
}