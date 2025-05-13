package com.OnlineGame.backend.GameRates.dto;


public class GameConfigDTO {
    private int id;

    private String gameType;
    private int gameRate;
    private int minBid;
    private int maxBid;

    private GameRatesDTO gameRates;

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

    public int getGameRate() {
        return gameRate;
    }

    public void setGameRate(int gameRate) {
        this.gameRate = gameRate;
    }

    public int getMinBid() {
        return minBid;
    }

    public void setMinBid(int minBid) {
        this.minBid = minBid;
    }

    public int getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(int maxBid) {
        this.maxBid = maxBid;
    }

    public GameRatesDTO getGameRates() {
        return gameRates;
    }

    public void setGameRates(GameRatesDTO gameRates) {
        this.gameRates = gameRates;
    }
}
