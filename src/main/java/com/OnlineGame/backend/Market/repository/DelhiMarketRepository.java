package com.OnlineGame.backend.Market.repository;

import com.OnlineGame.backend.Market.entity.DelhiMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DelhiMarketRepository extends JpaRepository<DelhiMarket, Integer> {
    List<DelhiMarket> findByStatusTrue();
}
