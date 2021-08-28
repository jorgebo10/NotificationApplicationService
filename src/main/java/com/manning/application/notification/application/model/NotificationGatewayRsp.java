package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class NotificationGatewayRsp {
    private String status;
    private String statusDescription;
}
