package com.OnlineGame.backend.AppSetting.controller;

import com.OnlineGame.backend.AppSetting.dto.AppSettingDTO;
import com.OnlineGame.backend.AppSetting.services.AppSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app-settings")
@CrossOrigin(value = "*")
public class AppSettingController {

    @Autowired
    private AppSettingService appSettingService;

    // Create a new AppSetting
    @PostMapping
    public ResponseEntity<AppSettingDTO> createAppSetting(@RequestBody AppSettingDTO dto) {
        AppSettingDTO saved = appSettingService.saveAppSetting(dto);
        return ResponseEntity.ok(saved);
    }

    // Get all AppSettings
    @GetMapping
    public ResponseEntity<List<AppSettingDTO>> getAllAppSettings() {
        return ResponseEntity.ok(appSettingService.getAllAppSettings());
    }

    // Get AppSetting by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppSettingDTO> getAppSettingById(@PathVariable int id) {
        return appSettingService.getAppSettingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update AppSetting
    @PutMapping("/{id}")
    public ResponseEntity<AppSettingDTO> updateAppSetting(@PathVariable int id, @RequestBody AppSettingDTO dto) {
        try {
            AppSettingDTO updated = appSettingService.updateAppSetting(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete AppSetting
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppSetting(@PathVariable int id) {
        appSettingService.deleteAppSetting(id);
        return ResponseEntity.noContent().build();
    }
}
