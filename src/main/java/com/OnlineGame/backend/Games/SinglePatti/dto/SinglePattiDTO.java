package com.OnlineGame.backend.Games.SinglePatti.dto;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.User.dto.AddUserDTO;

import java.time.LocalDate;
import java.util.Map;

public class SinglePattiDTO {
    private int id;
    private LocalDate date;
    private String type;
    private AddUserDTO addUserDTO;
    private MainMarketDTO mainMarketDTO;
    private DelhiMarketDTO delhiMarketDTO;
    private StarlineMarketDTO starlineMarketDTO;
    private Map<String, Map<String, Integer>> digitValues;

    private int totalBids;
    private double totalBidAmount;

    // Constructors
    public SinglePattiDTO() {
    }

    public SinglePattiDTO(Map<String, Map<String, Integer>> digitValues) {
        this.digitValues = digitValues;
    }

    // Getters and Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Map<String, Map<String, Integer>> getDigitValues() {
        return digitValues;
    }

    public void setDigitValues(Map<String, Map<String, Integer>> digitValues) {
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