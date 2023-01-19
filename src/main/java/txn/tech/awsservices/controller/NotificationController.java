package txn.tech.awsservices.controller;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import txn.tech.awsservices.dto.NotificationDto;
import txn.tech.awsservices.service.NotificationService;

import java.util.List;

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
