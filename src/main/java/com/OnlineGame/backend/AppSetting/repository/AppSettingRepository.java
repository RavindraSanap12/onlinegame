package com.OnlineGame.backend.AppSetting.repository;

import com.OnlineGame.backend.AppSetting.entity.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSettingRepository extends JpaRepository<AppSetting, Integer> {
}
