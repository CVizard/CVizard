package com.cvizard.converter;

import com.cvizard.converter.services.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaListeners {
    private final ResumeService resumeService;
    @KafkaListener(topics = "${settings.kafka.topics.cleaned-text}", groupId = "foo")
    void listener(String data){
        resumeService.resumeConverter(data);
    }
}
