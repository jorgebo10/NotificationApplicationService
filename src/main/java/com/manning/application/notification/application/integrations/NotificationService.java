package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.entities.Notification;
import com.manning.application.notification.application.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification) {
        return this.notificationRepository.save(notification);
    }
}
