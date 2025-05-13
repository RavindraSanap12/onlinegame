package com.OnlineGame.backend.ResultDeclare.controller;

import com.OnlineGame.backend.ResultDeclare.dto.DelhiMarketResultDTO;
import com.OnlineGame.backend.ResultDeclare.service.DelhiMarketResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delhi-market-results")
@CrossOrigin(value = "*")
public class DelhiMarketResultController {

    @Autowired
    private DelhiMarketResultService resultService;

    // Create
    @PostMapping
    public ResponseEntity<DelhiMarketResultDTO> addResult(@RequestBody DelhiMarketResultDTO dto) {
        DelhiMarketResultDTO createdResult = resultService.addResult(dto);
        return ResponseEntity.ok(createdResult);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<DelhiMarketResultDTO> updateResult(
            @PathVariable int id,
            @RequestBody DelhiMarketResultDTO dto
    ) {
        DelhiMarketResultDTO updatedResult = resultService.updateResult(id, dto);
        return ResponseEntity.ok(updatedResult);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<DelhiMarketResultDTO> getResultById(@PathVariable int id) {
        DelhiMarketResultDTO result = resultService.getResultById(id);
        return ResponseEntity.ok(result);
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<DelhiMarketResultDTO>> getAllResults() {
        List<DelhiMarketResultDTO> results = resultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable int id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
