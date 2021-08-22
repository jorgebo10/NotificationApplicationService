package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NotificationTemplateFormatterRsp {
    private String status;
    private String statusDescription;
    private String emailContent;
    private String smsContent;
    private String emailSubject;
}
