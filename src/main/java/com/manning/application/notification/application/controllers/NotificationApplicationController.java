package com.manning.application.notification.application.controllers;

import com.manning.application.notification.application.model.NotificationApplicationReq;
import com.manning.application.notification.application.model.NotificationApplicationRsp;
import com.manning.application.notification.application.services.NotificationApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationApplicationController {
    private final NotificationApplicationService notificationApplicationService;

    @PostMapping
    public NotificationApplicationRsp create(
            @RequestBody @Valid NotificationApplicationReq notificationApplicationReq) {
        return notificationApplicationService.create(notificationApplicationReq);
    }

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "UP";
    }
}
