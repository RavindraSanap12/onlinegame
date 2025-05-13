package com.OnlineGame.backend.config.dto;

import lombok.Builder;

@Builder
public class UserResponse {
    private int id;
    private String name;
    private String mobileNo;
    private boolean customWithdraw;
    private boolean customRate;
    private boolean customClose;
    private boolean status;

    public UserResponse() {
    }

    public UserResponse(int id, String name, String mobileNo, boolean customWithdraw, boolean customRate, boolean customClose, boolean status) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.customWithdraw = customWithdraw;
        this.customRate = customRate;
        this.customClose = customClose;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCustomWithdraw() {
        return customWithdraw;
    }

    public void setCustomWithdraw(boolean customWithdraw) {
        this.customWithdraw = customWithdraw;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean isCustomRate() {
        return customRate;
    }

    public void setCustomRate(boolean customRate) {
        this.customRate = customRate;
    }

    public boolean isCustomClose() {
        return customClose;
    }

    public void setCustomClose(boolean customClose) {
        this.customClose = customClose;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
