package com.OnlineGame.backend.ResultDeclare.dto;

import java.time.LocalDate;

public class DelhiMarketResultDTO {
    private int id;
    private LocalDate resultDate;
    private String game;
    private String highlight;
    private String jodi;
    private boolean sendNotification;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getResultDate() {
        return resultDate;
    }

    public void setResultDate(LocalDate resultDate) {
        this.resultDate = resultDate;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getJodi() {
        return jodi;
    }

    public void setJodi(String jodi) {
        this.jodi = jodi;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
