package com.project.forum.services

import com.project.forum.models.User
import com.project.forum.models.UserStatus
import com.project.forum.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(val userRepository: UserRepository) {

    fun registerUser(username: String, email: String, password: String): ResponseEntity<*>{
        if(userRepository.findByEmail(email) != null){
            return ResponseEntity.badRequest().body("User with email already exists.");
        }
        if(userRepository.findByUsername(username) != null){
            return ResponseEntity.badRequest().body("User with username already exists.");
        }
        val user = userRepository.save(User(username, email, password))
        return ResponseEntity.ok(user);
    }

    fun confirmUser(email: String): ResponseEntity<*> {
        var user = userRepository.findByEmail(email)
        if (user == null) {
            return ResponseEntity.badRequest().body("User with email doesn't exists.")
        }
        user.status = UserStatus.ACTIVE;
        user = userRepository.save(user)
        return ResponseEntity.badRequest().body(user)
    }

    fun banUser(email: String): ResponseEntity<*> {
        var user = userRepository.findByEmail(email)
        if (user == null) {
            return ResponseEntity.badRequest().body("User with email doesn't exists.")
        }
        user.status = UserStatus.BANNED
        user = userRepository.save(user)
        return ResponseEntity.badRequest().body(user)
    }

    fun removeBan(email: String): ResponseEntity<*> {
        var user = userRepository.findByEmail(email)
        if (user == null) {
            return ResponseEntity.badRequest().body("User with email doesn't exists.")
        }
        user.status = UserStatus.ACTIVE
        user = userRepository.save(user)
        return ResponseEntity.badRequest().body(user)
    }
}