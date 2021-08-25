package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationPreferencesReq;
import com.manning.application.notification.application.model.NotificationPreferencesRsp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationPreferencesService {
    public static final String NOTIFICATION_PREFERENCES_URL = "http://NotificationPreferencesServices/api/notification/preferences";
    private final RestTemplate restTemplate;

    public NotificationPreferencesRsp getNotificationPreferencesRsp(
            NotificationPreferencesReq notificationPreferencesRequest) {
        ResponseEntity<NotificationPreferencesRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, notificationPreferencesRequest,
                NotificationPreferencesRsp.class);
        return response.getBody();
    }
}
