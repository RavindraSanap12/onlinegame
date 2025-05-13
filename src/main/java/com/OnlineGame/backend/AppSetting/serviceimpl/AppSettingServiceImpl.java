package com.OnlineGame.backend.AppSetting.serviceimpl;

import com.OnlineGame.backend.AppSetting.dto.AppSettingDTO;
import com.OnlineGame.backend.AppSetting.entity.AppSetting;
import com.OnlineGame.backend.AppSetting.repository.AppSettingRepository;
import com.OnlineGame.backend.AppSetting.services.AppSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppSettingServiceImpl implements AppSettingService {

    @Autowired
    private AppSettingRepository appSettingRepository;

    @Override
    public AppSettingDTO saveAppSetting(AppSettingDTO dto) {
        List<AppSetting> existingSettings = appSettingRepository.findAll();
        if (!existingSettings.isEmpty()) {
            AppSetting existing = existingSettings.get(0);
            updateEntityFromDto(existing, dto);
            AppSetting updated = appSettingRepository.save(existing);
            return convertToDTO(updated);
        } else {
            AppSetting saved = appSettingRepository.save(convertToEntity(dto));
            return convertToDTO(saved);
        }
    }


    @Override
    public List<AppSettingDTO> getAllAppSettings() {
        return appSettingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppSettingDTO> getAppSettingById(int id) {
        return appSettingRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public AppSettingDTO updateAppSetting(int id, AppSettingDTO dto) {
        Optional<AppSetting> existingOpt = appSettingRepository.findById(id);
        if (existingOpt.isPresent()) {
            AppSetting existing = existingOpt.get();
            updateEntityFromDto(existing, dto);
            AppSetting updated = appSettingRepository.save(existing);
            return convertToDTO(updated);
        } else {
            throw new RuntimeException("AppSetting not found with ID: " + id);
        }
    }

    @Override
    public void deleteAppSetting(int id) {
        appSettingRepository.deleteById(id);
    }

    // Helper Methods

    private AppSettingDTO convertToDTO(AppSetting entity) {
        AppSettingDTO dto = new AppSettingDTO();
        dto.setId(entity.getId());
        dto.setApplicationName(entity.getApplicationName());
        dto.setCompanyAddress(entity.getCompanyAddress());
        dto.setAutoApproved(entity.isAutoApproved());
        dto.setUpiId(entity.getUpiId());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setWhatsappNumber(entity.getWhatsappNumber());
        dto.setEmail(entity.getEmail());
        dto.setTelegramLink(entity.getTelegramLink());
        dto.setYoutubeLink(entity.getYoutubeLink());
        dto.setPlaystoreLink(entity.getPlaystoreLink());
        dto.setMinPointAdd(entity.getMinPointAdd());
        dto.setMinWithdraw(entity.getMinWithdraw());
        dto.setMaxWithdraw(entity.getMaxWithdraw());
        dto.setWithdrawOpenTime(entity.getWithdrawOpenTime());
        dto.setWithdrawCloseTime(entity.getWithdrawCloseTime());
        dto.setWithdrawalEnabled(entity.isWithdrawalEnabled());
        dto.setWithdrawalContent(entity.getWithdrawalContent());
        dto.setShareLink(entity.getShareLink());
        return dto;
    }

    private AppSetting convertToEntity(AppSettingDTO dto) {
        AppSetting entity = new AppSetting();
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(AppSetting entity, AppSettingDTO dto) {
        entity.setApplicationName(dto.getApplicationName());
        entity.setCompanyAddress(dto.getCompanyAddress());
        entity.setAutoApproved(dto.isAutoApproved());
        entity.setUpiId(dto.getUpiId());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setWhatsappNumber(dto.getWhatsappNumber());
        entity.setEmail(dto.getEmail());
        entity.setTelegramLink(dto.getTelegramLink());
        entity.setYoutubeLink(dto.getYoutubeLink());
        entity.setPlaystoreLink(dto.getPlaystoreLink());
        entity.setMinPointAdd(dto.getMinPointAdd());
        entity.setMinWithdraw(dto.getMinWithdraw());
        entity.setMaxWithdraw(dto.getMaxWithdraw());
        entity.setWithdrawOpenTime(dto.getWithdrawOpenTime());
        entity.setWithdrawCloseTime(dto.getWithdrawCloseTime());
        entity.setWithdrawalEnabled(dto.isWithdrawalEnabled());
        entity.setWithdrawalContent(dto.getWithdrawalContent());
        entity.setShareLink(dto.getShareLink());
    }
}
