package com.OnlineGame.backend.Games.HalfSangam.dto;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.User.dto.AddUserDTO;

import java.time.LocalDate;

public class HalfSangamDTO {
    private int id;
    private AddUserDTO addUserDTO;
    private MainMarketDTO mainMarketDTO;
    private DelhiMarketDTO delhiMarketDTO;
    private StarlineMarketDTO starlineMarketDTO;
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

    public AddUserDTO getAddUserDTO() {
        return addUserDTO;
    }

    public void setAddUserDTO(AddUserDTO addUserDTO) {
        this.addUserDTO = addUserDTO;
    }

    public DelhiMarketDTO getDelhiMarketDTO() {
        return delhiMarketDTO;
    }

    public void setDelhiMarketDTO(DelhiMarketDTO delhiMarketDTO) {
        this.delhiMarketDTO = delhiMarketDTO;
    }

    public MainMarketDTO getMainMarketDTO() {
        return mainMarketDTO;
    }

    public void setMainMarketDTO(MainMarketDTO mainMarketDTO) {
        this.mainMarketDTO = mainMarketDTO;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCloseDigit() {
        return closeDigit;
    }

    public void setCloseDigit(int closeDigit) {
        this.closeDigit = closeDigit;
    }

    public int getOpenPanna() {
        return openPanna;
    }

    public void setOpenPanna(int openPanna) {
        this.openPanna = openPanna;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
