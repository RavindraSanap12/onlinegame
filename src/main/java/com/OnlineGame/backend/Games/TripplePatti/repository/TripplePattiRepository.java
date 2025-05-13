package com.OnlineGame.backend.Games.TripplePatti.repository;

import com.OnlineGame.backend.Games.SingleAnk.entity.SingleAnk;
import com.OnlineGame.backend.Games.TripplePatti.entity.TripplePatti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripplePattiRepository extends JpaRepository<TripplePatti, Integer> {
    List<TripplePatti> findByAddUser_Id(int userId);
}
