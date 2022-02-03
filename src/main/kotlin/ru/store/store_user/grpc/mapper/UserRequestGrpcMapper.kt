package ru.store.store_user.grpc.mapper

import ru.store.store_user.UserOuterClass.UserResponse
import ru.store.store_user.UserOuterClass.User
import ru.store.store_user.UserOuterClass.Status
import ru.store.store_user.UserOuterClass.Error
import ru.store.store_user.model.UserDto

object UserRequestGrpcMapper {

    fun userRequestMapper(user: User?): UserDto {
        return UserDto(
            id = user?.id ?: 0,
            role = user?.role ?: "",
            login = user?.login ?: "",
            password = user?.password ?: ""
        )
    }

    fun userResponseMapperOk(userDto: UserDto?): UserResponse {
        val user = User.newBuilder().apply {  ->
            userDto?.id?.let { id = it }
            userDto?.role?.let { role = it }
            userDto?.login?.let { login = it }
            userDto?.password?.let { password = it }
        }.build()

        val status = Status.OK

        return UserResponse.newBuilder()
            .setStatus(status)
            .setUser(user)
            .build()
    }

    fun userActionMapperOk(result: Int?): UserResponse {
        return when(result) {
            1 -> UserResponse.newBuilder().setStatus(Status.OK).build()
            else -> UserResponse.newBuilder().setStatus(Status.BAD).build()
        }
    }

    fun userActionMapperError(e: Exception): UserResponse {
        val errorResponse = Error.newBuilder()
            .setClass_(e::class.java.name)
            .setMessage(e.message ?: "Not data")
            .build()

        val status = Status.ERROR

        return UserResponse.newBuilder()
            .setStatus(status)
            .setError(errorResponse)
            .build()
    }
}