package com.OnlineGame.backend.PointManagement.serviceimpl;

import com.OnlineGame.backend.PointManagement.dto.PointsDTO;
import com.OnlineGame.backend.PointManagement.entity.Points;
import com.OnlineGame.backend.PointManagement.entity.TransactionHistory;
import com.OnlineGame.backend.PointManagement.repository.PointRepository;
import com.OnlineGame.backend.PointManagement.repository.TransactionHistoryRepository;
import com.OnlineGame.backend.PointManagement.service.PointsService;
import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.entity.AddUser;
import com.OnlineGame.backend.User.repository.AddUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PointsServiceImpl implements PointsService {

    @Autowired
    private PointRepository pointsRepository;

    @Autowired
    private AddUserRepository addUserRepository;

    @Autowired
    private TransactionHistoryRepository historyRepository;


    @Override
    public PointsDTO addOrUpdatePoints(PointsDTO pointsDTO) {
        Optional<AddUser> optionalUser = addUserRepository.findById(pointsDTO.getAddUserDTO().getId());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + pointsDTO.getAddUserDTO().getId());
        }

        AddUser user = optionalUser.get();
        // Update user's amount
        user.setAmount(user.getAmount() + pointsDTO.getAmount());
        addUserRepository.save(user);
        // Check if the user already has points entry (optional logic)
        Optional<Points> existingPoints = pointsRepository.findByAddUser(user);

        Points points = existingPoints.orElse(new Points());
        points.setAddUser(user);
        points.setAmount(points.getAmount() + pointsDTO.getAmount()); // Add to existing if present
        points.setTransactionNo(pointsDTO.getTransactionNo());
        points.setTransactionDate(LocalDate.now());
        points.setRemarks(pointsDTO.getRemarks());

        Points saved = pointsRepository.save(points);

        TransactionHistory history = new TransactionHistory();
        history.setDate(LocalDate.now());
        history.setEntryType("CREDIT");
        history.setUserName(user.getName());
        history.setMobileNo(user.getMobileNo());
        history.setAmount(pointsDTO.getAmount());
        history.setBalance(saved.getAmount());
        history.setPaymode("ONLINE"); // or from DTO if available
        history.setCreatedBy("Admin"); // or from session/auth info

        historyRepository.save(history);

        return convertToDTO(saved);
    }

    @Transactional
    @Override
    public PointsDTO deductPoints(PointsDTO pointsDTO) {
        Optional<AddUser> optionalUser = addUserRepository.findById(pointsDTO.getAddUserDTO().getId());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + pointsDTO.getAddUserDTO().getId());
        }

        AddUser user = optionalUser.get();

        Optional<Points> existingPoints = pointsRepository.findByAddUser(user);

        if (existingPoints.isEmpty()) {
            throw new RuntimeException("No points found for user with ID: " + user.getId());
        }

        Points points = existingPoints.get();
        double currentAmount = points.getAmount();

        if (currentAmount < pointsDTO.getAmount()) {
            throw new RuntimeException("Insufficient balance to deduct.");
        }

        user.setAmount(user.getAmount() - pointsDTO.getAmount());
        addUserRepository.save(user);

        points.setAmount(currentAmount - pointsDTO.getAmount());
        points.setTransactionNo(pointsDTO.getTransactionNo());
        points.setRemarks(pointsDTO.getRemarks());

        Points saved = pointsRepository.save(points);
        TransactionHistory history = new TransactionHistory();
        history.setDate(LocalDate.now());
        history.setEntryType("DEBIT");
        history.setUserName(user.getName());
        history.setMobileNo(user.getMobileNo());
        history.setAmount(pointsDTO.getAmount());
        history.setBalance(saved.getAmount());
        history.setPaymode("ONLINE"); // Or from DTO
        history.setCreatedBy("Admin"); // Or from auth/session

        historyRepository.save(history);
        return convertToDTO(saved);
    }


    @Override
    public List<PointsDTO> getAllPoints() {
        return pointsRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PointsDTO> getAmountByUserId(int userId) {
        return pointsRepository.findAllByAddUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PointsDTO convertToDTO(Points points) {
        PointsDTO dto = new PointsDTO();
        dto.setId(points.getId());
        dto.setAmount(points.getAmount());
        dto.setTransactionNo(points.getTransactionNo());
        dto.setTransactionDate(points.getTransactionDate());
        dto.setRemarks(points.getRemarks());

        if (points.getAddUser() != null)
        {
            AddUserDTO addUserDTO = new AddUserDTO();
            addUserDTO.setId(points.getAddUser().getId());
            addUserDTO.setName(points.getAddUser().getName());
            addUserDTO.setMobileNo(points.getAddUser().getMobileNo());

            dto.setAddUserDTO(addUserDTO);
        }

        return dto;
    }

    @Override
    public List<TransactionHistory> getAllTransactionHistory() {
        return historyRepository.findAll();
    }

    @Override
    public List<TransactionHistory> getTransactionsByCredit() {
        return historyRepository.findByEntryTypeCredit();
    }

    @Override
    public List<TransactionHistory> getTransactionsByDebit() {
        return historyRepository.findByEntryTypeDedit();
    }
}
