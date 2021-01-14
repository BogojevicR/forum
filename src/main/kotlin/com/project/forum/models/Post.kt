package com.project.forum.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table( name = "post")
class Post (
    var title: String,
    var description: String,
    var created: LocalDateTime,
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-posts")
    var owner: User,
    @ManyToOne()
    @JoinColumn(name = "topic_id")
    @JsonBackReference("topic-post")
    var topic: Topic,
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL])
    @JsonManagedReference("post-comments")
    var comments: List<Comment> = arrayListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
){
}