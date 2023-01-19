package txn.tech.awsservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import txn.tech.awsservices.dto.NotificationDto;
import txn.tech.awsservices.entity.Notification;
import txn.tech.awsservices.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service("notificationService")
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDto> getAllNotifications()
    {
        List<NotificationDto> notificationDtos = new ArrayList<NotificationDto>();
        List<Notification> notifications = notificationRepository.findAll();
        notifications.forEach(notification -> {
            notificationDtos.add(populateNotificationDto(notification));
        });
        return notificationDtos;
    }

    public NotificationDto saveNotification (NotificationDto notificationDto)
    {
        Notification notification = populateNotification(notificationDto);
        return populateNotificationDto(notificationRepository.save(notification));
    }

    public boolean deleteNotification(Long id)
    {
        notificationRepository.deleteById(id);
        return true;
    }




    private Notification populateNotification(NotificationDto notificationDto)
    {
        Notification notification = new Notification();

        notification.setId(notificationDto.getId());
        notification.setNotificationChannel(notificationDto.getNotificationChannel());
        notification.setNotificationTitle(notificationDto.getNotificationTitle());
        notification.setNotificationContent(notificationDto.getNotificationContent());
        notification.setPostDate(notificationDto.getPostDate());
        notification.setToAccountId(notificationDto.getToAccountId());

        return notification;

    }

    private NotificationDto populateNotificationDto(Notification notification)
    {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setNotificationChannel(notification.getNotificationChannel());
        notificationDto.setNotificationTitle(notification.getNotificationTitle());
        notificationDto.setNotificationContent(notification.getNotificationContent());
        notificationDto.setPostDate(notification.getPostDate());
        notificationDto.setToAccountId(notification.getToAccountId());

        return notificationDto;

    }

}
