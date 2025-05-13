package com.OnlineGame.backend.Games.SinglePatti.controller;

import com.OnlineGame.backend.Games.SinglePatti.dto.SinglePattiDTO;
import com.OnlineGame.backend.Games.SinglePatti.service.SinglePattiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/single-patti")
public class SinglePattiController {

    private final SinglePattiService singlePattiService;

    @Autowired
    public SinglePattiController(SinglePattiService singlePattiService) {
        this.singlePattiService = singlePattiService;
    }

    @PostMapping
    public ResponseEntity<SinglePattiDTO> createSinglePatti(@RequestBody SinglePattiDTO singlePattiDTO) {
        SinglePattiDTO createdSinglePatti = singlePattiService.createSinglePatti(singlePattiDTO);
        return new ResponseEntity<>(createdSinglePatti, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SinglePattiDTO> getSinglePattiById(@PathVariable int id) {
        SinglePattiDTO singlePattiDTO = singlePattiService.getSinglePattiById(id);
        return ResponseEntity.ok(singlePattiDTO);
    }

    @GetMapping
    public ResponseEntity<List<SinglePattiDTO>> getAllSinglePattis() {
        List<SinglePattiDTO> singlePattis = singlePattiService.getAllSinglePattis();
        return ResponseEntity.ok(singlePattis);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SinglePattiDTO>> getSinglePattisByUser(@PathVariable int userId) {
        List<SinglePattiDTO> singlePattis = singlePattiService.findByUser(userId);
        return ResponseEntity.ok(singlePattis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SinglePattiDTO> updateSinglePatti(
            @PathVariable int id,
            @RequestBody SinglePattiDTO singlePattiDTO) {
        SinglePattiDTO updatedSinglePatti = singlePattiService.updateSinglePatti(id, singlePattiDTO);
        return ResponseEntity.ok(updatedSinglePatti);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinglePatti(@PathVariable int id) {
        singlePattiService.deleteSinglePatti(id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints for digit operations
    @GetMapping("/{id}/digits")
    public ResponseEntity<Map<String, Map<String, Integer>>> getAllDigitValues(@PathVariable int id) {
        SinglePattiDTO singlePattiDTO = singlePattiService.getSinglePattiById(id);
        return ResponseEntity.ok(singlePattiDTO.getDigitValues());
    }

    @GetMapping("/{id}/digits/{digitName}")
    public ResponseEntity<Map<String, Integer>> getDigitGroup(
            @PathVariable int id,
            @PathVariable String digitName) {
        SinglePattiDTO singlePattiDTO = singlePattiService.getSinglePattiById(id);
        Map<String, Integer> digitGroup = singlePattiDTO.getDigitValues().get(digitName);
        if (digitGroup == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(digitGroup);
    }

    @GetMapping("/{id}/digits/{digitName}/{specificDigit}")
    public ResponseEntity<Integer> getSpecificDigitValue(
            @PathVariable int id,
            @PathVariable String digitName,
            @PathVariable String specificDigit) {
        SinglePattiDTO singlePattiDTO = singlePattiService.getSinglePattiById(id);
        Map<String, Integer> digitGroup = singlePattiDTO.getDigitValues().get(digitName);
        if (digitGroup == null || !digitGroup.containsKey(specificDigit)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(digitGroup.get(specificDigit));
    }
}