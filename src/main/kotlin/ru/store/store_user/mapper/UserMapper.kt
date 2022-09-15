package ru.store.store_user.mapper

import org.springframework.jdbc.core.RowMapper
import ru.store.store_user.model.UserDto
import java.sql.ResultSet

class UserMapper: RowMapper<UserDto> {

    override fun mapRow(rs: ResultSet, rowNum: Int): UserDto {
        return UserDto(
            login = rs.getString("login"),
            password = rs.getString("password")
        )
    }
}