package ru.store.store_user.controller

import org.springframework.web.bind.annotation.*
import ru.store.store_user.model.UserDto

@RequestMapping("/user")
interface IUserController {

    @PostMapping("/")
    fun saveUser(@RequestBody user: UserDto): Int

    @GetMapping("/{login}")
    fun getUserByLogin(login: String?): UserDto?

    @DeleteMapping("/{login}")
    fun delete(@PathVariable login: String): Int
}