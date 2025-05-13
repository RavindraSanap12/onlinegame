package com.OnlineGame.backend.Market.controller;

import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.Market.service.StarlineMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/starlines")
public class StarlineMarketController {

    private final StarlineMarketService starlineMarketService;

    @Autowired
    public StarlineMarketController(StarlineMarketService starlineMarketService) {
        this.starlineMarketService = starlineMarketService;
    }

    @PostMapping
    public ResponseEntity<StarlineMarketDTO> createStarline(@RequestBody StarlineMarketDTO starlineDTO) {
        StarlineMarketDTO createdStarline = starlineMarketService.createStarline(starlineDTO);
        return new ResponseEntity<>(createdStarline, HttpStatus.CREATED);
    }
    @GetMapping("/active")
    public ResponseEntity<List<StarlineMarket>> getActiveMarkets() {
        List<StarlineMarket> markets = starlineMarketService.getActiveMarkets();
        return ResponseEntity.ok(markets);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StarlineMarketDTO> getStarlineById(@PathVariable int id) {
        return starlineMarketService.getStarlineById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StarlineMarketDTO>> getAllStarlines() {
        List<StarlineMarketDTO> starlines = starlineMarketService.getAllStarlines();
        return ResponseEntity.ok(starlines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarlineMarketDTO> updateStarline(
            @PathVariable int id,
            @RequestBody StarlineMarketDTO starlineDTO) {
        StarlineMarketDTO updatedStarline = starlineMarketService.updateStarline(id, starlineDTO);
        return ResponseEntity.ok(updatedStarline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStarline(@PathVariable int id) {
        starlineMarketService.deleteStarline(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public String changeMarketStatus(@PathVariable int id, @RequestParam boolean status) {
        starlineMarketService.changeStarlineMarketStatus(id, status);
        return "Status updated successfully for Starline Market with ID " + id;
    }
}