package com.manning.application.notification.application.model;

import lombok.Data;

@Data
public final class NotificationTemplateFormatterRsp {
    private String status;
    private String statusDescription;
    private String emailContent;
    private String smsContent;
    private String emailSubject;
}
