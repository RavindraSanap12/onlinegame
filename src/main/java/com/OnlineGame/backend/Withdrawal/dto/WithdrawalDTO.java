package com.OnlineGame.backend.Withdrawal.dto;

import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.entity.AddUser;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class WithdrawalDTO {
    private int id;

    private LocalDate date;

    private AddUserDTO addUserDTO;

    private String paymentMethod;
    private double Amount;

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

    public AddUserDTO getAddUserDTO() {
        return addUserDTO;
    }

    public void setAddUserDTO(AddUserDTO addUserDTO) {
        this.addUserDTO = addUserDTO;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }
}
