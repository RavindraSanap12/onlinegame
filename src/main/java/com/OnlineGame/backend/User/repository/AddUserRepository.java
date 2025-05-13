package com.OnlineGame.backend.User.repository;

import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.entity.AddUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddUserRepository extends JpaRepository<AddUser, Integer> {
    Optional<AddUser> findByMobileNoAndPassword(String mobileNo, String password);
    List<AddUser> findByStatusTrue();
    List<AddUser> findByStatusFalse();
    Optional<AddUser> findByMobileNo(String mobileNo);

}
