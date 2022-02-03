package ru.store.store_user.service

import org.springframework.stereotype.Service
import ru.store.store_user.encryption.EncoderDecoder
import ru.store.store_user.lowercaseWords
import ru.store.store_user.model.UserDto
import ru.store.store_user.repository.IUserRerository
import kotlin.math.log

@Service
class UserService(
    private val repository: IUserRerository,
    private val encoderDecoder: EncoderDecoder
    ): IUserService {

    override fun save(userDto: UserDto): Int {
        if (userDto.login.isNullOrBlank() ||
            userDto.password.isNullOrBlank() ||
            userDto.role.isNullOrBlank()) {
            throw IllegalArgumentException("Not correct login: ${userDto.login} or password or role")
        }
        userDto.apply { password = password?.let { encoderDecoder.encoder(it) } }
        return repository.save(userDto)
    }

    override fun getUserByLogin(login: String): UserDto? {
        if (login.isNullOrBlank()) throw IllegalArgumentException("Not correct login: $login")
        return repository.getUserByLogin(login)?.apply {
            password?.let { encoderDecoder.decoder(it) }
        }
    }

    override fun getUserById(id: Long): UserDto? {
        if (id <= 0) throw IllegalArgumentException("Not correct id: $id")

        return repository.getUserById(id)?.apply {
            password?.let { encoderDecoder.decoder(it) }
        }
    }

    override fun getUser(userDto: UserDto): UserDto? {
        if (userDto.login.isNullOrBlank() || userDto.password.isNullOrBlank()) {
            throw IllegalArgumentException("Not correct login: ${userDto.login} or password")
        }

        userDto.password = encoderDecoder.encoder(userDto.password!!)

        return repository.getUser(userDto)?.apply {
            password?.let { encoderDecoder.decoder(it) }
        }
    }

    override fun delete(id: Long): Int {
        if (id <= 0) throw IllegalArgumentException("Not correct id: $id")
        return repository.delete(id)
    }
}