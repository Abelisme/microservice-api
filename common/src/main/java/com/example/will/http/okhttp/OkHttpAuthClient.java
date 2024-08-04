package com.example.will.http.okhttp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpAuthClient {
    public String doPost(MediaType type, String jsonBody, String url, String api_key){
        String result = null;
        OkHttpClient okHttpClient = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        okhttp3.RequestBody body = okhttp3.RequestBody.create(jsonBody, type);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + api_key)
                .post(body)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            //response.body() 在okhttp中，response只能被调用一次。
            result = response.body().string();
            System.out.println(result);

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
