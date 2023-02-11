package txn.tech.awsservices.kafka.consumer;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import txn.tech.awsservices.dto.NotificationDto;
import txn.tech.awsservices.service.NotificationService;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Resource(name = "notificationService")
    private NotificationService notificationService;

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "notification")
    public void receive(String payload) {
        LOGGER.info("received payload='{}'", payload);
        latch.countDown();
        try
        {
            NotificationDto notification = new NotificationDto();
            notification.setNotificationChannel("PUSH");
            notification.setNotificationTitle(payload);
            notification.setNotificationContent(payload);
            notification.setToAccountId("user0");
            notification.setPostDate(LocalDateTime.now());
            notificationService.saveNotification(notification);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}