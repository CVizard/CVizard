package com.cvizard.converter;

import com.cvizard.converter.services.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class KafkaListeners {
    private final ResumeService resumeService;
    @KafkaListener(topics = "${settings.kafka.topics.cleaned-text}", groupId = "ooo")
    void listener(@Payload String data, @Header UUID key){
        resumeService.resumeConverter(data, key);
    }
}
