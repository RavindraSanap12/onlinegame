package com.OnlineGame.backend.Games.DoublePatti.entity;

import com.OnlineGame.backend.Games.SinglePatti.entity.DigitValuesConverter;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;

@Entity
public class DoublePatti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;
    private String type;


    private int totalBids;
    private double totalBidAmount;


    @ManyToOne
    private AddUser addUser;

    @ManyToOne
    private MainMarket mainMarket;

    @ManyToOne
    private DelhiMarket delhiMarket;

    @ManyToOne
    private StarlineMarket starlineMarket;

    @Lob
    @Convert(converter = DigitValuesConverter.class)
    private Map<String, Map<String, Integer>> digitValues;


    public DoublePatti() {
    }

    public DoublePatti(Map<String, Map<String, Integer>> digitValues) {
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

    public Map<String, Map<String, Integer>> getDigitValues() {
        return digitValues;
    }

    public void setDigitValues(Map<String, Map<String, Integer>> digitValues) {
        this.digitValues = digitValues;
    }

    // Helper method to get values for a specific digit
    public Map<String, Integer> getValuesForDigit(String digitName) {
        return digitValues.get(digitName);
    }

    // Helper method to add/update values for a digit
    public void setValuesForDigit(String digitName, Map<String, Integer> values) {
        digitValues.put(digitName, values);
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