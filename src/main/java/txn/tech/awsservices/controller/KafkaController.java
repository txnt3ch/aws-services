package txn.tech.awsservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import txn.tech.awsservices.dto.NotificationDto;
import txn.tech.awsservices.kafka.consumer.Receiver;
import txn.tech.awsservices.kafka.producer.Sender;
import txn.tech.awsservices.service.NotificationService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Configure Kafka in application.yaml
// Create consumer and producer config and bean classes (Consumer, ConsumerConfig, Receiver, ReceiverConfig)
// Create KafkaController for sample of sending event
// The consumer is autowired, and will save a notification to database (Receiver.java)
//




@RestController
public class KafkaController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/kafka/send")
    public String sendEvent(@RequestParam(value = "payload", defaultValue = "{nothing}") String payload) {
        sender.send(payload);
        return String.format("Kafka event %s: %s", counter.incrementAndGet(), payload);
    }

    @Autowired
    private Sender sender;


}
