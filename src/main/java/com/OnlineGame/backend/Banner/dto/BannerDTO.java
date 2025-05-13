package com.OnlineGame.backend.Banner.dto;

public class BannerDTO {

    private int id;
    private byte[] bannerImage;
    private boolean showInApp;
    private boolean showInWeb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(byte[] bannerImage) {
        this.bannerImage = bannerImage;
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
