package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Valid
@ToString
public final class NotificationParameter {
    @NotEmpty
    private String notificationParameterName;
    @NotEmpty
    private String notificationParameterValue;
}
