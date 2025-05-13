package com.OnlineGame.backend.PointManagement.repository;

import com.OnlineGame.backend.PointManagement.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    List<TransactionHistory> findByEntryTypeIgnoreCase(String entryType);

    default List<TransactionHistory> findByEntryTypeCredit() {
        return findByEntryTypeIgnoreCase("credit");
    }
    default List<TransactionHistory> findByEntryTypeDedit() {
        return findByEntryTypeIgnoreCase("debit");
    }
}
