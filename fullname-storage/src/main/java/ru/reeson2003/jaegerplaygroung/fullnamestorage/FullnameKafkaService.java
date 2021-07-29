package ru.reeson2003.jaegerplaygroung.fullnamestorage;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FullnameKafkaService {

    private final FullnameRepository repository;

    private final ObjectMapper mapper;

    @SneakyThrows
    @KafkaListener(topics = "${kafka.topic}")
    public void fullnameReceived(String data) {
        var fullname = mapper.readValue(data, Fullname.class);
        var entity = FullnameEntity.builder()
                                   .firstname(fullname.getFirstname())
                                   .lastname(fullname.getLastname())
                                   .build();
        repository.save(entity);
    }

}
