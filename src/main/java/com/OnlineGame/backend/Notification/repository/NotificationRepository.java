package com.OnlineGame.backend.Notification.repository;

import com.OnlineGame.backend.Notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
