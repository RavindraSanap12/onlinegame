package com.OnlineGame.backend.AppSetting.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class AppSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String applicationName;
    public String companyAddress;

    public boolean autoApproved;
    public String upiId;
    public String mobileNumber;
    public String whatsappNumber;

    public String email;
    public String telegramLink;

    public String youtubeLink;
    public String playstoreLink;

    public int minPointAdd;
    public int minWithdraw;
    public int maxWithdraw;
    public LocalTime withdrawOpenTime;

    public LocalTime withdrawCloseTime;
    public boolean withdrawalEnabled;

    public String withdrawalContent;

    public String shareLink;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public boolean isAutoApproved() {
        return autoApproved;
    }

    public void setAutoApproved(boolean autoApproved) {
        this.autoApproved = autoApproved;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramLink() {
        return telegramLink;
    }

    public void setTelegramLink(String telegramLink) {
        this.telegramLink = telegramLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getPlaystoreLink() {
        return playstoreLink;
    }

    public void setPlaystoreLink(String playstoreLink) {
        this.playstoreLink = playstoreLink;
    }

    public int getMinPointAdd() {
        return minPointAdd;
    }

    public void setMinPointAdd(int minPointAdd) {
        this.minPointAdd = minPointAdd;
    }

    public int getMinWithdraw() {
        return minWithdraw;
    }

    public void setMinWithdraw(int minWithdraw) {
        this.minWithdraw = minWithdraw;
    }

    public int getMaxWithdraw() {
        return maxWithdraw;
    }

    public void setMaxWithdraw(int maxWithdraw) {
        this.maxWithdraw = maxWithdraw;
    }

    public LocalTime getWithdrawOpenTime() {
        return withdrawOpenTime;
    }

    public void setWithdrawOpenTime(LocalTime withdrawOpenTime) {
        this.withdrawOpenTime = withdrawOpenTime;
    }

    public LocalTime getWithdrawCloseTime() {
        return withdrawCloseTime;
    }

    public void setWithdrawCloseTime(LocalTime withdrawCloseTime) {
        this.withdrawCloseTime = withdrawCloseTime;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getWithdrawalContent() {
        return withdrawalContent;
    }

    public void setWithdrawalContent(String withdrawalContent) {
        this.withdrawalContent = withdrawalContent;
    }

    public boolean isWithdrawalEnabled() {
        return withdrawalEnabled;
    }

    public void setWithdrawalEnabled(boolean withdrawalEnabled) {
        this.withdrawalEnabled = withdrawalEnabled;
    }
}
