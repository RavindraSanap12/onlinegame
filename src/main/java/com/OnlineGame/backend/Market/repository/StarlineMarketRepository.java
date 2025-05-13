package com.OnlineGame.backend.Market.repository;

import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarlineMarketRepository extends JpaRepository<StarlineMarket, Integer> {
    List<StarlineMarket> findByStatusTrue();
}