package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationGatewayReq;
import com.manning.application.notification.application.model.NotificationGatewayRsp;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationGatewayService {
    public static final String NOTIFICATION_PREFERENCES_URL =
            "http://NotificationGatewayService/api/notifications/gateway/send";
    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "gatewayService")
    public NotificationGatewayRsp sendNotification(NotificationGatewayReq NotificationGatewayReq) {
        ResponseEntity<NotificationGatewayRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, NotificationGatewayReq,
                NotificationGatewayRsp.class);

        return response.getBody();
    }
}