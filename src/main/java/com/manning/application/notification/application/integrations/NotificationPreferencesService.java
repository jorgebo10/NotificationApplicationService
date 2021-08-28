package com.manning.application.notification.application.integrations;

import com.manning.application.notification.application.model.NotificationPreferencesReq;
import com.manning.application.notification.application.model.NotificationPreferencesRsp;
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
public class NotificationPreferencesService {
    public static final String NOTIFICATION_PREFERENCES_URL =
            "http://NotificationPreferencesServices/api/notification/preferences";
    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "preferencesService", fallbackMethod = "buildFallbackPreference")
    @Bulkhead(name = "preferencesService", fallbackMethod = "buildFallbackPreference")
    @Retry(name = "preferencesService", fallbackMethod = "buildFallbackPreferences")
    @RateLimiter(name = "preferencesService", fallbackMethod = "buildFallbackPreferences")
    public NotificationPreferencesRsp getNotificationPreferencesRsp(
            NotificationPreferencesReq notificationPreferencesRequest) {
        ResponseEntity<NotificationPreferencesRsp> response
                = restTemplate.postForEntity(NOTIFICATION_PREFERENCES_URL, notificationPreferencesRequest,
                NotificationPreferencesRsp.class);
        return response.getBody();
    }

    private NotificationPreferencesRsp buildFallbackPreference(NotificationPreferencesReq req, Throwable t) {
        NotificationPreferencesRsp notificationPreferencesRsp = new NotificationPreferencesRsp();
        notificationPreferencesRsp.setStatus("ERROR");
        notificationPreferencesRsp.setStatusDescription("Sorry no preferences available");
        return notificationPreferencesRsp;
    }
}
