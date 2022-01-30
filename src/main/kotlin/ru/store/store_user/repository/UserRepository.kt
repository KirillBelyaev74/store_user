package ru.store.store_user.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository
import ru.store.store_user.model.UserDto
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import ru.store.store_user.capitalizeWords
import ru.store.store_user.lowercaseWords
import ru.store.store_user.mapper.UserMapper

@Repository
@PropertySource("classpath:database/select.sql.properties")
class UserRepository: IUserRerository {

    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    @Value("\${insertUser}")
    private lateinit var insert: String

    @Value("\${find.user}")
    private lateinit var findUser: String
    @Value("\${find.user.by.login}")
    private lateinit var findUserByLogin: String
    @Value("\${find.user.by.id}")
    private lateinit var findUserById: String

    @Value("\${delete}")
    private lateinit var delete: String

    override fun save(userDto: UserDto): Int {
        val user = userDto.login?.let { getUserByLogin(it) }
        if (user != null) {
            throw IllegalArgumentException("So login: ${userDto.login} exists")
        }
        return jdbcTemplate.update(
            insert,
            MapSqlParameterSource()
                .addValue("role", userDto.role?.capitalizeWords())
                .addValue("login", userDto.login?.lowercaseWords())
                .addValue("password", userDto.password)
        )
    }

    override fun getUser(userDto: UserDto): UserDto? {
        return jdbcTemplate.query(
            findUser,
            mapOf("login" to userDto.login?.lowercaseWords(), "password" to userDto.password),
            UserMapper()
        ).firstOrNull()
    }

    override fun getUserByLogin(login: String): UserDto? {
        return jdbcTemplate.query(findUserByLogin, mapOf("login" to login.lowercaseWords()), UserMapper()).firstOrNull()
    }

    override fun getUserById(id: Long): UserDto? {
        return jdbcTemplate.query(findUserById, mapOf("id" to id), UserMapper()).firstOrNull()
    }

    override fun delete(id: Long): Int {
        return jdbcTemplate.update(delete, mapOf("id" to id))
    }
}