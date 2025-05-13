package com.OnlineGame.backend.GameRates.service;

import com.OnlineGame.backend.GameRates.dto.GameRatesDTO;

import java.util.List;

public interface GameRatesService {
    List<GameRatesDTO> saveAll(List<GameRatesDTO> gameRatesDTOList);

    List<GameRatesDTO> getAll();

    List<GameRatesDTO> updateAll(List<GameRatesDTO> gameRatesDTOList);

    GameRatesDTO getById(int id);

    void deleteById(int id);
}
