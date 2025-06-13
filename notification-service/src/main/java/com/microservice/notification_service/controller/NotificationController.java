package com.microservice.notification_service.controller;

import com.microservice.notification_service.service.OneSignalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private final OneSignalService oneSignalService;

    public NotificationController(OneSignalService oneSignalService) {
        this.oneSignalService = oneSignalService;
    }

    @PostMapping("/send-notification")
    public String sendNotification(@RequestParam String message, @RequestParam String playerId) {
        return oneSignalService.sendPushNotification(message, playerId);
    }
}
