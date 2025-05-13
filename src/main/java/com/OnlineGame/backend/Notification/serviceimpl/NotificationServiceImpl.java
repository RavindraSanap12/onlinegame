package com.OnlineGame.backend.Notification.serviceimpl;

import com.OnlineGame.backend.Notification.dto.NotificationDTO;
import com.OnlineGame.backend.Notification.entity.Notification;
import com.OnlineGame.backend.Notification.repository.NotificationRepository;
import com.OnlineGame.backend.Notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        // Create a new Notification entity from DTO
        Notification notification = new Notification();
        notification.setTitle(notificationDTO.getTitle());
        notification.setDescription(notificationDTO.getDescription());
        notification.setShowInApp(notificationDTO.isShowInApp());
        notification.setShowInWeb(notificationDTO.isShowInWeb());

        // Save the entity to the database
        Notification savedNotification = notificationRepository.save(notification);

        // Return the saved Notification as DTO
        return mapToDTO(savedNotification);
    }

    @Override
    public NotificationDTO updateNotification(int id, NotificationDTO notificationDTO) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);

        if (existingNotification.isPresent()) {
            Notification notification = existingNotification.get();
            notification.setTitle(notificationDTO.getTitle());
            notification.setDescription(notificationDTO.getDescription());
            notification.setShowInApp(notificationDTO.isShowInApp());
            notification.setShowInWeb(notificationDTO.isShowInWeb());

            // Save the updated notification
            Notification updatedNotification = notificationRepository.save(notification);

            return mapToDTO(updatedNotification);
        } else {
            // Handle case where notification is not found (optional)
            return null;
        }
    }

    @Override
    public NotificationDTO getNotificationById(int id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);

        return notificationOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();

        // Convert list of entities to list of DTOs
        return notifications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(int id) {
        notificationRepository.deleteById(id);
    }

    // Helper method to map Notification entity to DTO
    private NotificationDTO mapToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setTitle(notification.getTitle());
        notificationDTO.setDescription(notification.getDescription());
        notificationDTO.setShowInApp(notification.isShowInApp());
        notificationDTO.setShowInWeb(notification.isShowInWeb());
        return notificationDTO;
    }
}
