package com.OnlineGame.backend.PointManagement.service;

import com.OnlineGame.backend.PointManagement.dto.PointsDTO;
import com.OnlineGame.backend.PointManagement.entity.TransactionHistory;

import java.util.List;

public interface PointsService {

    PointsDTO addOrUpdatePoints(PointsDTO pointsDTO);

    List<PointsDTO> getAllPoints();

    List<PointsDTO> getAmountByUserId(int userId);

    public PointsDTO deductPoints(PointsDTO pointsDTO);

    public List<TransactionHistory> getAllTransactionHistory();

    List<TransactionHistory> getTransactionsByCredit();

    List<TransactionHistory> getTransactionsByDebit();

}
