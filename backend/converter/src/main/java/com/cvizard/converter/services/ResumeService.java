package com.cvizard.converter.services;

import com.cvizard.converter.ChatGPTRequest;
import com.cvizard.converter.ChatGPTResponse;
import com.cvizard.converter.interfaces.ChatGPTClient;
//import com.cvizard.converter.repositories.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {
//    private final ResumeRepository resumeRepository;
    private final ChatGPTClient chatGPTClient;
    @Value("${settings.prompt}")
    private String prompt;
    @Value("${settings.gpt-api-key}")
    private String apiKey;
    @Value("${settings.model}")
    private String model;
     public void resumeConverter(String resumeText){
         String promptWithText = prompt.replace("[RESUME TEXT]",resumeText);
         ChatGPTRequest request = new ChatGPTRequest(model,promptWithText);
         ChatGPTResponse response = chatGPTClient.generateChatGPTResponse("Bearer "+apiKey,request);
         System.out.println(response.getChoices().get(0).getMessage());
     }
}
