package com.enjoybycode.notification;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/notifications")
public record NotificationController(NotificationService notificationService) {

    @PostMapping
    NotificationResponse sendNotification(@RequestBody NotificationRequest request) {
        boolean successful = notificationService.sendingMessageWasSuccessful(request);
        return new NotificationResponse(request.customerId(), request.message(), LocalDateTime.now(), successful);
    }

}
