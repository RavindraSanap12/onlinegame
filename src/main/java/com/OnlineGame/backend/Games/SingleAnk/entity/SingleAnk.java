package com.OnlineGame.backend.Games.SingleAnk.entity;


import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SingleAnk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AddUser addUser;

    @ManyToOne
    private MainMarket mainMarket;

    @ManyToOne
    private DelhiMarket delhiMarket;

    @ManyToOne
    private StarlineMarket starlineMarket;

    private LocalDate date;
    private String type;

    private int zero;
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private int six;
    private int seven;
    private int eight;
    private int nine;

    private int totalBids;
    private double totalBidAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getZero() {
        return zero;
    }

    public void setZero(int zero) {
        this.zero = zero;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public int getSeven() {
        return seven;
    }

    public void setSeven(int seven) {
        this.seven = seven;
    }

    public int getEight() {
        return eight;
    }

    public void setEight(int eight) {
        this.eight = eight;
    }

    public int getNine() {
        return nine;
    }

    public void setNine(int nine) {
        this.nine = nine;
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
