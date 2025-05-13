package com.OnlineGame.backend.Games.SPDPTP.controller;

import com.OnlineGame.backend.Games.SPDPTP.dto.SPDPTPDTO;
import com.OnlineGame.backend.Games.SPDPTP.service.SPDPTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/spdptp")
public class SPDPTPController {

    private final SPDPTPService spdptpService;

    @Autowired
    public SPDPTPController(SPDPTPService spdptpService) {
        this.spdptpService = spdptpService;
    }

    @PostMapping()
    public ResponseEntity<List<SPDPTPDTO>> saveAll(@RequestBody List<SPDPTPDTO> spdptpDTOList) {
        List<SPDPTPDTO> saved = spdptpService.saveAllSPDPTP(spdptpDTOList);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SPDPTPDTO> updateSPDPTP(
            @PathVariable int id,
            @RequestBody SPDPTPDTO spdptpDTO) {
        SPDPTPDTO updatedSPDPTP = spdptpService.updateSPDPTP(id, spdptpDTO);
        return ResponseEntity.ok(updatedSPDPTP);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSPDPTP(@PathVariable int id) {
        spdptpService.deleteSPDPTP(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SPDPTPDTO> getSPDPTPById(@PathVariable int id) {
        SPDPTPDTO spdptpDTO = spdptpService.getSPDPTPById(id);
        return ResponseEntity.ok(spdptpDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SPDPTPDTO>> getSPDPTPByUser(@PathVariable int userId) {
        List<SPDPTPDTO> spdptpList = spdptpService.findByUser(userId);
        return ResponseEntity.ok(spdptpList);
    }

    @GetMapping
    public ResponseEntity<List<SPDPTPDTO>> getAllSPDPTP() {
        List<SPDPTPDTO> spdptpList = spdptpService.getAllSPDPTP();
        return ResponseEntity.ok(spdptpList);
    }

}