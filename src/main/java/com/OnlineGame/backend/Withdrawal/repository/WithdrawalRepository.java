package com.OnlineGame.backend.Withdrawal.repository;

import com.OnlineGame.backend.Withdrawal.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {
    List<Withdrawal> findByAddUser_Id(int userId);
}
