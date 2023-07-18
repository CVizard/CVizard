package com.cvizard.converter.services;

import com.cvizard.converter.ChatGPTRequest;
import com.cvizard.converter.ChatGPTResponse;
import com.cvizard.converter.interfaces.ChatGPTClient;
import com.cvizard.converter.models.Resume;
import com.cvizard.converter.repositories.ResumeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final ChatGPTClient chatGPTClient;
//    @Value("${classpath:prompt.txt}")
//    private Resource prompt;
    private final String prompt = "you have here plain text from cv: [RESUME TEXT], analyse this and return me only this JSON template: { \"work\": { [ \"name\": \"\", \"position\": \"\", \"startDate\": \"\", \"endDate\": \"\", \"summary\": \"\", ] }, \"education\": { [ \"institution\": \"\", \"area\": \"\", \"studyType\": \"\", \"startDate\": \"\", \"endDate\": \"\" ] }, \"certificates\": { [ \"name\": \"\", \"date\": \"\", \"issuer\": \"\" ] }, \"skills\": { [ \"name\": \"\", \"level\": \"\", \"keywords\": [] ] }, \"languages\": { [ \"language\": \"\", \"level\": \"\" ] }, \"projects\": { [ \"name\": \"\", \"startDate\": \"\", \"endDate\": \"\", \"summary\": \"\" ] }, \"interests\": { [ \"name\": \"\", \"keywords\": [] ] } } with approximately parsed data from the given text";
    @Value("${settings.gpt-api-key}")
    private String apiKey;
    @Value("${settings.model}")
    private String model;

    @SneakyThrows
     public Resume resumeConverter(String resumeText, String key){
         String promptWithText = prompt.replace("[RESUME TEXT]",resumeText);
         ChatGPTRequest request = new ChatGPTRequest(model,promptWithText);
         ChatGPTResponse response = chatGPTClient.generateChatGPTResponse("Bearer "+apiKey,request);
        System.out.println(response.getChoices().get(0).getMessage().getContent());
         Resume resume = resumeParser(response.getChoices().get(0).getMessage().getContent());
         resume.setId(key);
         resumeRepository.save(resume);
         return resume;
     }
     @SneakyThrows
     public Resume resumeParser(String resumeText){
         ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper.readValue(resumeText, Resume.class);
     }
}
