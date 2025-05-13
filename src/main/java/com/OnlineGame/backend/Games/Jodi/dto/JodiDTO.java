package com.OnlineGame.backend.Games.Jodi.dto;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.User.dto.AddUserDTO;

import java.time.LocalDate;
import java.util.Map;

public class JodiDTO {
    private int id;
    private LocalDate date;
    private AddUserDTO addUserDTO;
    private MainMarketDTO mainMarketDTO;
    private DelhiMarketDTO delhiMarketDTO;
    private StarlineMarketDTO starlineMarketDTO;
    private Map<String, Integer> digitValues;

    private int totalBids;
    private double totalBidAmount;


    // Constructors
    public JodiDTO() {
    }

    public JodiDTO(LocalDate date, Map<String, Integer> digitValues) {
        this.date = date;
        this.digitValues = digitValues;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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

    public Map<String, Integer> getDigitValues() {
        return digitValues;
    }

    public void setDigitValues(Map<String, Integer> digitValues) {
        this.digitValues = digitValues;
    }

    public int getTotalBids() {
        return totalBids;
    }

    public void setTotalBids(int totalBids) {
        this.totalBids = totalBids;
    }

    public double getTotalBidAmount() {
        return totalBidAmount;
    }

    public void setTotalBidAmount(double totalBidAmount) {
        this.totalBidAmount = totalBidAmount;
    }
}