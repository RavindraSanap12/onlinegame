package com.OnlineGame.backend.Games.SPMotor.repository;


import com.OnlineGame.backend.Games.SPMotor.entity.SPMotor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SPMotorRepository extends JpaRepository<SPMotor, Integer> {
    List<SPMotor> findByAddUser_Id(int userId);
}
