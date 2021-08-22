package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NotificationPreferencesRsp {
    private String status;
    private String statusDescription;
    private Boolean smsPreferenceFlag;
    private Boolean emailPreferenceFlag;
    private String emailAddress;
    private String phoneNumber;
}