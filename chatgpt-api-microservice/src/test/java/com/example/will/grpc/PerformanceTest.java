package com.example.will.grpc;

import com.example.will.http.HttpClient;
import com.example.will.model.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class PerformanceTest {
    private static final int ITERATIONS = 10000;

    /*
     * 測試get
     */
    @Test
    public void testGet() throws Exception {
        testRest();
//        testGrpc();
    }
    private static void testRest() throws Exception {
//        RestTemplate restTemplate = new RestTemplate();
        HttpClient httpClient = new HttpClient();
        String url = "http://localhost:8080/api/users/" + ITERATIONS;
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            String jsonStr = httpClient.doGet(url, false);
            System.out.println(jsonStr);
        }
        long end = System.currentTimeMillis();
        System.out.println("REST API took: " + (end - start) + "ms");
    }

    private static void testGrpc() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            stub.getUser(UserProto.UserRequest.newBuilder().setId(ITERATIONS).build());
        }
        long end = System.currentTimeMillis();
        System.out.println("gRPC took: " + (end - start) + "ms");

        channel.shutdown();
    }
}
