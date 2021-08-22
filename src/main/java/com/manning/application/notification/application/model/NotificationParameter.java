package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Valid
public final class NotificationParameter {
    @NotEmpty
    private String notificationParameterName;
    @NotEmpty
    private String notificationParameterValue;
}
