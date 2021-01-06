package com.project.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table( name = "topic")
class Topic (
    var title: String,
    var description: String,
    var created: LocalDateTime,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
){

}