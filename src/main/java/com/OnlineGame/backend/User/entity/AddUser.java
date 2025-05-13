package com.OnlineGame.backend.User.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "users")
public class AddUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String mobileNo;

    private LocalDate registerDate;
    private LocalTime registerTime;
    private String password;

    private double amount;

    private boolean customWithdraw;
    private boolean customRate;
    private boolean customClose;
    private boolean status;



    // Getters and Setters
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getCustomWithdraw() {
        return customWithdraw;
    }

    public void setCustomWithdraw(boolean customWithdraw) {
        this.customWithdraw = customWithdraw;
    }

    public boolean getCustomRate() {
        return customRate;
    }

    public void setCustomRate(boolean customRate) {
        this.customRate = customRate;
    }

    public boolean getCustomClose() {
        return customClose;
    }

    public void setCustomClose(boolean customClose) {
        this.customClose = customClose;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalTime registerTime) {
        this.registerTime = registerTime;
    }
}
