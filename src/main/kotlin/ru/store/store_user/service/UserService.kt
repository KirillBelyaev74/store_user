package ru.store.store_user.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.store.store_user.model.RoleDto
import ru.store.store_user.model.UserDto
import ru.store.store_user.repository.IUserRepository

@Service
class UserService(private val repository: IUserRepository, private val passwordEncoder: PasswordEncoder): IUserService {

    override fun saveUser(userDto: UserDto): Int {
        if (userDto.login.isNullOrBlank() || userDto.password.isNullOrBlank() || userDto.roles?.isEmpty() == true) {
            throw IllegalArgumentException("No correct parameters login: ${userDto.login} or roles: ${userDto.roles} or password")
        }
        userDto.password = passwordEncoder.encode(userDto.password)
        var response = repository.saveUser(userDto)
        userDto.roles?.filter { !it.name.isNullOrBlank() }?.forEach { response += saveAuthority(userDto.login, it.name) }
        return response
    }

    override fun saveAuthority(login: String?, role: String?): Int {
        return repository.saveAuthority(login, role)
    }

    override fun getUserByLogin(login: String?): UserDto? {
        if (login.isNullOrBlank()) throw IllegalArgumentException("Not correct login: $login")
        val response = repository.getUserByLogin(login)?.apply {
            roles = getAuthoritiesByLogin(login)
        }
        return response
    }

    override fun getAuthoritiesByLogin(login: String): List<RoleDto> {
        if (login.isNullOrBlank()) throw IllegalArgumentException("Not correct login: $login")
        return repository.getAuthoritiesByLogin(login)
    }

    override fun getAuthoritiesByAuthority(role: String): String? {
        if (role.isNullOrBlank()) throw IllegalArgumentException("Not correct login: $role")
        return repository.getAuthoritiesByAuthority(role)
    }

    override fun delete(login: String?): Int {
        if (login.isNullOrBlank()) throw IllegalArgumentException("Not correct login: $login")
        return repository.delete(login)
    }
}