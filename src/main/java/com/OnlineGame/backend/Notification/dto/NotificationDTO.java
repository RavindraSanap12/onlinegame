package com.OnlineGame.backend.Notification.dto;

import jakarta.persistence.*;

public class NotificationDTO {

    private int id;

    private String title;
    private String description;
    private boolean showInApp;
    private boolean showInWeb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowInApp() {
        return showInApp;
    }

    public void setShowInApp(boolean showInApp) {
        this.showInApp = showInApp;
    }

    public boolean isShowInWeb() {
        return showInWeb;
    }

    public void setShowInWeb(boolean showInWeb) {
        this.showInWeb = showInWeb;
    }
}