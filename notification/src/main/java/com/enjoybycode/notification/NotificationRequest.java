package com.enjoybycode.notification;

public record NotificationRequest(Integer customerId, String customerEmail, String message) {
}
