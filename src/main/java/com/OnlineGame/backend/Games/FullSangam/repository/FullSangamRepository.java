package com.OnlineGame.backend.Games.FullSangam.repository;

import com.OnlineGame.backend.Games.FullSangam.entity.FullSangam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FullSangamRepository extends JpaRepository<FullSangam, Integer> {
    List<FullSangam> findByAddUser_Id(int userId);
}
