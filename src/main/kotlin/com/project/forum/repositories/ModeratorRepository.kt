package com.project.forum.repositories

import com.project.forum.models.Moderator
import org.springframework.data.jpa.repository.JpaRepository

interface ModeratorRepository : JpaRepository<Moderator, Long> {
    fun findByEmail(email: String): Moderator?
    fun findByUsername(username: String): Moderator?
}