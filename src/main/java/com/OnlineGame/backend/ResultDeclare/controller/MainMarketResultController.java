package com.OnlineGame.backend.ResultDeclare.controller;

import com.OnlineGame.backend.ResultDeclare.dto.MainMarketResultDTO;
import com.OnlineGame.backend.ResultDeclare.service.MainMarketResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main-market-results")
@CrossOrigin(value = "*")
public class MainMarketResultController {

    @Autowired
    private MainMarketResultService resultService;

    // Add new result
    @PostMapping
    public ResponseEntity<MainMarketResultDTO> addResult(@RequestBody MainMarketResultDTO dto) {
        MainMarketResultDTO savedResult = resultService.addResult(dto);
        return ResponseEntity.ok(savedResult);
    }

    // Update existing result
    @PutMapping("/{id}")
    public ResponseEntity<MainMarketResultDTO> updateResult(
            @PathVariable int id,
            @RequestBody MainMarketResultDTO dto) {
        MainMarketResultDTO updated = resultService.updateResult(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Get result by ID
    @GetMapping("/{id}")
    public ResponseEntity<MainMarketResultDTO> getResultById(@PathVariable int id) {
        MainMarketResultDTO result = resultService.getResultById(id);
        return ResponseEntity.ok(result);
    }

    // Get all results
    @GetMapping
    public ResponseEntity<List<MainMarketResultDTO>> getAllResults() {
        List<MainMarketResultDTO> allResults = resultService.getAllResults();
        return ResponseEntity.ok(allResults);
    }

    // Delete result
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable int id) {
        resultService.deleteResult(id);
        return ResponseEntity.ok("Result with ID " + id + " deleted successfully.");
    }
}
