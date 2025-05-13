package com.OnlineGame.backend.Games.SPDPTP.dto;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.User.dto.AddUserDTO;

import java.time.LocalDate;

public class SPDPTPDTO {
    private int id;
    private AddUserDTO addUserDTO;
    private MainMarketDTO mainMarketDTO;
    private DelhiMarketDTO delhiMarketDTO;
    private StarlineMarketDTO starlineMarketDTO;
    private LocalDate date;
    private int digit;
    private int points;
    private String gameType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddUserDTO getAddUserDTO() {
        return addUserDTO;
    }

    public void setAddUserDTO(AddUserDTO addUserDTO) {
        this.addUserDTO = addUserDTO;
    }

    public MainMarketDTO getMainMarketDTO() {
        return mainMarketDTO;
    }

    public void setMainMarketDTO(MainMarketDTO mainMarketDTO) {
        this.mainMarketDTO = mainMarketDTO;
    }

    public DelhiMarketDTO getDelhiMarketDTO() {
        return delhiMarketDTO;
    }

    public void setDelhiMarketDTO(DelhiMarketDTO delhiMarketDTO) {
        this.delhiMarketDTO = delhiMarketDTO;
    }

    public StarlineMarketDTO getStarlineMarketDTO() {
        return starlineMarketDTO;
    }

    public void setStarlineMarketDTO(StarlineMarketDTO starlineMarketDTO) {
        this.starlineMarketDTO = starlineMarketDTO;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
