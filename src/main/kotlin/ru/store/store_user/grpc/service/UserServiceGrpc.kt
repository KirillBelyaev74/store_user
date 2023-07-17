//package ru.store.store_user.grpc.service
//
//import io.grpc.stub.StreamObserver
//import net.devh.boot.grpc.server.service.GrpcService
//import ru.store.store_user.UserOuterClass
//import ru.store.store_user.UserServiceGrpc
//import ru.store.store_user.grpc.mapper.UserRequestGrpcMapper
//import ru.store.store_user.service.UserService
//
//@GrpcService
//class UserServiceGrpc(private val service: UserService): UserServiceGrpc.UserServiceImplBase() {
//
//    override fun saveUser(
//        request: UserOuterClass.User?,
//        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
//    ) {
//        try {
//            val result = service.saveUser(UserRequestGrpcMapper.userRequestMapper(request))
//            val response = UserRequestGrpcMapper.userActionMapperOk(result)
//            responseObserver?.onNext(response)
//        } catch (e: Exception) {
//            val error = UserRequestGrpcMapper.userActionMapperError(e)
//            responseObserver?.onNext(error)
//        }
//        responseObserver?.onCompleted()
//    }
//
//    override fun getUserByLogin(
//        request: UserOuterClass.User?,
//        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
//    ) {
//        try {
//            val result = service.getUserByLogin(UserRequestGrpcMapper.userRequestMapper(request).login)
//            val response = UserRequestGrpcMapper.userResponseMapperOk(result)
//            responseObserver?.onNext(response)
//        } catch (e: Exception) {
//            val error = UserRequestGrpcMapper.userActionMapperError(e)
//            responseObserver?.onNext(error)
//        }
//        responseObserver?.onCompleted()
//    }
//
//    override fun delete(
//        request: UserOuterClass.User?,
//        responseObserver: StreamObserver<UserOuterClass.UserResponse>?
//    ) {
//        try {
//            val result = service.delete(UserRequestGrpcMapper.userRequestMapper(request).login)
//            val response = UserRequestGrpcMapper.userActionMapperOk(result)
//            responseObserver?.onNext(response)
//        } catch (e: Exception) {
//            val error = UserRequestGrpcMapper.userActionMapperError(e)
//            responseObserver?.onNext(error)
//        }
//        responseObserver?.onCompleted()
//    }
//}