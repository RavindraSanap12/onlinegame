package com.OnlineGame.backend.Market.controller;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.service.DelhiMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/delhi-markets")
public class DelhiMarketController {

    private final DelhiMarketService delhiMarketService;

    @Autowired
    public DelhiMarketController(DelhiMarketService delhiMarketService) {
        this.delhiMarketService = delhiMarketService;
    }

    @PostMapping
    public ResponseEntity<DelhiMarketDTO> createDelhiMarket(@RequestBody DelhiMarketDTO delhiMarketDTO) {
        DelhiMarketDTO savedMarket = delhiMarketService.saveDelhiMarket(delhiMarketDTO);
        return new ResponseEntity<>(savedMarket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DelhiMarketDTO> getDelhiMarketById(@PathVariable int id) {
        return delhiMarketService.getDelhiMarketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DelhiMarketDTO>> getAllDelhiMarkets() {
        List<DelhiMarketDTO> markets = delhiMarketService.getAllMarkets();
        return ResponseEntity.ok(markets);
    }

    @GetMapping("/active")
    public ResponseEntity<List<DelhiMarket>> getActiveMarkets() {
        List<DelhiMarket> markets = delhiMarketService.getActiveMarkets();
        return ResponseEntity.ok(markets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DelhiMarketDTO> updateDelhiMarket(
            @PathVariable int id,
            @RequestBody DelhiMarketDTO delhiMarketDTO) {
        DelhiMarketDTO updatedMarket = delhiMarketService.updateDelhiMarket(id, delhiMarketDTO);
        return ResponseEntity.ok(updatedMarket);
    }
    @PutMapping("/{id}/status")
    public String changeMarketStatus(@PathVariable int id, @RequestParam boolean status) {
        delhiMarketService.changeDelhiMarketStatus(id, status);
        return "Status updated successfully for Delhi Market with ID " + id;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelhiMarket(@PathVariable int id) {
        delhiMarketService.deleteDelhiMarket(id);
        return ResponseEntity.noContent().build();
    }
}