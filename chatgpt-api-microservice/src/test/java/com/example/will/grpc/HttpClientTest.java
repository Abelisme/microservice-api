package com.example.will.grpc;

import com.example.will.http.HttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class HttpClientTest {
    private static final int ITERATIONS = 10000;

    @Test
    public void testHttpClient() throws Exception {
        String url = "http://localhost:8080/api/users/1000";
        HttpClient httpClient = new HttpClient();
        try {
//            String jsonStr = httpClient.doGet(url, MediaType.APPLICATION_JSON,null,null, false);
            String jsonStr = httpClient.doGet(url, false);
            System.out.println(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        RestTemplate restTemplate = new RestTemplate();
//        String id = "1000"; // 請將此處的id替換為您想要查詢的用戶id
//        String url = "http://localhost:8080/api/users/" + id;
//        String result = restTemplate.getForObject(url, String.class);
//        System.out.println(result);
    }
}
