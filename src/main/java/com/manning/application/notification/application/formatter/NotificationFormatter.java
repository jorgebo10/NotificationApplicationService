package com.manning.application.notification.application.formatter;

import com.manning.application.notification.application.entities.Notification;
import com.manning.application.notification.application.entities.NotificationParameter;
import com.manning.application.notification.application.model.NotificationApplicationReq;
import com.manning.application.notification.application.model.NotificationApplicationRsp;
import com.manning.application.notification.application.model.NotificationGatewayReq;
import com.manning.application.notification.application.model.NotificationPreferencesReq;
import com.manning.application.notification.application.model.NotificationPreferencesRsp;
import com.manning.application.notification.application.model.NotificationTemplateFormatterReq;
import com.manning.application.notification.application.model.NotificationTemplateFormatterRsp;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NotificationFormatter {

    public NotificationApplicationRsp toNotificationApplicationRsp(Notification notification, String status) {
        NotificationApplicationRsp notificationApplicationRsp = new NotificationApplicationRsp();
        if (status.equals("SUCCESS")) {
            notificationApplicationRsp.setNotificationReferenceId(notification.getId());
            notificationApplicationRsp.setStatus("SUCCESS");
            notificationApplicationRsp.setStatusDescription("Notification sent");
        } else {
            notificationApplicationRsp.setStatus("ERROR");
            notificationApplicationRsp.setStatusDescription("Notification not sent");
        }
        return notificationApplicationRsp;

    }

    public NotificationPreferencesReq toNotificationPreferencesReq(NotificationApplicationReq notificationRequest) {
        NotificationPreferencesReq notificationPreferencesRequest = new NotificationPreferencesReq();
        notificationPreferencesRequest.setCustomerId(notificationRequest.getCustomerId());
        return notificationPreferencesRequest;
    }

    public NotificationTemplateFormatterReq toNotificationTemplateFormatterReq(
            NotificationApplicationReq notificationApplicationReq,
            NotificationPreferencesRsp notificationPreferencesRsp) {
        NotificationTemplateFormatterReq notificationTemplateRequest = new NotificationTemplateFormatterReq();
        if (notificationPreferencesRsp.getEmailPreferenceFlag() != null) {
            notificationTemplateRequest.setNotificationMode("EMAIL");
        } else if (notificationPreferencesRsp.getSmsPreferenceFlag() != null) {
            notificationTemplateRequest.setNotificationMode("SMS");
        }

        notificationTemplateRequest.setNotificationTemplateName(
                notificationApplicationReq.getNotificationTemplateName());
        notificationTemplateRequest.setNotificationParameters(notificationApplicationReq.getNotificationParameters());
        return notificationTemplateRequest;
    }

    public NotificationGatewayReq totNotificationGatewayReq(NotificationApplicationReq notificationApplicationReq,
                                                            NotificationPreferencesRsp notificationPreferencesRsp,
                                                            NotificationTemplateFormatterRsp notificationTemplateFormatterRsp) {
        NotificationGatewayReq notificationGatewayReq = new NotificationGatewayReq();
        notificationGatewayReq.setCustomerId(notificationApplicationReq.getCustomerId());

        if (notificationTemplateFormatterRsp.getEmailContent() != null) {
            notificationGatewayReq.setNotificationContent(notificationTemplateFormatterRsp.getEmailContent());
            notificationGatewayReq.setNotificationMode("EMAIL");
            notificationGatewayReq.setEmailAddress(notificationPreferencesRsp.getEmailAddress());
            notificationGatewayReq.setEmailSubject(notificationTemplateFormatterRsp.getEmailSubject());
        } else {
            notificationGatewayReq.setNotificationContent(notificationTemplateFormatterRsp.getSmsContent());
            notificationGatewayReq.setNotificationMode("SMS");
            notificationGatewayReq.setPhoneNumber(notificationPreferencesRsp.getPhoneNumber());

        }
        return notificationGatewayReq;
    }

    public Notification toNotification(NotificationApplicationReq notificationApplicationReq) {
        Notification notification = new Notification();
        notification.setNotificationMode(notificationApplicationReq.getNotificationMode());
        notification.setNotificationParameters(notificationApplicationReq.getNotificationParameters().stream()
                .map(notificationParameter -> {
                    NotificationParameter param = new NotificationParameter();
                    param.setNotificationParameterName(notificationParameter.getNotificationParameterName());
                    param.setNotificationParameterValue(notificationParameter.getNotificationParameterValue());
                    return param;
                })
                .collect(Collectors.toList()));
        notification.setCustomerId(notificationApplicationReq.getCustomerId());
        notification.setNotificationTemplateId(notificationApplicationReq.getNotificationTemplateName());
        return notification;
    }
}
