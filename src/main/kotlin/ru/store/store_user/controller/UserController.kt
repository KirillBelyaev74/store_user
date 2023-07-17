package ru.store.store_user.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.logging.annotation.Log
import ru.store.store_user.model.UserDto
import ru.store.store_user.service.IUserService

@RestController
open class UserController(private val service: IUserService) : IUserController {

    @Log
    override fun saveUser(@RequestBody user: UserDto): Int {
        return service.saveUser(user)
    }

    override fun getUserByLogin(login: String?): UserDto? {
        return service.getUserByLogin(login)
    }

    @Log
    override fun delete(@PathVariable login: String): Int {
        return service.delete(login)
    }
}