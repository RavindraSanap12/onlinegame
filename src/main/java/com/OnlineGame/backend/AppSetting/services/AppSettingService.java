package com.OnlineGame.backend.AppSetting.services;

import com.OnlineGame.backend.AppSetting.dto.AppSettingDTO;
import com.OnlineGame.backend.AppSetting.entity.AppSetting;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {

    AppSettingDTO saveAppSetting(AppSettingDTO appSettingDTO);

    List<AppSettingDTO> getAllAppSettings();

    Optional<AppSettingDTO> getAppSettingById(int id);

    AppSettingDTO updateAppSetting(int id, AppSettingDTO appSettingDTO);

    void deleteAppSetting(int id);
}
