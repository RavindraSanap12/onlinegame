package com.OnlineGame.backend.ResultDeclare.service;

import com.OnlineGame.backend.ResultDeclare.dto.DelhiMarketResultDTO;

import java.util.List;

public interface DelhiMarketResultService {
    DelhiMarketResultDTO addResult(DelhiMarketResultDTO dto);

    DelhiMarketResultDTO updateResult(int id, DelhiMarketResultDTO dto);

    DelhiMarketResultDTO getResultById(int id);

    List<DelhiMarketResultDTO> getAllResults();

    void deleteResult(int id);
}
