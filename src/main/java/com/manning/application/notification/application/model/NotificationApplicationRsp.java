package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NotificationApplicationRsp {
    private String status;
    private String statusDescription;
    private Long notificationReferenceId;
}
