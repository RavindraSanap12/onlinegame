package com.OnlineGame.backend.GameRates.controller;

import com.OnlineGame.backend.GameRates.dto.GameRatesDTO;
import com.OnlineGame.backend.GameRates.service.GameRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/gamerates")
public class GameRatesController {

    private final GameRatesService gameRatesService;

    @Autowired
    public GameRatesController(GameRatesService gameRatesService) {
        this.gameRatesService = gameRatesService;
    }

    // SAVE - New Entries Only
    @PostMapping("/save")
    public ResponseEntity<List<GameRatesDTO>> saveGameRates(@RequestBody List<GameRatesDTO> gameRatesDTOList) {
        List<GameRatesDTO> savedRates = gameRatesService.saveAll(gameRatesDTOList);
        return ResponseEntity.ok(savedRates); // You can also use .created(...) for URI location
    }

    // UPDATE - Existing Entries Only
    @PutMapping("/update")
    public ResponseEntity<List<GameRatesDTO>> updateGameRates(@RequestBody List<GameRatesDTO> gameRatesDTOList) {
        List<GameRatesDTO> updatedRates = gameRatesService.updateAll(gameRatesDTOList);
        return ResponseEntity.ok(updatedRates);
    }

    // Endpoint to retrieve all GameRates
    @GetMapping("/all")
    public ResponseEntity<List<GameRatesDTO>> getAll() {
        List<GameRatesDTO> gameRatesDTOList = gameRatesService.getAll();
        return new ResponseEntity<>(gameRatesDTOList, HttpStatus.OK);
    }

    // Endpoint to retrieve a specific GameRate by ID
    @GetMapping("/{id}")
    public ResponseEntity<GameRatesDTO> getById(@PathVariable int id) {
        GameRatesDTO gameRatesDTO = gameRatesService.getById(id);
        if (gameRatesDTO != null) {
            return new ResponseEntity<>(gameRatesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a GameRate by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        gameRatesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
