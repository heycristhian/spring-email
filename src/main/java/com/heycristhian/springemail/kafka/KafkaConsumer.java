package com.heycristhian.springemail.kafka;

import com.heycristhian.springemail.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @Value("${topic.name.consumer}")
    private String topicName;

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "scf")
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("Payload received: {}", payload);
        emailService.sendEmail(payload.value());
    }
}
