package txn.tech.awsservices.kafka.producer;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import txn.tech.awsservices.service.NotificationService;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);




    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String payload) {
        LOGGER.info("sending payload='{}'", payload);
        kafkaTemplate.send("notification", payload);
    }
}