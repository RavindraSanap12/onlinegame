package com.OnlineGame.backend.Games.DoublePatti.controller;

import com.OnlineGame.backend.Games.DoublePatti.dto.DoublePattiDTO;
import com.OnlineGame.backend.Games.DoublePatti.service.DoublePattiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/double-patti")
public class DoublePattiController {

    private final DoublePattiService doublePattiService;

    @Autowired
    public DoublePattiController(DoublePattiService doublePattiService) {
        this.doublePattiService = doublePattiService;
    }

    @PostMapping
    public ResponseEntity<DoublePattiDTO> createDoublePatti(@RequestBody DoublePattiDTO doublePattiDTO) {
        DoublePattiDTO createdDoublePatti = doublePattiService.createDoublePatti(doublePattiDTO);
        return new ResponseEntity<>(createdDoublePatti, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoublePattiDTO> getDoublePattiById(@PathVariable int id) {
        DoublePattiDTO doublePattiDTO = doublePattiService.getDoublePattiById(id);
        return ResponseEntity.ok(doublePattiDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoublePattiDTO>> getAllDoublePattis() {
        List<DoublePattiDTO> doublePattis = doublePattiService.getAllDoublePattis();
        return ResponseEntity.ok(doublePattis);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DoublePattiDTO>> getDoublePattisByUser(@PathVariable int userId) {
        List<DoublePattiDTO> doublePattis = doublePattiService.findByUser(userId);
        return ResponseEntity.ok(doublePattis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoublePattiDTO> updateDoublePatti(
            @PathVariable int id,
            @RequestBody DoublePattiDTO doublePattiDTO) {
        DoublePattiDTO updatedDoublePatti = doublePattiService.updateDoublePatti(id, doublePattiDTO);
        return ResponseEntity.ok(updatedDoublePatti);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoublePatti(@PathVariable int id) {
        doublePattiService.deleteDoublePatti(id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints can be added here as needed
}