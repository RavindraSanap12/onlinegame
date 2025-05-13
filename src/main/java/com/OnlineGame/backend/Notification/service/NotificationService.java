package com.OnlineGame.backend.Notification.service;

import com.OnlineGame.backend.Notification.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    NotificationDTO createNotification(NotificationDTO notificationDTO);

    NotificationDTO updateNotification(int id, NotificationDTO notificationDTO);

    NotificationDTO getNotificationById(int id);

    List<NotificationDTO> getAllNotifications();

    void deleteNotification(int id);
}
