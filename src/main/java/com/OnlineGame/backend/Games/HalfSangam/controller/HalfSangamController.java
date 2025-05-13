package com.OnlineGame.backend.Games.HalfSangam.controller;

import com.OnlineGame.backend.Games.HalfSangam.dto.HalfSangamDTO;
import com.OnlineGame.backend.Games.HalfSangam.service.HalfSangamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/half-sangam")
public class HalfSangamController {

    private final HalfSangamService halfSangamService;

    @Autowired
    public HalfSangamController(HalfSangamService halfSangamService) {
        this.halfSangamService = halfSangamService;
    }

    @PostMapping
    public ResponseEntity<HalfSangamDTO> createHalfSangam(@RequestBody HalfSangamDTO halfSangamDTO) {
        HalfSangamDTO createdHalfSangam = halfSangamService.saveHalfSangam(halfSangamDTO);
        return new ResponseEntity<>(createdHalfSangam, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HalfSangamDTO> getHalfSangamById(@PathVariable int id) {
        HalfSangamDTO halfSangamDTO = halfSangamService.findById(id);
        return ResponseEntity.ok(halfSangamDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HalfSangamDTO>> getHalfSangamByUser(@PathVariable int userId) {
        List<HalfSangamDTO> halfSangamList = halfSangamService.findByUser(userId);
        return ResponseEntity.ok(halfSangamList);
    }

    @GetMapping
    public ResponseEntity<List<HalfSangamDTO>> getAllHalfSangam() {
        List<HalfSangamDTO> halfSangamList = halfSangamService.findAllHalfSangam();
        return ResponseEntity.ok(halfSangamList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HalfSangamDTO> updateHalfSangam(
            @PathVariable int id,
            @RequestBody HalfSangamDTO halfSangamDTO) {
        HalfSangamDTO updatedHalfSangam = halfSangamService.updateHalfSangam(id, halfSangamDTO);
        return ResponseEntity.ok(updatedHalfSangam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHalfSangam(@PathVariable int id) {
        halfSangamService.deleteHalfSangam(id);
        return ResponseEntity.noContent().build();
    }

}