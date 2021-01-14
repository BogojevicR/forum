package com.project.forum.controllers

import com.project.forum.models.User
import com.project.forum.services.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/admin")
class AdminController(var adminService: AdminService) {

    @PostMapping(value = ["/createAdmin"])
    fun createAdmin(@RequestBody user: User): ResponseEntity<*> {
        return adminService.createAdmin(user.username, user.email, user.password)
    }

    @PostMapping(value = ["/createModerator"])
    fun createModerator(@RequestBody user: User): ResponseEntity<*> {
        return adminService.createModerator(user.username, user.email, user.password)
    }

    @PutMapping(value = ["/ban{email}"])
    fun banUser(@RequestParam email: String): ResponseEntity<*> {
        return adminService.banUser(email)
    }

    @PutMapping(value = ["/removeBan{email}"])
    fun removeBan(@RequestParam email: String): ResponseEntity<*> {
        return adminService.removeBan(email)
    }

}