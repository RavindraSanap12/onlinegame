package com.OnlineGame.backend.Games.FullSangam.controller;

import com.OnlineGame.backend.Games.FullSangam.dto.FullSangamDTO;
import com.OnlineGame.backend.Games.FullSangam.service.FullSangamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/full-sangam")
public class FullSangamController {

    private final FullSangamService fullSangamService;

    @Autowired
    public FullSangamController(FullSangamService fullSangamService) {
        this.fullSangamService = fullSangamService;
    }

    @PostMapping
    public ResponseEntity<FullSangamDTO> createFullSangam(@RequestBody FullSangamDTO fullSangamDTO) {
        FullSangamDTO createdFullSangam = fullSangamService.saveFullSangam(fullSangamDTO);
        return new ResponseEntity<>(createdFullSangam, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullSangamDTO> getFullSangamById(@PathVariable int id) {
        FullSangamDTO fullSangamDTO = fullSangamService.findById(id);
        return ResponseEntity.ok(fullSangamDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FullSangamDTO>> getFullSangamByUser(@PathVariable int userId) {
        List<FullSangamDTO> fullSangamList = fullSangamService.findByUser(userId);
        return ResponseEntity.ok(fullSangamList);
    }

    @GetMapping
    public ResponseEntity<List<FullSangamDTO>> getAllFullSangam() {
        List<FullSangamDTO> fullSangamList = fullSangamService.findAllFullSangam();
        return ResponseEntity.ok(fullSangamList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullSangamDTO> updateFullSangam(
            @PathVariable int id,
            @RequestBody FullSangamDTO fullSangamDTO) {
        FullSangamDTO updatedFullSangam = fullSangamService.updateFullSangam(id, fullSangamDTO);
        return ResponseEntity.ok(updatedFullSangam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFullSangam(@PathVariable int id) {
        fullSangamService.deleteFullSangam(id);
        return ResponseEntity.noContent().build();
    }

}