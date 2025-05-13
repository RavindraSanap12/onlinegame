package com.OnlineGame.backend.ResultDeclare.repository;

import com.OnlineGame.backend.ResultDeclare.entity.MainMarketResult;
import com.OnlineGame.backend.ResultDeclare.entity.StarlineMarketResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StarlineMarketResultRepository extends JpaRepository<StarlineMarketResult, Integer> {
}
