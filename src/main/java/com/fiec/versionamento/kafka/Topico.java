package com.fiec.versionamento.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;


public class Topico{
    @Bean
    public NewTopic myTopic() {
        return TopicBuilder.name("matriculas")
            .partitions(3)
            .replicas(1)
            .build();
    }
}
