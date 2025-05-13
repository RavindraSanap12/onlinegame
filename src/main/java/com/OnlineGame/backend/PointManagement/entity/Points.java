package com.OnlineGame.backend.PointManagement.entity;

import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AddUser addUser;

    private double amount;
    private String transactionNo;
    private LocalDate transactionDate;
    private String remarks;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
