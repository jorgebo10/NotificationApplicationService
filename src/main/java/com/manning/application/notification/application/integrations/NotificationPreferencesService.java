package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationPreferencesReq;
import com.manning.application.notification.application.model.NotificationPreferencesRsp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationPreferencesService {
    public static final String NOTIFICATION_PREFERENCES_URL = "http://localhost:8081/api/notification/preferences";

    public NotificationPreferencesRsp getNotificationPreferencesRsp(
            NotificationPreferencesReq notificationPreferencesRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationPreferencesRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, notificationPreferencesRequest,
                NotificationPreferencesRsp.class);
        return response.getBody();
    }
}
