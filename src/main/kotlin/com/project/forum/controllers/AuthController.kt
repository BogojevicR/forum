package com.project.forum.controllers

import com.project.forum.models.User
import com.project.forum.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(var userService: AuthService) {

    @PostMapping(value = ["/signup"])
    fun registerUser(@RequestBody user: User): ResponseEntity<*> {
        return userService.registerUser(user.username, user.email, user.password)
    }

    @PutMapping(value = ["/confirm{email}"])
    fun confirmUser(@RequestParam email: String): ResponseEntity<*> {
        return userService.confirmUser(email)
    }
}