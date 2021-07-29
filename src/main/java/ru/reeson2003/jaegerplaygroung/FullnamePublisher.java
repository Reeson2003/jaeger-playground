package ru.reeson2003.jaegerplaygroung;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FullnamePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.topic}")
    private String topic;

    @SneakyThrows
    public void publish(Fullname fullname) {
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(fullname));
    }

}
