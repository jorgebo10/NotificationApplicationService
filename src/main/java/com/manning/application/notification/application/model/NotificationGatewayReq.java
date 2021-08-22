package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NotificationGatewayReq {
    private String customerId;
    private String notificationContent;
    private String emailAddress;
    private String emailSubject;
    private String phoneNumber;
    private String notificationMode;
}
