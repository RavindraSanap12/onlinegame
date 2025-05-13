package com.OnlineGame.backend.Games.TripplePatti.controller;

import com.OnlineGame.backend.Games.TripplePatti.dto.TripplePattiDTO;
import com.OnlineGame.backend.Games.TripplePatti.service.TripplePattiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/tripple-patti")
public class TripplePattiController {

    private final TripplePattiService tripplePattiService;

    @Autowired
    public TripplePattiController(TripplePattiService tripplePattiService) {
        this.tripplePattiService = tripplePattiService;
    }

    @PostMapping
    public ResponseEntity<TripplePattiDTO> createTripplePatti(@RequestBody TripplePattiDTO tripplePattiDTO) {
        TripplePattiDTO createdTripplePatti = tripplePattiService.saveTripplePatti(tripplePattiDTO);
        return new ResponseEntity<>(createdTripplePatti, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripplePattiDTO> getTripplePattiById(@PathVariable int id) {
        TripplePattiDTO tripplePattiDTO = tripplePattiService.findById(id);
        return ResponseEntity.ok(tripplePattiDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TripplePattiDTO>> getTripplePattiByUser(@PathVariable int userId) {
        List<TripplePattiDTO> tripplePattiList = tripplePattiService.findByUser(userId);
        return ResponseEntity.ok(tripplePattiList);
    }

    @GetMapping
    public ResponseEntity<List<TripplePattiDTO>> getAllTripplePatti() {
        List<TripplePattiDTO> tripplePattiList = tripplePattiService.findAllTripplePatti();
        return ResponseEntity.ok(tripplePattiList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripplePattiDTO> updateTripplePatti(
            @PathVariable int id,
            @RequestBody TripplePattiDTO tripplePattiDTO) {
        TripplePattiDTO updatedTripplePatti = tripplePattiService.updateTripplePatti(id, tripplePattiDTO);
        return ResponseEntity.ok(updatedTripplePatti);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripplePatti(@PathVariable int id) {
        tripplePattiService.deleteTripplePatti(id);
        return ResponseEntity.noContent().build();
    }

}