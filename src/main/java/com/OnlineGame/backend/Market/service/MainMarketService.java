package com.OnlineGame.backend.Market.service;

import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;

import java.util.List;
import java.util.Optional;

public interface MainMarketService {

    MainMarketDTO saveMainMarket(MainMarketDTO mainMarketDTO);

    Optional<MainMarketDTO> getMainMarketById(int id);

    List<MainMarketDTO> getAllMainMarkets();

    MainMarketDTO updateMainMarket(int id, MainMarketDTO mainMarketDTO);

    void deleteMainMarket(int id);

    void changeMainMarketStatus(int id, boolean status);

    List<MainMarket> getActiveMarkets();

}
