package ru.store.store_user.grpc.mapper

import com.google.protobuf.StringValue
import ru.store.store_user.UserOuterClass.UserResponse
import ru.store.store_user.UserOuterClass.User
import ru.store.store_user.UserOuterClass.Status
import ru.store.store_user.UserOuterClass.Error
import ru.store.store_user.model.UserDto

object UserRequestGrpcMapper {

    fun userRequestMapper(user: User?): UserDto {
        return UserDto(
            id = if (user?.hasId() == true) user.id.value else null,
            role = if (user?.hasRole() == true) user.role.value else null,
            login = if (user?.hasLogin() == true) user.login.value else null,
            password = if (user?.hasPassword() == true) user.password.value else null
        )
    }

    fun userResponseMapperOk(userDto: UserDto?): UserResponse {
        val user = User.newBuilder().apply {
            role = StringValue.of(userDto?.role)
            login = StringValue.of(userDto?.login)
            password = StringValue.of(userDto?.password)
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