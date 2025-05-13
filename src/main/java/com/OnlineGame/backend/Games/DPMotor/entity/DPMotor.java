package com.OnlineGame.backend.Games.DPMotor.entity;

import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DPMotor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;

    @ManyToOne
    private AddUser addUser;

    @ManyToOne
    private MainMarket mainMarket;

    @ManyToOne
    private DelhiMarket delhiMarket;

    @ManyToOne
    private StarlineMarket starlineMarket;

    private int digit;
    private int points;
    private String gameType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
