package com.project.forum.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table( name = "topic")
class Topic (
    var title: String,
    var description: String,
    var created: LocalDateTime,
    @ManyToOne()
    @JoinColumn(name = "admin_id")
    @JsonBackReference("admin-topics")
    var admin: Admin,
    @ManyToMany()
    @JoinTable(
        name = "moderator_topics",
        joinColumns = [JoinColumn(name = "topic_id")],
        inverseJoinColumns = [JoinColumn(name = "moderator_id")]
    )
    var moderators: List<Moderator> = arrayListOf(),
    @OneToMany(mappedBy = "topic", cascade = [CascadeType.ALL])
    @JsonManagedReference("topic-post")
    var posts: List<Post> = arrayListOf(),
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
){

}