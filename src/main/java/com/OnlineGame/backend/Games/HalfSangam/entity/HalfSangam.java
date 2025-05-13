package com.OnlineGame.backend.Games.HalfSangam.entity;

import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HalfSangam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AddUser addUser;

    @ManyToOne
    private MainMarket mainMarket;

    @ManyToOne
    private DelhiMarket delhiMarket;

    @ManyToOne
    private StarlineMarket starlineMarket;

    private LocalDate date;
    private String type;

    private int openPanna;
    private int closeDigit;
    private int points;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddUser getAddUser() {
        return addUser;
    }

    public void setAddUser(AddUser addUser) {
        this.addUser = addUser;
    }

    public MainMarket getMainMarket() {
        return mainMarket;
    }

    public void setMainMarket(MainMarket mainMarket) {
        this.mainMarket = mainMarket;
    }

    public DelhiMarket getDelhiMarket() {
        return delhiMarket;
    }

    public void setDelhiMarket(DelhiMarket delhiMarket) {
        this.delhiMarket = delhiMarket;
    }

    public StarlineMarket getStarlineMarket() {
        return starlineMarket;
    }

    public void setStarlineMarket(StarlineMarket starlineMarket) {
        this.starlineMarket = starlineMarket;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOpenPanna() {
        return openPanna;
    }

    public void setOpenPanna(int openPanna) {
        this.openPanna = openPanna;
    }

    public int getCloseDigit() {
        return closeDigit;
    }

    public void setCloseDigit(int closeDigit) {
        this.closeDigit = closeDigit;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
