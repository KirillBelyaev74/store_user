package ru.store.store_user.model

data class UserDto(
    val id: Long? = null,
    val login: String? = null,
    var password: String? = null,
    var roles: List<RoleDto>? = null
)
