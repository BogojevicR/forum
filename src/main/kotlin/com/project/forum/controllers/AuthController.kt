package com.project.forum.controllers

import com.project.forum.models.User
import com.project.forum.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(var authService: AuthService) {

    @PostMapping(value = ["/signup"])
    fun registerUser(@RequestBody user: User): ResponseEntity<*> {
        return authService.registerUser(user.username, user.email, user.password)
    }

    @PutMapping(value = ["/confirm{email}"])
    fun confirmUser(@RequestParam email: String): ResponseEntity<*> {
        return authService.confirmUser(email)
    }
}