package com.OnlineGame.backend.GameRates.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gamesconfigs")
public class GameConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String gameType;
    private int gameRate;
    private int minBid;
    private int maxBid;

    @ManyToOne
    @JoinColumn(name = "rates_id")
    private GameRates gameRates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getMinBid() {
        return minBid;
    }

    public void setMinBid(int minBid) {
        this.minBid = minBid;
    }

    public int getGameRate() {
        return gameRate;
    }

    public void setGameRate(int gameRate) {
        this.gameRate = gameRate;
    }

    public GameRates getGameRates() {
        return gameRates;
    }

    public void setGameRates(GameRates gameRates) {
        this.gameRates = gameRates;
    }

    public int getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(int maxBid) {
        this.maxBid = maxBid;
    }
}
