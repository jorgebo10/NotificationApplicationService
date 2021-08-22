package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationGatewayReq;
import com.manning.application.notification.application.model.NotificationGatewayRsp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationGatewayService {
    public static final String NOTIFICATION_PREFERENCES_URL = "http://localhost:8083/api/notifications/send";

    public NotificationGatewayRsp sendNotification(NotificationGatewayReq NotificationGatewayReq) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationGatewayRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, NotificationGatewayReq,
                NotificationGatewayRsp.class);

        return response.getBody();
    }
}