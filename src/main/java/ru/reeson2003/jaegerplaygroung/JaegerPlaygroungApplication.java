package ru.reeson2003.jaegerplaygroung;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@EnableFeignClients
@SpringBootApplication
public class JaegerPlaygroungApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaegerPlaygroungApplication.class, args);
    }

    @Bean
    public NewTopic topic(@Value("${kafka.topic}") String topic) {
        return TopicBuilder.name(topic)
                           .partitions(1)
                           .replicas(1)
                           .build();
    }

}
