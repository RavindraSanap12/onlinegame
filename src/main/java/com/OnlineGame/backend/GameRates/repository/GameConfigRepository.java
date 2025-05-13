package com.OnlineGame.backend.GameRates.repository;

import com.OnlineGame.backend.GameRates.entity.GameConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameConfigRepository extends JpaRepository<GameConfig, Integer> {
    List<GameConfig> findByGameRatesId(int gameRatesId);
}
