package com.OnlineGame.backend.Withdrawal.service;

import com.OnlineGame.backend.Withdrawal.dto.WithdrawalDTO;

import java.util.List;

public interface WithdrawalService {
    WithdrawalDTO saveWithdrawal(WithdrawalDTO withdrawalDTO);
    WithdrawalDTO findById(int id);
    List<WithdrawalDTO> findByUser(int userId);
    List<WithdrawalDTO> findAllWithdrawal();
    WithdrawalDTO updateWithdrawal(int id, WithdrawalDTO withdrawalDTO);
    void deleteWithdrawal(int id);
}
