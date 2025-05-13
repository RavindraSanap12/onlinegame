package com.OnlineGame.backend.Games.SinglePatti.repository;

import com.OnlineGame.backend.Games.SinglePatti.entity.SinglePatti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SinglePattiRepository extends JpaRepository<SinglePatti, Integer> {
    List<SinglePatti> findByAddUser_Id(int userId);
}
