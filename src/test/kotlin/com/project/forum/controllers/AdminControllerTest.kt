package com.project.forum.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.project.forum.models.Admin
import com.project.forum.models.Moderator
import com.project.forum.models.User
import com.project.forum.models.UserStatus
import com.project.forum.services.AdminService
import com.project.forum.services.AuthService
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
internal class AdminControllerTest {

    val username = "username";
    val email = "email";
    val password = "password";

    @Mock
    lateinit var adminService: AdminService;

    @InjectMocks
    lateinit var adminController: AdminController

    private lateinit var mockMvc: MockMvc

    val mapper = ObjectMapper().registerModule(JavaTimeModule())


    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build()
    }

    @Test
    fun createAdmin() {
        val admin = Admin(username, email, password)
        Mockito.`when`(adminService.createAdmin(username, email, password)).thenReturn(ResponseEntity.ok(admin))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/admin/createAdmin/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(admin))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo(username)))
    }

    @Test
    fun createModerator() {
        val mod = Moderator(username, email, password)
        Mockito.`when`(adminService.createModerator(username, email, password)).thenReturn(ResponseEntity.ok(mod))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/admin/createModerator/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mod))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo(username)))

    }

    @Test
    fun banUser() {
        val user = User(username, email, password)
        user.status = UserStatus.BANNED
        Mockito.`when`(adminService.banUser(email)).thenReturn(ResponseEntity.ok(user))

        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/admin/ban").param("email", user.email)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo(username)))
    }

    @Test
    fun removeBan() {
        val user = User(username, email, password)
        user.status = UserStatus.ACTIVE
        Mockito.`when`(adminService.removeBan(email)).thenReturn(ResponseEntity.ok(user))

        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/admin/removeBan").param("email", user.email)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo(username)))
    }
}