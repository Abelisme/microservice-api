package com.example.will.grpc;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUser(UserProto.UserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        // 模擬從數據庫獲取用戶
        UserProto.UserResponse response = UserProto.UserResponse.newBuilder()
                .setId(request.getId())
                .setName("User " + request.getId())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
