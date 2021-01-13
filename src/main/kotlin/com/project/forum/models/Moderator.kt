package com.project.forum.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity()
@Table(name = "moderator")
class Moderator(
    username: String,
    email: String,
    password: String, createdAt: LocalDateTime,
    status: UserStatus = UserStatus.NEEDS_CONFIRMATION,
    @ManyToMany(mappedBy = "moderators")
    @JsonBackReference
    var topics: List<Topic> = arrayListOf()
) : User(username, email, password, createdAt, status) {
}