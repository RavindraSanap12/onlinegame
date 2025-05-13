package com.OnlineGame.backend.PointManagement.repository;

import com.OnlineGame.backend.PointManagement.entity.Points;
import com.OnlineGame.backend.User.entity.AddUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PointRepository extends JpaRepository<Points, Integer> {
    Optional<Points> findByAddUser(AddUser user);

    List<Points> findAllByAddUserId(int userId);

    @Query("SELECT SUM(p.amount) FROM Points p WHERE p.addUser.id = :userId")
    Optional<Double> findTotalAmountsByUserId(int userId);
}
