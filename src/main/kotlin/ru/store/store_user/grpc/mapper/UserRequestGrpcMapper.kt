//package ru.store.store_user.grpc.mapper
//
//import com.google.protobuf.StringValue
//import ru.store.store_user.UserOuterClass.*
//import ru.store.store_user.model.RoleDto
//import ru.store.store_user.model.UserDto
//
//object UserRequestGrpcMapper {
//
//    fun userRequestMapper(user: User?): UserDto {
//        return UserDto(
//            login = if (user?.hasLogin() == true) user.login.value else null,
//            password = if (user?.hasPassword() == true) user.password.value else null,
//            roles = if (user?.rolesCount == 0) user.rolesList.map { RoleDto(it.value) } else null,
//        )
//    }
//
//    fun userResponseMapperOk(userDto: UserDto?): UserResponse {
//        val user = User.newBuilder().apply {
//            login = StringValue.of(userDto?.login)
//            password = StringValue.of(userDto?.password)
//            addAllRoles(userDto?.roles?.map { StringValue.of(it.name) })
//        }.build()
//
//        val status = Status.OK
//
//        return UserResponse.newBuilder()
//            .setStatus(status)
//            .setUser(user)
//            .build()
//    }
//
//    fun userActionMapperOk(result: Int?): UserResponse {
//        val user = User.newBuilder().apply { login = StringValue.of(result.toString()) }.build()
//
//        val status = Status.OK
//
//        return UserResponse.newBuilder()
//            .setStatus(status)
//            .setUser(user)
//            .build()
//    }
//
//    fun userActionMapperError(e: Exception): UserResponse {
//        val errorResponse = Error.newBuilder()
//            .setClass_(e::class.java.name)
//            .setMessage(e.message ?: "Not data")
//            .build()
//
//        val status = Status.ERROR
//
//        return UserResponse.newBuilder()
//            .setStatus(status)
//            .setError(errorResponse)
//            .build()
//    }
//}