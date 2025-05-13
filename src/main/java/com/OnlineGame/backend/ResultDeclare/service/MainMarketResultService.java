package com.OnlineGame.backend.ResultDeclare.service;

import com.OnlineGame.backend.ResultDeclare.dto.MainMarketResultDTO;

import java.util.List;

public interface MainMarketResultService {
    MainMarketResultDTO addResult(MainMarketResultDTO dto);

    MainMarketResultDTO updateResult(int id, MainMarketResultDTO dto);

    MainMarketResultDTO getResultById(int id);

    List<MainMarketResultDTO> getAllResults();

    void deleteResult(int id);
}