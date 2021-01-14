package com.project.forum.services

import com.project.forum.models.User
import com.project.forum.models.UserStatus
import com.project.forum.repositories.UserRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@ExtendWith(MockitoExtension::class)
internal class AuthServiceTest {

    private val user: User = User("username","email", "password" )

    private lateinit var authService:AuthService;

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        authService = AuthService(userRepository)
    }

    @Test
    fun registerUser() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(null)
        `when`(userRepository.findByUsername(user.username)).thenReturn(null)
        `when`(userRepository.save(any())).thenReturn(user)

        val savedUser: User = authService.registerUser(user.username, user.email, user.password).body as User

        assertEquals(user.username, savedUser.username)
        assertEquals(user.email, savedUser.email)
        assertEquals(user.password, savedUser.password)
    }

    @Test
    fun registerUserEmailExists() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(user)

        val response: ResponseEntity<*> = authService.registerUser(user.username, user.email, user.password)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun registerUserUsernameExists() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(null)
        `when`(userRepository.findByUsername(user.username)).thenReturn(user)

        val response: ResponseEntity<*> = authService.registerUser(user.username, user.email, user.password)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun confirmUser() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(user)
        `when`(userRepository.save(any())).thenReturn(user)

        val response: ResponseEntity<*> = authService.confirmUser(user.email)
        val savedUser = response.body as User

        assertEquals(UserStatus.ACTIVE ,savedUser.status)
    }

    @Test
    fun confirmUserNotExist() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(null)

        val response: ResponseEntity<*> = authService.confirmUser(user.email)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }
}