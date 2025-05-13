package com.OnlineGame.backend.Banner.entity;

import jakarta.persistence.*;

@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private byte[] bannerImage;

    private boolean showInApp;
    private boolean showInWeb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShowInApp() {
        return showInApp;
    }

    public void setShowInApp(boolean showInApp) {
        this.showInApp = showInApp;
    }

    public byte[] getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(byte[] bannerImage) {
        this.bannerImage = bannerImage;
    }

    public boolean isShowInWeb() {
        return showInWeb;
    }

    public void setShowInWeb(boolean showInWeb) {
        this.showInWeb = showInWeb;
    }
}
