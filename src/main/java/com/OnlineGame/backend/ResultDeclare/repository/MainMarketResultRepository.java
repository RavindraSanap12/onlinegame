package com.OnlineGame.backend.ResultDeclare.repository;

import com.OnlineGame.backend.ResultDeclare.entity.MainMarketResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MainMarketResultRepository extends JpaRepository<MainMarketResult, Integer> {
}
