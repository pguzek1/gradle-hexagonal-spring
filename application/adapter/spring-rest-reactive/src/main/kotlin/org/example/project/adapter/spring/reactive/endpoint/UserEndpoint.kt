package org.example.project.adapter.spring.reactive.endpoint

import java.util.UUID
import org.example.project.adapter.spring.reactive.adapter.UserAdapter
import org.example.project.adapter.spring.reactive.criteria.CreateUserCriteria
import org.example.project.adapter.spring.reactive.dto.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserEndpoint(
    private val userAdapter: UserAdapter,
) {

    @GetMapping("{id}")
    fun getUser(@PathVariable id: UUID): UserDto {
        return userAdapter.findUser(id)
    }

    @PostMapping
    fun createUser(@RequestBody createUserCriteria: CreateUserCriteria): UUID {
        return userAdapter.persistUser(createUserCriteria)
    }

    @GetMapping
    fun hello(): String {
        return "Hello world"
    }

}