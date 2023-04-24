package com.enjoybycode.clients.notification;

public record NotificationRequest(Integer customerId, String customerEmail, String message) {
}
