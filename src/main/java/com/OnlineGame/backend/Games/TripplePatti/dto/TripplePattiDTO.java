package com.OnlineGame.backend.Games.TripplePatti.dto;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.User.dto.AddUserDTO;

import java.time.LocalDate;

public class TripplePattiDTO {

    private int id;

    private LocalDate date;
    private String type;

    private AddUserDTO addUserDTO;
    private MainMarketDTO mainMarketDTO;
    private DelhiMarketDTO delhiMarketDTO;
    private StarlineMarketDTO starlineMarketDTO;

    private int zero3;
    private int one3;
    private int two3;
    private int three3;
    private int four3;
    private int five3;
    private int six3;
    private int seven3;
    private int eight3;
    private int nine3;

    private int totalBids;
    private double totalBidAmount;

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

    public int getZero3() {
        return zero3;
    }

    public void setZero3(int zero3) {
        this.zero3 = zero3;
    }

    public int getOne3() {
        return one3;
    }

    public void setOne3(int one3) {
        this.one3 = one3;
    }

    public int getTwo3() {
        return two3;
    }

    public void setTwo3(int two3) {
        this.two3 = two3;
    }

    public int getThree3() {
        return three3;
    }

    public void setThree3(int three3) {
        this.three3 = three3;
    }

    public int getFour3() {
        return four3;
    }

    public void setFour3(int four3) {
        this.four3 = four3;
    }

    public int getFive3() {
        return five3;
    }

    public void setFive3(int five3) {
        this.five3 = five3;
    }

    public int getSix3() {
        return six3;
    }

    public void setSix3(int six3) {
        this.six3 = six3;
    }

    public int getSeven3() {
        return seven3;
    }

    public void setSeven3(int seven3) {
        this.seven3 = seven3;
    }

    public int getEight3() {
        return eight3;
    }

    public void setEight3(int eight3) {
        this.eight3 = eight3;
    }

    public int getNine3() {
        return nine3;
    }

    public void setNine3(int nine3) {
        this.nine3 = nine3;
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
