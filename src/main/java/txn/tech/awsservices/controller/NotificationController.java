package txn.tech.awsservices.controller;
import jakarta.annotation.Resource;
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
    public String addNotification(@RequestBody NotificationDto notification) {
        notificationService.saveNotification(notification);
        return "ok";
    }

    @PostMapping("/notifications/delete/{id}")
    public boolean addNotification(@PathVariable Long id) {
        return notificationService.deleteNotification(id);

    }


}
