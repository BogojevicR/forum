package com.project.forum.models

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "admin")
class Admin(
    username: String,
    email: String,
    password: String,
    createdAt: LocalDateTime,
    status: UserStatus = UserStatus.NEEDS_CONFIRMATION,
    id: Long = 0
) : User(username, email, password, createdAt) {
}