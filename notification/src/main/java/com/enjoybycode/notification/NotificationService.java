package com.enjoybycode.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record NotificationService(NotificationRepository notificationRepository) {

    boolean sendingMessageWasSuccessful(NotificationRequest request) {
        notificationRepository
                .save(Notification.builder()
                        .toCustomerEmail(request.customerEmail())
                        .toCustomerId(request.customerId())
                        .message(request.message()).build());

        log.info("notification send request for customer email: {}", request.customerEmail());
        return true;
    }
}
