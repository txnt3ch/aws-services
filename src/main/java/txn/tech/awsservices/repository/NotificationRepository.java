package txn.tech.awsservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import txn.tech.awsservices.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
