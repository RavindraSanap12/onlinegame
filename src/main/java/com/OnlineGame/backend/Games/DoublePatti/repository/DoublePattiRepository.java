package com.OnlineGame.backend.Games.DoublePatti.repository;

import com.OnlineGame.backend.Games.DoublePatti.entity.DoublePatti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoublePattiRepository extends JpaRepository<DoublePatti, Integer> {
    List<DoublePatti> findByAddUser_Id(int userId);
}
