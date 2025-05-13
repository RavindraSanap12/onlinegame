package com.OnlineGame.backend.Market.repository;

import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainMarketRepository extends JpaRepository<MainMarket, Integer> {
    List<MainMarket> findByStatusTrue();
}
