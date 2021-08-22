package com.manning.application.notification.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.util.List;

@Getter
@Setter
public final class NotificationApplicationReq {
    @NotEmpty
    private String customerId;
    @NotEmpty
    private String notificationTemplateName;
    @Pattern(regexp = "^(EMAIL|SMS)$")
    private String notificationMode;
    @NotEmpty
    @Valid
    private List<NotificationParameter> notificationParameters;
}
