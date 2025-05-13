package com.OnlineGame.backend.GameRates.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gamesrates")
public class GameRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "gameRates", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameConfig> gameConfigs;

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

    public List<GameConfig> getGameConfigs() {
        return gameConfigs;
    }

    public void setGameConfigs(List<GameConfig> gameConfigs) {
        this.gameConfigs = gameConfigs;
    }
}
