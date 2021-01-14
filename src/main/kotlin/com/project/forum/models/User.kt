package com.project.forum.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
open class User(
    @Column(unique = true)
    open var username: String,
    @Column(unique = true)
    open var email: String,
    open var password: String,
    open var createdAt: LocalDateTime = LocalDateTime.now(),
    open var status: UserStatus = UserStatus.NEEDS_CONFIRMATION,

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    @JsonManagedReference("user-posts")
    open var posts: List<Post> = arrayListOf(),
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    @JsonManagedReference("user-comments")
    open var comments: List<Comment> = arrayListOf(),
    @Id @GeneratedValue(strategy = GenerationType.AUTO) open val id: Long = 0
) {

}