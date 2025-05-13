package com.OnlineGame.backend.PointManagement.dto;

import com.OnlineGame.backend.User.dto.AddUserDTO;

import java.time.LocalDate;

public class PointsDTO {

    private int id;

    private AddUserDTO addUserDTO;
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

    public AddUserDTO getAddUserDTO() {
        return addUserDTO;
    }

    public void setAddUserDTO(AddUserDTO addUserDTO) {
        this.addUserDTO = addUserDTO;
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
