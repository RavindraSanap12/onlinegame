package com.OnlineGame.backend.Market.service;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;

import java.util.List;
import java.util.Optional;

public interface DelhiMarketService {

    DelhiMarketDTO saveDelhiMarket(DelhiMarketDTO delhiMarketDTO);

    Optional<DelhiMarketDTO> getDelhiMarketById(int id);

    List<DelhiMarketDTO> getAllMarkets();

    DelhiMarketDTO updateDelhiMarket(int id, DelhiMarketDTO delhiMarketDTO);

    void deleteDelhiMarket(int id);

    void changeDelhiMarketStatus(int id, boolean status);

    List<DelhiMarket> getActiveMarkets();

    }
