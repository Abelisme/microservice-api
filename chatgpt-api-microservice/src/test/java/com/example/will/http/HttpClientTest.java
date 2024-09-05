package com.example.will.http;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

@SpringBootTest
public class HttpClientTest {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
    /*
     * 測試get
     */
    @Test
    public void testGet() throws Exception {
        String url = "https://api.github.com/users/Bard";
        HttpClient httpClient = new HttpClient();
        try {
            String jsonStr = httpClient.doGet(url, false);
//            System.out.println(jsonStr);
            logger.info("This is a log message" + jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 測試post
     */
    @Test
    public void testPost() throws Exception {
        String url = "https://api.github.com/repos/organization/repo/issues?access_token=e72e16c7e42f292c6912e7710c838347ae178b4a";
        HttpClient httpClient = new HttpClient();
        try {
            String json = "{\"title\":\"Title\"}";
            String response = httpClient.doPost(url, MediaType.APPLICATION_JSON, json, false);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
