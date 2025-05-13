package com.OnlineGame.backend.Market.controller;

import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.service.MainMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/main-markets")
public class MainMarketController {

    @Autowired
    private MainMarketService mainMarketService;

    @PostMapping
    public ResponseEntity<MainMarketDTO> createMarket(@RequestBody MainMarketDTO mainMarketDTO) {
        MainMarketDTO savedMarket = mainMarketService.saveMainMarket(mainMarketDTO);
        return new ResponseEntity<>(savedMarket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainMarketDTO> getMarketById(@PathVariable int id) {
        return mainMarketService.getMainMarketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MainMarketDTO>> getAllMarkets() {
        List<MainMarketDTO> markets = mainMarketService.getAllMainMarkets();
        return ResponseEntity.ok(markets);
    }

    @GetMapping("/active")
    public ResponseEntity<List<MainMarket>> getActiveMarkets() {
        List<MainMarket> markets = mainMarketService.getActiveMarkets();
        return ResponseEntity.ok(markets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainMarketDTO> updateMarket(
            @PathVariable int id,
            @RequestBody MainMarketDTO mainMarketDTO) {
        MainMarketDTO updatedMarket = mainMarketService.updateMainMarket(id, mainMarketDTO);
        return ResponseEntity.ok(updatedMarket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable int id) {
        mainMarketService.deleteMainMarket(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/status")
    public String changeMarketStatus(@PathVariable int id, @RequestParam boolean status) {
        mainMarketService.changeMainMarketStatus(id, status);
        return "Status updated successfully for Main Market with ID " + id;
    }

    // Additional endpoints can be added here as needed
}