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
import ru.store.store_user.mapper.RoleMapper
import ru.store.store_user.mapper.UserMapper
import ru.store.store_user.model.RoleDto

@Repository
@PropertySource("classpath:database/select.sql.properties")
open class UserRepository: IUserRepository {

    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    @Value("\${save.user}")
    private lateinit var saveUser: String
    @Value("\${save.authority}")
    private lateinit var saveAuthority: String

    @Value("\${select.user.and.password.by.login}")
    private lateinit var findUserByLogin: String
    @Value("\${select.user.and.authority.by.login}")
    private lateinit var findAuthoritiesByLogin: String
    @Value("\${select.user.and.authority.by.authority}")
    private lateinit var findAuthorityByAuthority: String

    @Value("\${delete.user.by.login}")
    private lateinit var deleteUserByLogin: String

    override fun saveUser(userDto: UserDto): Int {
        return jdbcTemplate.update(saveUser,
            MapSqlParameterSource()
                .addValue("login", userDto.login?.lowercaseWords())
                .addValue("password", userDto.password)
        )
    }

    override fun saveAuthority(login: String?, role: String?): Int {
        return jdbcTemplate.update(saveAuthority,
            MapSqlParameterSource()
                .addValue("login", login?.lowercaseWords())
                .addValue("authority", role?.lowercase())
        )
    }

    override fun getUserByLogin(login: String): UserDto? {
        val response = jdbcTemplate.query(findUserByLogin, mapOf("login" to login.lowercaseWords()), UserMapper()).firstOrNull()
        response?.roles = getAuthoritiesByLogin(login)
        return response
    }

    override fun getAuthoritiesByLogin(login: String): List<RoleDto> {
        return jdbcTemplate.query(findAuthoritiesByLogin, mapOf("login" to login.lowercaseWords()), RoleMapper())
    }

    override fun getAuthoritiesByAuthority(role: String): String? {
        return jdbcTemplate.queryForObject(findAuthorityByAuthority, mapOf("authority" to role.lowercase()), String::class.java)
    }

    override fun delete(login: String): Int {
        return jdbcTemplate.update(deleteUserByLogin, mapOf("login" to login.lowercaseWords()))
    }
}