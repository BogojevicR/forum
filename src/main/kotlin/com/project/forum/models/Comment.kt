package com.project.forum.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Comment (
    var text: String,
    var created: LocalDateTime,
    var likes: Int,
    @ManyToOne()
    @JoinColumn(name = "post_id")
    @JsonBackReference
    var post: Post,
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    var owner: User,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
){
}