package com.OnlineGame.backend.GameRates.repository;

import com.OnlineGame.backend.GameRates.entity.GameRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRatesRepository extends JpaRepository<GameRates, Integer> {
    Optional<GameRates> findByName(String name);
}
