package com.OnlineGame.backend.Games.SPMotor.controller;

import com.OnlineGame.backend.Games.SPDPTP.service.SPMotorService;
import com.OnlineGame.backend.Games.SPMotor.dto.SPMotorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/spmotor")
public class SPMotorController {

    private final SPMotorService spMotorService;

    @Autowired
    public SPMotorController(SPMotorService spMotorService) {
        this.spMotorService = spMotorService;
    }

    // Save list of SPMotor entries
    @PostMapping()
    public ResponseEntity<List<SPMotorDTO>> saveSPMotor(@RequestBody List<SPMotorDTO> spMotorDTOList) {
        List<SPMotorDTO> savedList = spMotorService.saveSPMotor(spMotorDTOList);
        return ResponseEntity.ok(savedList);
    }

    // Update a single SPMotor by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<SPMotorDTO> updateSPMotor(@PathVariable int id, @RequestBody SPMotorDTO spMotorDTO) {
        SPMotorDTO updated = spMotorService.updateSPMotor(id, spMotorDTO);
        return ResponseEntity.ok(updated);
    }

    // Delete a single SPMotor by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSPMotor(@PathVariable int id) {
        spMotorService.deleteSPMotor(id);
        return ResponseEntity.noContent().build();
    }

    // Get SPMotor by ID
    @GetMapping("/{id}")
    public ResponseEntity<SPMotorDTO> getSPMotorById(@PathVariable int id) {
        SPMotorDTO dto = spMotorService.getSPMotorById(id);
        return ResponseEntity.ok(dto);
    }

    // Get SPMotor list by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SPMotorDTO>> getSPMotorsByUser(@PathVariable int userId) {
        List<SPMotorDTO> list = spMotorService.findByUser(userId);
        return ResponseEntity.ok(list);
    }

    // Get all SPMotor entries
    @GetMapping("/getall")
    public ResponseEntity<List<SPMotorDTO>> getAllSPMotors() {
        List<SPMotorDTO> list = spMotorService.getAllSPMotor();
        return ResponseEntity.ok(list);
    }
}
