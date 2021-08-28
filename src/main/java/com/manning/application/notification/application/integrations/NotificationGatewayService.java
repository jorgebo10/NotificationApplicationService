package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationGatewayReq;
import com.manning.application.notification.application.model.NotificationGatewayRsp;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationGatewayService {
    public static final String NOTIFICATION_PREFERENCES_URL =
            "http://NotificationGatewayServices/api/notifications/gateway/send";
    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "gatewayService", fallbackMethod = "buildFallbackGateway")
    @Bulkhead(name = "gatewayService", fallbackMethod = "buildFallbackGateway")
    @Retry(name = "gatewayService", fallbackMethod = "buildFallbackGateway")
    @RateLimiter(name = "gatewayService", fallbackMethod = "buildFallbackGateway")
    public NotificationGatewayRsp sendNotification(NotificationGatewayReq NotificationGatewayReq) {
        ResponseEntity<NotificationGatewayRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, NotificationGatewayReq,
                NotificationGatewayRsp.class);

        return response.getBody();
    }

    public NotificationGatewayRsp buildFallbackGateway(NotificationGatewayReq req, Throwable t) {
        NotificationGatewayRsp notificationGatewayRsp = new NotificationGatewayRsp();
        notificationGatewayRsp.setStatus("ERROR");
        notificationGatewayRsp.setStatusDescription("Sorry no gateway available");
        return notificationGatewayRsp;
    }
}