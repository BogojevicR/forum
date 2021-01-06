package com.project.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity()
@Table(name = "moderator")
class Moderator(
    username: String,
    email: String,
    password: String, createdAt: LocalDateTime, status: UserStatus = UserStatus.NEEDS_CONFIRMATION
) : User(username, email, password, createdAt, status) {
}