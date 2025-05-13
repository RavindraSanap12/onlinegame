package com.OnlineGame.backend.ResultDeclare.service;

import com.OnlineGame.backend.ResultDeclare.dto.StarlineMarketResultDTO;

import java.util.List;

public interface StarlineMarketResultService {
    StarlineMarketResultDTO saveResult(StarlineMarketResultDTO resultDTO);

    List<StarlineMarketResultDTO> getAllResults();

    StarlineMarketResultDTO getResultById(int id);

    StarlineMarketResultDTO updateResult(int id, StarlineMarketResultDTO resultDTO);

    void deleteResult(int id);
}
