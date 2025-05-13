package com.OnlineGame.backend.Games.Jodi.controller;

import com.OnlineGame.backend.Games.Jodi.dto.JodiDTO;
import com.OnlineGame.backend.Games.Jodi.service.JodiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/app/jodis")
public class JodiController {

    private final JodiService jodiService;

    @Autowired
    public JodiController(JodiService jodiService) {
        this.jodiService = jodiService;
    }

    @PostMapping
    public ResponseEntity<JodiDTO> createJodi(@RequestBody JodiDTO jodiDTO) {
        JodiDTO createdJodi = jodiService.createJodi(jodiDTO);
        return new ResponseEntity<>(createdJodi, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JodiDTO> getJodiById(@PathVariable int id) {
        JodiDTO jodiDTO = jodiService.getJodiById(id);
        return ResponseEntity.ok(jodiDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JodiDTO>> getJodisByUser(@PathVariable int userId) {
        List<JodiDTO> jodis = jodiService.findByUser(userId);
        return ResponseEntity.ok(jodis);
    }

    @GetMapping
    public ResponseEntity<List<JodiDTO>> getAllJodis() {
        List<JodiDTO> jodis = jodiService.getAllJodis();
        return ResponseEntity.ok(jodis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JodiDTO> updateJodi(@PathVariable int id, @RequestBody JodiDTO jodiDTO) {
        JodiDTO updatedJodi = jodiService.updateJodi(id, jodiDTO);
        return ResponseEntity.ok(updatedJodi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJodi(@PathVariable int id) {
        jodiService.deleteJodi(id);
        return ResponseEntity.noContent().build();
    }
}