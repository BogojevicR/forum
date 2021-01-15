package com.project.forum.controllers

import com.project.forum.models.User
import com.project.forum.services.AuthService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when`
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.project.forum.models.UserStatus
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@ExtendWith(MockitoExtension::class)
internal class AuthControllerTest {

    val username = "username";
    val email = "email";
    val password = "password";

    @Mock
    lateinit var authService: AuthService;

    @InjectMocks
    lateinit var authController: AuthController

    private lateinit var mockMvc: MockMvc

    val mapper = ObjectMapper().registerModule(JavaTimeModule())

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build()
    }

    @Test
    fun registerUser() {
        val user = User(username, email, password)
        `when`(authService.registerUser(username, email, password)).thenReturn(ResponseEntity.ok(user))

        mockMvc.perform(
            post("/api/auth/signup/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.username", equalTo(username)))
    }

    @Test
    fun confirmUser() {
        val user = User(username, email, password)
        user.status = UserStatus.ACTIVE
        `when`(authService.confirmUser(email)).thenReturn(ResponseEntity.ok(user))

        mockMvc.perform(
            put("/api/auth/confirm").param("email", user.email)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.username", equalTo(username)))
    }
}