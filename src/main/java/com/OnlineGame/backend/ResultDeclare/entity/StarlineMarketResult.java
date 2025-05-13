package com.OnlineGame.backend.ResultDeclare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class StarlineMarketResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate resultDate;

    private String game;

    private String session;

    private String highlight;

    private String panna;

    private String ank;

    private boolean sendNotification;

    // Getters and Setters

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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getPanna() {
        return panna;
    }

    public void setPanna(String panna) {
        this.panna = panna;
    }

    public String getAnk() {
        return ank;
    }

    public void setAnk(String ank) {
        this.ank = ank;
    }

    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
