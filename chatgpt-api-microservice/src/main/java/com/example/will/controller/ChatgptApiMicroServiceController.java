package com.example.will.controller;

import com.example.will.http.okhttp.OkHttpAuthClient;
import com.example.will.model.GptParmeter;
import okhttp3.MediaType;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
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

        OkHttpAuthClient okHttpAuthClient = new OkHttpAuthClient();
        String result = okHttpAuthClient.doPost(JSON, jsonBody, API_URL, API_KEY);

        return result;
    }
}
