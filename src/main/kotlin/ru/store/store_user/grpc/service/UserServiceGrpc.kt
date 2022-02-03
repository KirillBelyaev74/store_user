package ru.store.store_user.grpc.service

import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import ru.store.store_user.UserOuterClass
import ru.store.store_user.UserServiceGrpc
import ru.store.store_user.grpc.mapper.UserRequestGrpcMapper
import ru.store.store_user.service.UserService

@GrpcService
class UserServiceGrpc(private val service: UserService): UserServiceGrpc.UserServiceImplBase() {

    override fun saveUser(
        request: UserOuterClass.User?,
        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
    ) {
        try {
            val result = service.save(UserRequestGrpcMapper.userRequestMapper(request))
            val response = UserRequestGrpcMapper.userActionMapperOk(result)
            responseObserver?.onNext(response)
        } catch (e: Exception) {
            val error = UserRequestGrpcMapper.userActionMapperError(e)
            responseObserver?.onNext(error)
        }
        responseObserver?.onCompleted()
    }

    override fun getUserByLogin(
        request: UserOuterClass.User?,
        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
    ) {
        try {
            val result = UserRequestGrpcMapper.userRequestMapper(request).login?.let { service.getUserByLogin(it) }
            val response = UserRequestGrpcMapper.userResponseMapperOk(result)
            responseObserver?.onNext(response)
        } catch (e: Exception) {
            val error = UserRequestGrpcMapper.userActionMapperError(e)
            responseObserver?.onNext(error)
        }
        responseObserver?.onCompleted()
    }

    override fun getUserById(
        request: UserOuterClass.User?,
        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
    ) {
        try {
            val result = UserRequestGrpcMapper.userRequestMapper(request).id?.let { service.getUserById(it) }
            val response = UserRequestGrpcMapper.userResponseMapperOk(result)
            responseObserver?.onNext(response)
        } catch (e: Exception) {
            val error = UserRequestGrpcMapper.userActionMapperError(e)
            responseObserver?.onNext(error)
        }
        responseObserver?.onCompleted()
    }

    override fun getUser(
        request: UserOuterClass.User?,
        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
    ) {
        try {
            val result = service.getUser(UserRequestGrpcMapper.userRequestMapper(request))
            val response = UserRequestGrpcMapper.userResponseMapperOk(result)
            responseObserver?.onNext(response)
        } catch (e: Exception) {
            val error = UserRequestGrpcMapper.userActionMapperError(e)
            responseObserver?.onNext(error)
        }
        responseObserver?.onCompleted()
    }

    override fun delete(
        request: UserOuterClass.User?,
        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
    ) {
        try {
            val result = UserRequestGrpcMapper.userRequestMapper(request).id?.let { service.delete(it) }
            val response = UserRequestGrpcMapper.userActionMapperOk(result)
            responseObserver?.onNext(response)
        } catch (e: Exception) {
            val error = UserRequestGrpcMapper.userActionMapperError(e)
            responseObserver?.onNext(error)
        }
        responseObserver?.onCompleted()
    }
}