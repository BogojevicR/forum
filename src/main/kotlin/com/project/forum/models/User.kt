package com.project.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class User(
    open var username: String,
    open var email: String,
    open var password: String,
    open var createdAt: LocalDateTime,
    open var status: UserStatus = UserStatus.NEEDS_CONFIRMATION,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) open val id: Long = 0
) {

}