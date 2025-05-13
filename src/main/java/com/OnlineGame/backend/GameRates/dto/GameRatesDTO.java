package com.OnlineGame.backend.GameRates.dto;


import java.util.List;

public class GameRatesDTO {
    private int id;

    private String name;

    private List<GameConfigDTO> gameConfigs;

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

    public List<GameConfigDTO> getGameConfigs() {
        return gameConfigs;
    }

    public void setGameConfigs(List<GameConfigDTO> gameConfigs) {
        this.gameConfigs = gameConfigs;
    }
}
