package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationTemplateFormatterReq;
import com.manning.application.notification.application.model.NotificationTemplateFormatterRsp;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationTemplateFormatterService {
    public static final String NOTIFICATION_PREFERENCES_URL =
            "http://NotificationTemplatesServices/api/notifications/templates";
    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "templateService")
    public NotificationTemplateFormatterRsp getNotificationTemplateFormatterRsp(
            NotificationTemplateFormatterReq notificationTemplateRequest) {
        ResponseEntity<NotificationTemplateFormatterRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, notificationTemplateRequest,
                NotificationTemplateFormatterRsp.class);
        return response.getBody();
    }
}
