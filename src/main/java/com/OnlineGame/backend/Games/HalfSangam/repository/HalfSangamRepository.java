package com.OnlineGame.backend.Games.HalfSangam.repository;

import com.OnlineGame.backend.Games.HalfSangam.entity.HalfSangam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HalfSangamRepository extends JpaRepository<HalfSangam, Integer> {
    List<HalfSangam> findByAddUser_Id(int userId);
}
