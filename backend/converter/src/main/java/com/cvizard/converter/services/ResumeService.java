package com.cvizard.converter.services;

import com.cvizard.converter.interfaces.ChatGPTClient;
import com.cvizard.converter.models.Resume;
import com.cvizard.converter.repositories.ResumeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final ChatGPTClient chatGPTClient;
    private final Gson gson;
    @Value("${settings.prompt}")
    private String prompt;
    @Value("${settings.gpt-api-key}")
    private String apiKey;
    @Value("${settings.model}")
    private String model;
     public Resume resumeConverter(String resumeText, String key){
         String promptWithText = prompt.replace("[RESUME TEXT]",resumeText);
         String tempText = "{   \"Education\": [     {       \"institution\": \"Technikum Ekonomiczne nr 1 im. Mikołaja Kopernika\",       \"area\": \"Technik ekonomista\",       \"studyType\": \"średnie\",       \"startDate\": \"09.2019\",       \"endDate\": \"05.2023\"     }   ],   \"Certificate\": [],   \"Skill\": [     {       \"name\": \"Poczucie humoru\",       \"level\": \"\",       \"keywords\": []     },     {       \"name\": \"Znajdowanie nieoczywistych rozwiązań\",       \"level\": \"\",       \"keywords\": []     },     {       \"name\": \"Umiejętność dogadania się z każdym\",       \"level\": \"\",       \"keywords\": []     }   ],   \"Language\": [     {       \"language\": \"angielski\",       \"level\": \"poziom zaawansowany\"     }   ],   \"Project\": [],   \"Interest\": [     {       \"name\": \"Czytanie książek samorozwojowych\",       \"keywords\": []     },     {       \"name\": \"aktywności fizyczne\",       \"keywords\": [         \"pływanie\",         \"bieganie\",         \"koszykówka\"       ]     },     {       \"name\": \"analyse this\",       \"keywords\": []     }   ] }";
//         ChatGPTRequest request = new ChatGPTRequest(model,promptWithText);
//         ChatGPTResponse response = chatGPTClient.generateChatGPTResponse("Bearer "+apiKey,request);
//         System.out.println(response.getChoices().get(0).getMessage());
         Resume resume = resumeParser(tempText);
         resume.setId(key);
         System.out.println(resume);
         resumeRepository.save(resume);
         return resume;
     }
     @SneakyThrows
     public Resume resumeParser(String resumeText){
         ObjectMapper objectMapper = new ObjectMapper();
//         objectMapper.registerModule(new JavaTimeModule());
         return objectMapper.readValue(resumeText, Resume.class);
     }
}
