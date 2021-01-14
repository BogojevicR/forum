package com.project.forum.services

import com.project.forum.models.Admin
import com.project.forum.models.Moderator
import com.project.forum.models.UserStatus
import com.project.forum.repositories.AdminRepository
import com.project.forum.repositories.ModeratorRepository
import com.project.forum.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AdminService(var userRepository: UserRepository,
                   var adminRepository: AdminRepository,
                   var moderatorRepository: ModeratorRepository) {

    fun banUser(email: String): ResponseEntity<*> {
        var user = userRepository.findByEmail(email)
        if (user == null) {
            return ResponseEntity.badRequest().body("User with email doesn't exists.")
        }
        user.status = UserStatus.BANNED
        user = userRepository.save(user)
        return ResponseEntity.ok().body(user)
    }

    fun removeBan(email: String): ResponseEntity<*> {
        var user = userRepository.findByEmail(email)
        if (user == null) {
            return ResponseEntity.badRequest().body("User with email doesn't exists.")
        }
        user.status = UserStatus.ACTIVE
        user = userRepository.save(user)
        return ResponseEntity.ok().body(user)
    }

    fun createAdmin(username: String, email: String, password: String): ResponseEntity<*> {
        if(adminRepository.findByEmail(email) != null){
            return ResponseEntity.badRequest().body("User with email already exists.");
        }
        if(adminRepository.findByUsername(username) != null){
            return ResponseEntity.badRequest().body("User with username already exists.");
        }
        val user = adminRepository.save(Admin(username, email, password))
        return ResponseEntity.ok(user)
    }

    fun createModerator(username: String, email: String, password: String): ResponseEntity<*> {
        if(moderatorRepository.findByEmail(email) != null){
            return ResponseEntity.badRequest().body("User with email already exists.");
        }
        if(moderatorRepository.findByUsername(username) != null){
            return ResponseEntity.badRequest().body("User with username already exists.");
        }
        val user = moderatorRepository.save(Moderator(username, email, password))
        return ResponseEntity.ok(user)
    }
}