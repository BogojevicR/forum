package com.project.forum.repositories

import com.project.forum.models.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Long> {
    fun findByEmail(email: String): Admin?
    fun findByUsername(username: String): Admin?
}