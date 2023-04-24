package com.enjoybycode.notification;

import java.time.LocalDateTime;

public record NotificationResponse(Integer customerId, String message, LocalDateTime sentAt, boolean sendingIsSuccessful) {
}
