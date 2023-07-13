package com.cvizard.converter.interfaces;

import com.cvizard.converter.ChatGPTRequest;
import com.cvizard.converter.ChatGPTResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chatGPTClient", url = "https://api.openai.com/v1")
public interface ChatGPTClient {
    @PostMapping("/chat/completions")
    ChatGPTResponse generateChatGPTResponse(@RequestHeader("Authorization") String authorization,
                                            @RequestBody ChatGPTRequest request);
}