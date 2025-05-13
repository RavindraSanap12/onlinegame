package com.OnlineGame.backend.Games.DPMotor.repository;


import com.OnlineGame.backend.Games.DPMotor.entity.DPMotor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DPMotorRepository extends JpaRepository<DPMotor, Integer> {
    List<DPMotor> findByAddUser_Id(int userId);
}
