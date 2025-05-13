package com.OnlineGame.backend.Games.DPMotor.controller;

import com.OnlineGame.backend.Games.DPMotor.dto.DPMotorDTO;
import com.OnlineGame.backend.Games.DPMotor.service.DPMotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/dpmotor")
public class DPMotorController {

    private final DPMotorService dpMotorService;

    @Autowired
    public DPMotorController(DPMotorService dpMotorService) {
        this.dpMotorService = dpMotorService;
    }

    @PostMapping()
    public ResponseEntity<List<DPMotorDTO>> saveDPMotor(@RequestBody List<DPMotorDTO> dpMotorDTOList) {
        List<DPMotorDTO> savedList = dpMotorService.saveDPMotor(dpMotorDTOList);
        return ResponseEntity.ok(savedList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DPMotorDTO> updateDPMotor(@PathVariable int id, @RequestBody DPMotorDTO dpMotorDTO) {
        DPMotorDTO updated = dpMotorService.updateDPMotor(id, dpMotorDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDPMotor(@PathVariable int id) {
        dpMotorService.deleteDPMotor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DPMotorDTO> getDPMotorById(@PathVariable int id) {
        DPMotorDTO dpMotorDTO = dpMotorService.getDPMotorById(id);
        return ResponseEntity.ok(dpMotorDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DPMotorDTO>> getDPMotorsByUser(@PathVariable int userId) {
        List<DPMotorDTO> list = dpMotorService.findByUser(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<DPMotorDTO>> getAllDPMotors() {
        List<DPMotorDTO> list = dpMotorService.getAllDPMotor();
        return ResponseEntity.ok(list);
    }
}
