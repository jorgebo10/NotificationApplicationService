package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationTemplateFormatterReq;
import com.manning.application.notification.application.model.NotificationTemplateFormatterRsp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationTemplateFormatterService {

    public static final String NOTIFICATION_PREFERENCES_URL = "http://localhost:8082/api/notifications/templates";

    public NotificationTemplateFormatterRsp getNotificationTemplateFormatterRsp(
            NotificationTemplateFormatterReq notificationTemplateRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationTemplateFormatterRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, notificationTemplateRequest,
                NotificationTemplateFormatterRsp.class);
        return response.getBody();
    }
}
