package com.OnlineGame.backend.Games.SPDPTP.repository;


import com.OnlineGame.backend.Games.SPDPTP.entity.SPDPTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SPDPTPRepository extends JpaRepository<SPDPTP, Integer> {
    List<SPDPTP> findByAddUser_Id(int userId);
}
