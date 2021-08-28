package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public final class NotificationTemplateFormatterReq {
    private List<NotificationParameter> notificationParameters;
    private String notificationTemplateName;
    private String notificationMode;
}
