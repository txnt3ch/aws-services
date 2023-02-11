package txn.tech.awsservices.controller;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import txn.tech.awsservices.dto.NotificationDto;
import txn.tech.awsservices.service.NotificationService;

import java.util.List;
// Create entity Notification to map with database table
// Create dto NotificationDTO as Data Transfer Object to use in REST
// Create NotificationRepository interface that inherit JpaRepository
// Create NotificationService to handle the logic to interact with DB. This service will use the jpa repository to do CURD
// Create NotificationController that have notificationService instance and handle the REST API






@RestController
public class NotificationController {


    @Resource(name = "notificationService")
    private NotificationService notificationService;


    @GetMapping("/notifications/all")
    public List<NotificationDto> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping("/notifications/add")
    public ResponseEntity<NotificationDto> addNotification(@RequestBody NotificationDto notification) {
        notificationService.saveNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @PostMapping("/notifications/delete/{id}")
    public boolean addNotification(@PathVariable Long id) {
        return notificationService.deleteNotification(id);

    }


}
