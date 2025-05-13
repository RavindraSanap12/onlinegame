package com.OnlineGame.backend.Games.Jodi.entity;

import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

@Entity
public class Jodi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;


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


    @ElementCollection
    @CollectionTable(name = "digit_values", joinColumns = @JoinColumn(name = "digit_record_id"))
    @MapKeyColumn(name = "digit_code")
    @Column(name = "value")
    private Map<String, Integer> digitValues = new HashMap<>();

    // Constructors
    public Jodi() {
    }

    public Jodi(LocalDate date, Map<String, Integer> digitValues) {
        this.date = date;
        this.digitValues = digitValues;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MainMarket getMainMarket() {
        return mainMarket;
    }

    public void setMainMarket(MainMarket mainMarket) {
        this.mainMarket = mainMarket;
    }

    public AddUser getAddUser() {
        return addUser;
    }

    public void setAddUser(AddUser addUser) {
        this.addUser = addUser;
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

    public Map<String, Integer> getDigitValues() {
        return digitValues;
    }

    public void setDigitValues(Map<String, Integer> digitValues) {
        this.digitValues = digitValues;
    }

    // Helper method to add/update a single digit value
    public void setDigitValue(String digitCode, Integer value) {
        this.digitValues.put(digitCode, value);
    }

    // Helper method to get a single digit value
    public Integer getDigitValue(String digitCode) {
        return this.digitValues.get(digitCode);
    }

    @Override
    public String toString() {
        return "DigitRecord{" +
                "id=" + id +
                ", date=" + date +
                ", digitValues=" + digitValues +
                '}';
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