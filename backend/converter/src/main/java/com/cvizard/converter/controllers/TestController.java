package com.cvizard.converter.controllers;

import com.cvizard.converter.ChatGPTRequest;
import com.cvizard.converter.ChatGPTResponse;
import com.cvizard.converter.Message;
import com.cvizard.converter.Test;
import com.cvizard.converter.interfaces.ChatGPTClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final ChatGPTClient chatGPTClient;
    private final KafkaTemplate<String,String> template;
    @Value(value = "${settings.gpt-api-key}")
    private String apiKey;
    @PostMapping("gpt")
    public String testGPTPrompt(@RequestBody Test test){
        ChatGPTRequest request = new ChatGPTRequest(test.getModel(),test.getPrompt());
        ChatGPTResponse response = chatGPTClient.generateChatGPTResponse("Bearer "+apiKey,request);
        return response.getChoices().get(0).getMessage().getContent();
    }
    @PostMapping("topic")
    public void postToKafka(@RequestBody Message message){
        template.send("cleaned-text",message.getContent());
    }
}
