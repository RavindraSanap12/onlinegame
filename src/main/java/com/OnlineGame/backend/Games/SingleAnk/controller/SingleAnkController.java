package com.OnlineGame.backend.Games.SingleAnk.controller;

import com.OnlineGame.backend.Games.SingleAnk.dto.SingleAnkDTO;
import com.OnlineGame.backend.Games.SingleAnk.service.SingleAnkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/single-ank")
public class SingleAnkController {

    private final SingleAnkService singleAnkService;

    @Autowired
    public SingleAnkController(SingleAnkService singleAnkService) {
        this.singleAnkService = singleAnkService;
    }

    @PostMapping
    public ResponseEntity<SingleAnkDTO> createSingleAnk(@RequestBody SingleAnkDTO singleAnkDTO) {
        SingleAnkDTO savedSingleAnk = singleAnkService.saveSingleAnk(singleAnkDTO);
        return new ResponseEntity<>(savedSingleAnk, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleAnkDTO> getSingleAnkById(@PathVariable int id) {
        SingleAnkDTO singleAnkDTO = singleAnkService.findById(id);
        return ResponseEntity.ok(singleAnkDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SingleAnkDTO>> getSingleAnksByUser(@PathVariable int userId) {
        List<SingleAnkDTO> singleAnkDTOs = singleAnkService.findByUser(userId);
        return ResponseEntity.ok(singleAnkDTOs);
    }

    @GetMapping
    public ResponseEntity<List<SingleAnkDTO>> getAllSingleAnks() {
        List<SingleAnkDTO> singleAnks = singleAnkService.findAllSingleAnk();
        return ResponseEntity.ok(singleAnks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SingleAnkDTO> updateSingleAnk(
            @PathVariable int id,
            @RequestBody SingleAnkDTO singleAnkDTO) {
        SingleAnkDTO updatedSingleAnk = singleAnkService.updateSingleAnk(id, singleAnkDTO);
        return ResponseEntity.ok(updatedSingleAnk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSingleAnk(@PathVariable int id) {
        singleAnkService.deleteSingleAnk(id);
        return ResponseEntity.noContent().build();
    }
}