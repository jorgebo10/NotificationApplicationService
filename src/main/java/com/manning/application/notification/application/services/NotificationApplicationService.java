package com.manning.application.notification.application.services;

import com.manning.application.notification.application.entities.Notification;
import com.manning.application.notification.application.formatter.NotificationFormatter;
import com.manning.application.notification.application.integrations.NotificationGatewayService;
import com.manning.application.notification.application.integrations.NotificationPreferencesService;
import com.manning.application.notification.application.integrations.NotificationService;
import com.manning.application.notification.application.integrations.NotificationTemplateFormatterService;
import com.manning.application.notification.application.model.NotificationApplicationReq;
import com.manning.application.notification.application.model.NotificationApplicationRsp;
import com.manning.application.notification.application.model.NotificationGatewayReq;
import com.manning.application.notification.application.model.NotificationGatewayRsp;
import com.manning.application.notification.application.model.NotificationPreferencesReq;
import com.manning.application.notification.application.model.NotificationPreferencesRsp;
import com.manning.application.notification.application.model.NotificationTemplateFormatterReq;
import com.manning.application.notification.application.model.NotificationTemplateFormatterRsp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationApplicationService {
    private final NotificationFormatter notificationFormatter;
    private final NotificationPreferencesService notificationPreferencesService;
    private final NotificationTemplateFormatterService notificationTemplateFormatterService;
    private final NotificationGatewayService notificationGatewayService;
    private final NotificationService notificationService;

    public NotificationApplicationRsp create(NotificationApplicationReq notificationApplicationReq) {

        //Save current notification in as we are going to produce a foreign state mutation later
        Notification notification = notificationFormatter.toNotification(notificationApplicationReq);
        notificationService.save(notification);
        if (notification.getId() == null) {
            NotificationApplicationRsp notificationApplicationRsp = new NotificationApplicationRsp();
            notificationApplicationRsp.setStatus("ERROR");
            notificationApplicationRsp.setStatusDescription("CANNOT SEND NOTIFICATION");
        }

        //Get user preferences, email or sms plus address or phone
        NotificationPreferencesReq notificationPreferencesReq =
                notificationFormatter.toNotificationPreferencesReq(notificationApplicationReq);
        NotificationPreferencesRsp notificationPreferencesRsp =
                notificationPreferencesService.getNotificationPreferencesRsp(notificationPreferencesReq);

        //format the provided template according to user preferences, email or sms
        NotificationTemplateFormatterReq notificationTemplateFormatterReq =
                notificationFormatter.toNotificationTemplateFormatterReq(notificationApplicationReq,
                        notificationPreferencesRsp);
        NotificationTemplateFormatterRsp notificationTemplateFormatterRsp =
                notificationTemplateFormatterService.getNotificationTemplateFormatterRsp(
                        notificationTemplateFormatterReq);

        //Return result from notification gateway
        NotificationGatewayReq notificationGatewayReq =
                notificationFormatter.totNotificationGatewayReq(notificationApplicationReq, notificationPreferencesRsp,
                        notificationTemplateFormatterRsp);
        NotificationGatewayRsp notificationGatewayRsp =
                notificationGatewayService.sendNotification(notificationGatewayReq);
        return notificationFormatter.toNotificationApplicationRsp(notification, notificationGatewayRsp.getStatus());
    }
}
