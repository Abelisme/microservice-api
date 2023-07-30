package com.example.will.controller;
import com.example.will.model.GptParmeter;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/chatgpt-api")
public class ChatgptApiMicroServiceController {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    @GetMapping("/message")
    public String test() {
        return "Hello this is my first microservice";
    }

//    @PostMapping("/call")
    @RequestMapping(value="/call", method= RequestMethod.POST)
    @ResponseBody
    public String call(HttpServletRequest httpServletRequest, @RequestBody GptParmeter gptParmeter) {
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonBody = "{\"role\":\"assistant\", \"content\":\"Translate the following English text to French: '{hi men you are such nice person}'\",\"max_tokens\":60}";
//        jsonBody = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"assistant\", \"content\": \"Translate the following English text to French: '{hi men you are such nice person}'\"}, {\"role\": \"user\", \"content\": \"Hello!\"}]}";
        String model = StringUtils.isNotBlank(gptParmeter.getModel()) ? gptParmeter.getModel() : "";
        String contents = StringUtils.isNotBlank(gptParmeter.getContents()) ? gptParmeter.getContents() : "";
        String role = StringUtils.isNotBlank(gptParmeter.getRole()) ? gptParmeter.getRole() : "";

        if (StringUtils.isBlank(contents)){
            return "內容為空，無法執行。";
        }
        jsonBody = "{\"model\": \"" + model + "\",\"messages\": [{\"role\": \"" + role +"\", \"content\": \"" + contents + "\"}]}";

        okhttp3.RequestBody body = okhttp3.RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            //response.body() 在okhttp中，response只能被调用一次。
            String result = response.body().string();
            System.out.println(result);

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
