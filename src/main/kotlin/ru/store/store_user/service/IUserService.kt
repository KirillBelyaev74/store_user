package ru.store.store_user.service

import ru.store.store_user.model.RoleDto
import ru.store.store_user.model.UserDto

interface IUserService {

    fun saveUser(userDto: UserDto): Int

    fun saveAuthority(login: String?, role: String?): Int

    fun getUserByLogin(login: String?): UserDto?

    fun getAuthoritiesByLogin(login: String): List<RoleDto>

    fun getAuthoritiesByAuthority(role: String): String?

    fun delete(login: String?): Int
}