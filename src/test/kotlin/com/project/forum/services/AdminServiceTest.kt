package com.project.forum.services

import com.project.forum.models.Admin
import com.project.forum.models.Moderator
import com.project.forum.models.User
import com.project.forum.models.UserStatus
import com.project.forum.repositories.AdminRepository
import com.project.forum.repositories.ModeratorRepository
import com.project.forum.repositories.UserRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ExtendWith(MockitoExtension::class)
internal class AdminServiceTest {

    private var user: User = User("username","email", "password" )

    private var admin: Admin = Admin("admin","adminEmail", "adminPassword" )

    private var mod: Moderator = Moderator("mod","modEmail", "modPassword" )


    private var bannedUser: User = User("username","email", "password" )


    private lateinit var adminService: AdminService;

    @Mock
    private lateinit var userRepository: UserRepository;

    @Mock
    private lateinit var  adminRepository: AdminRepository

    @Mock
    private lateinit var moderatorRepository: ModeratorRepository

    @BeforeEach
    fun setUp() {
        adminService = AdminService(userRepository, adminRepository, moderatorRepository)
        bannedUser.status = UserStatus.BANNED
    }

    @Test
    fun banUser() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(user)
        `when`(userRepository.save(Mockito.any())).thenReturn(user)


        val response: ResponseEntity<*> = adminService.banUser(user.email)
        val user = response.body as User

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(UserStatus.BANNED, user.status)
    }

    @Test
    fun banUserNotExist() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(null)

        val response: ResponseEntity<*> = adminService.banUser(user.email)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun removeBan() {
        `when`(userRepository.findByEmail(bannedUser.email)).thenReturn(bannedUser)
        `when`(userRepository.save(Mockito.any())).thenReturn(bannedUser)


        val response: ResponseEntity<*> = adminService.removeBan(user.email)
        val user = response.body as User

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(UserStatus.ACTIVE, user.status)
    }

    @Test
    fun removeBanUserNotExist() {
        `when`(userRepository.findByEmail(user.email)).thenReturn(null)

        val response: ResponseEntity<*> = adminService.removeBan(user.email)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun createAdmin() {
        `when`(adminRepository.findByEmail(admin.email)).thenReturn(null)
        `when`(adminRepository.findByUsername(admin.username)).thenReturn(null)
        `when`(adminRepository.save(Mockito.any())).thenReturn(admin)

        val savedUser: Admin = adminService.createAdmin(admin.username, admin.email, admin.password).body as Admin

        assertEquals(admin.username, savedUser.username)
        assertEquals(admin.email, savedUser.email)
        assertEquals(admin.password, savedUser.password)
    }

    @Test
    fun createAdminEmailExists() {
        `when`(adminRepository.findByEmail(admin.email)).thenReturn(admin)

        val response: ResponseEntity<*> = adminService.createAdmin(admin.username, admin.email, admin.password)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun createAdminUsernameExists() {
        `when`(adminRepository.findByEmail(admin.email)).thenReturn(null)
        `when`(adminRepository.findByUsername(admin.username)).thenReturn(admin)

        val response: ResponseEntity<*> = adminService.createAdmin(admin.username, admin.email, admin.password)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun createModerator() {
        `when`(moderatorRepository.findByEmail(mod.email)).thenReturn(null)
        `when`(moderatorRepository.findByUsername(mod.username)).thenReturn(null)
        `when`(moderatorRepository.save(Mockito.any())).thenReturn(mod)

        val savedUser: Moderator = adminService.createModerator(mod.username, mod.email, mod.password).body as Moderator

        assertEquals(mod.username, savedUser.username)
        assertEquals(mod.email, savedUser.email)
        assertEquals(mod.password, savedUser.password)
    }

    @Test
    fun createModeratorEmailExists() {
        `when`(moderatorRepository.findByEmail(mod.email)).thenReturn(mod)

        val response: ResponseEntity<*> = adminService.createModerator(mod.username, mod.email, mod.password)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun createModeratorUsernameExists() {
        `when`(moderatorRepository.findByEmail(mod.email)).thenReturn(null)
        `when`(moderatorRepository.findByUsername(mod.username)).thenReturn(mod)

        val response: ResponseEntity<*> = adminService.createModerator(mod.username, mod.email, mod.password)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }
}