package ru.store.store_user.service

import ru.store.store_user.model.UserDto

interface IUserService {

    fun save(userDto: UserDto): Int

    fun getUserByLogin(login: String): UserDto?

    fun getUserById(id: Long): UserDto?

    fun getUser(userDto: UserDto): UserDto?

    fun delete(id: Long): Int
}