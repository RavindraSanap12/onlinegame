package com.OnlineGame.backend.PointManagement.controller;

import com.OnlineGame.backend.PointManagement.dto.PointsDTO;
import com.OnlineGame.backend.PointManagement.entity.TransactionHistory;
import com.OnlineGame.backend.PointManagement.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "*")
public class PointsController {

    @Autowired
    private PointsService pointsService;

    // Add or update points
    @PostMapping("/add")
    public ResponseEntity<PointsDTO> addOrUpdatePoints(@RequestBody PointsDTO pointsDTO) {
        try {
            PointsDTO saved = pointsService.addOrUpdatePoints(pointsDTO);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/deduct")
    public ResponseEntity<PointsDTO> deductPoints(@RequestBody PointsDTO pointsDTO) {
        PointsDTO deducted = pointsService.deductPoints(pointsDTO);
        return ResponseEntity.ok(deducted);
    }



    // Get all points
    @GetMapping("/all")
    public ResponseEntity<List<PointsDTO>> getAllPoints() {
        List<PointsDTO> pointsList = pointsService.getAllPoints();
        return ResponseEntity.ok(pointsList);
    }

    // Get points for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PointsDTO>> getPointsByUserId(@PathVariable int userId) {
        List<PointsDTO> userPoints = pointsService.getAmountByUserId(userId);
        return ResponseEntity.ok(userPoints);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TransactionHistory>> getAllTransactionHistory() {
        List<TransactionHistory> historyList = pointsService.getAllTransactionHistory();
        return ResponseEntity.ok(historyList);
    }

    @GetMapping("/credit")
    public List<TransactionHistory> getCreditTransactions() {
        return pointsService.getTransactionsByCredit();
    }

    // Endpoint to get transactions with entry type "debit"
    @GetMapping("/debit")
    public List<TransactionHistory> getDebitTransactions() {
        return pointsService.getTransactionsByDebit();
    }
}
