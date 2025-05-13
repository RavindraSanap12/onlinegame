package com.OnlineGame.backend.Withdrawal.controller;

import com.OnlineGame.backend.Withdrawal.dto.WithdrawalDTO;
import com.OnlineGame.backend.Withdrawal.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/withdrawals")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @Autowired
    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PostMapping
    public ResponseEntity<WithdrawalDTO> createWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) {
        try {
            WithdrawalDTO createdWithdrawal = withdrawalService.saveWithdrawal(withdrawalDTO);
            return new ResponseEntity<>(createdWithdrawal, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WithdrawalDTO> getWithdrawalById(@PathVariable int id) {
        try {
            WithdrawalDTO withdrawalDTO = withdrawalService.findById(id);
            return new ResponseEntity<>(withdrawalDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WithdrawalDTO>> getWithdrawalsByUser(@PathVariable int userId) {
        List<WithdrawalDTO> withdrawals = withdrawalService.findByUser(userId);
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WithdrawalDTO>> getAllWithdrawals() {
        List<WithdrawalDTO> withdrawals = withdrawalService.findAllWithdrawal();
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WithdrawalDTO> updateWithdrawal(
            @PathVariable int id,
            @RequestBody WithdrawalDTO withdrawalDTO) {
        try {
            WithdrawalDTO updatedWithdrawal = withdrawalService.updateWithdrawal(id, withdrawalDTO);
            return new ResponseEntity<>(updatedWithdrawal, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable int id) {
        try {
            withdrawalService.deleteWithdrawal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}