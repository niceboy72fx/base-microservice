package com.microservice.order_service.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.TopicForRetryable;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j(topic = "KAFKA-PRODUCER")
public class KafkaProducer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.topic.checkoutOrder}")
    private String checkoutOrderTopic;

    @Value("${spring.kafka.topic.updateInventory}")
    private String updateInventory;

    @Value("${spring.kafka.topic.notifyOrderStatus}")
    private String notifyOrderStatus;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        if (profile.equals("prod")) {
            props.put("security.protocol", "SSL");
            props.put("ssl.truststore.type", "none");
            props.put("endpoint.identification.algorithm", "");
        }

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(checkoutOrderTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic(updateInventory, 3, (short) 1);
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic(notifyOrderStatus, 3, (short) 1);
    }
}
