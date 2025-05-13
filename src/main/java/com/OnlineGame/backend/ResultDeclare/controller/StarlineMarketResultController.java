package com.OnlineGame.backend.ResultDeclare.controller;

import com.OnlineGame.backend.ResultDeclare.dto.StarlineMarketResultDTO;
import com.OnlineGame.backend.ResultDeclare.service.StarlineMarketResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/starline-market-results")
public class StarlineMarketResultController {

    @Autowired
    private StarlineMarketResultService resultService;

    // Endpoint to save a new result
    @PostMapping
    public ResponseEntity<StarlineMarketResultDTO> saveResult(@RequestBody StarlineMarketResultDTO dto) {
        StarlineMarketResultDTO savedResult = resultService.saveResult(dto);
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    // Endpoint to get all results
    @GetMapping
    public ResponseEntity<List<StarlineMarketResultDTO>> getAllResults() {
        List<StarlineMarketResultDTO> results = resultService.getAllResults();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    // Endpoint to get a result by ID
    @GetMapping("/{id}")
    public ResponseEntity<StarlineMarketResultDTO> getResultById(@PathVariable int id) {
        StarlineMarketResultDTO result = resultService.getResultById(id);
        return result != null ? new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to update an existing result
    @PutMapping("/{id}")
    public ResponseEntity<StarlineMarketResultDTO> updateResult(@PathVariable int id, @RequestBody StarlineMarketResultDTO dto) {
        StarlineMarketResultDTO updatedResult = resultService.updateResult(id, dto);
        return updatedResult != null ? new ResponseEntity<>(updatedResult, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a result by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable int id) {
        resultService.deleteResult(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
