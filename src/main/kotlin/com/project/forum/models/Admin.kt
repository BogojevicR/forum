package com.project.forum.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "admin")
class Admin(
    username: String,
    email: String,
    password: String,
    createdAt: LocalDateTime,
    @OneToMany(mappedBy = "admin", cascade = [CascadeType.ALL])
    @JsonManagedReference
    var topics: List<Topic> = arrayListOf(),
) : User(username, email, password, createdAt) {
}