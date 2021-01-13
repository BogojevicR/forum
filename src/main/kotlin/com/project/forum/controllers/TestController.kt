package com.project.forum.controllers

import com.project.forum.models.*
import com.project.forum.repositories.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/test")
class TestController(val userRepository: UserRepository,
                     val commentRepository: CommentRepository,
                     val topicRepository: TopicRepository,
                     val postRepository: PostRepository,
                     val adminRepository: AdminRepository,
                     val moderatorRepository: ModeratorRepository) {

    @GetMapping
    fun test(): List<User>{
        var user = User("ets","t","t", LocalDateTime.now())
        userRepository.save(user)

        var admin = Admin("admin", "t", "t", LocalDateTime.now())

        adminRepository.save(admin)

        var mod1 = Moderator("mod1", "t", "t", LocalDateTime.now())
        var mod2= Moderator("mod2", "t", "t", LocalDateTime.now())


        moderatorRepository.save(mod1)
        moderatorRepository.save(mod2)


        var topic = Topic("test", "test", LocalDateTime.now(), admin)

        topic.moderators = arrayListOf(mod1, mod2)

        topicRepository.save(topic)

        var post = Post("name", "test", LocalDateTime.now(), user, topic)

        postRepository.save(post)

        var com = Comment("t", LocalDateTime.now(),1, post, user)


       commentRepository.save(com)


        return userRepository.findAll();
    }
}