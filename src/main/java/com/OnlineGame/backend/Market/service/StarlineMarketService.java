package com.OnlineGame.backend.Market.service;

import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;

import java.util.List;
import java.util.Optional;

public interface StarlineMarketService {

    StarlineMarketDTO createStarline(StarlineMarketDTO starlineDTO);

    Optional<StarlineMarketDTO> getStarlineById(int id);

    List<StarlineMarketDTO> getAllStarlines();

    StarlineMarketDTO updateStarline(int id, StarlineMarketDTO starlineDTO);

    void deleteStarline(int id);

    void changeStarlineMarketStatus(int id, boolean status);

    List<StarlineMarket> getActiveMarkets();

}