package com.enjoybycode.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "notification" , url = "${clients.notification.url}")
public interface NotificationClient {

    @PostMapping("api/v1/notifications")
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
