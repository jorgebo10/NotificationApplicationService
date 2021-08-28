package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class NotificationPreferencesRsp {
    private String status;
    private String statusDescription;
    private Boolean smsPreferenceFlag;
    private Boolean emailPreferenceFlag;
    private String emailAddress;
    private String phoneNumber;
}