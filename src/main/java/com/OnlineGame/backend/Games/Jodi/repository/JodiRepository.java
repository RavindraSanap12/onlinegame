package com.OnlineGame.backend.Games.Jodi.repository;

import com.OnlineGame.backend.Games.Jodi.entity.Jodi;
import com.OnlineGame.backend.Games.SingleAnk.entity.SingleAnk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JodiRepository extends JpaRepository<Jodi, Integer> {
    List<Jodi> findByAddUser_Id(int userId);
}
