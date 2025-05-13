package com.OnlineGame.backend.Games.SingleAnk.repository;

import com.OnlineGame.backend.Games.SingleAnk.entity.SingleAnk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SingleAnkRepository extends JpaRepository<SingleAnk, Integer> {
    List<SingleAnk> findByAddUser_Id(int userId);
}
