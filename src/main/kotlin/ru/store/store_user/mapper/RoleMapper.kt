package ru.store.store_user.mapper

import org.springframework.jdbc.core.RowMapper
import ru.store.store_user.model.RoleDto
import java.sql.ResultSet

class RoleMapper: RowMapper<RoleDto> {

    override fun mapRow(rs: ResultSet, rowNum: Int): RoleDto {
        return RoleDto(name = rs.getString("authority"))
    }
}