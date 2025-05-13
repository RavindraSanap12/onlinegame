package com.OnlineGame.backend.ResultDeclare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class MainMarketResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate resultDate;

    private String game;
    private String session;
    private String highlight;

    private String openPanna;
    private String openAnk;
    private String closePanna;
    private String closeAnk;

    private String jodi;

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

    public String getOpenPanna() {
        return openPanna;
    }

    public void setOpenPanna(String openPanna) {
        this.openPanna = openPanna;
    }

    public String getOpenAnk() {
        return openAnk;
    }

    public void setOpenAnk(String openAnk) {
        this.openAnk = openAnk;
    }

    public String getClosePanna() {
        return closePanna;
    }

    public void setClosePanna(String closePanna) {
        this.closePanna = closePanna;
    }

    public String getCloseAnk() {
        return closeAnk;
    }

    public void setCloseAnk(String closeAnk) {
        this.closeAnk = closeAnk;
    }

    public String getJodi() {
        return jodi;
    }

    public void setJodi(String jodi) {
        this.jodi = jodi;
    }

    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }
}
