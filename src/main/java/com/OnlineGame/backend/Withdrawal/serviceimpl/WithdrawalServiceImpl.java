package com.OnlineGame.backend.Withdrawal.serviceimpl;

import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.entity.AddUser;
import com.OnlineGame.backend.User.repository.AddUserRepository;
import com.OnlineGame.backend.Withdrawal.dto.WithdrawalDTO;
import com.OnlineGame.backend.Withdrawal.entity.Withdrawal;
import com.OnlineGame.backend.Withdrawal.repository.WithdrawalRepository;
import com.OnlineGame.backend.Withdrawal.service.WithdrawalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private AddUserRepository addUserRepository;

    @Override
    @Transactional
    public WithdrawalDTO saveWithdrawal(WithdrawalDTO withdrawalDTO) {
        // Validate input
        if (withdrawalDTO == null || withdrawalDTO.getAddUserDTO() == null) {
            throw new IllegalArgumentException("WithdrawalDTO or AddUserDTO cannot be null");
        }

        // Find user
        Optional<AddUser> optionalUser = addUserRepository.findById(withdrawalDTO.getAddUserDTO().getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + withdrawalDTO.getAddUserDTO().getId());
        }

        AddUser user = optionalUser.get();

        // Check if user has sufficient balance
        if (user.getAmount() < withdrawalDTO.getAmount()) {
            throw new RuntimeException("Insufficient balance for withdrawal");
        }

        // Update user's amount
        user.setAmount(user.getAmount() - withdrawalDTO.getAmount());
        addUserRepository.save(user);

        // Create withdrawal
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setDate(LocalDate.now());
        withdrawal.setAddUser(user);
        withdrawal.setPaymentMethod(withdrawalDTO.getPaymentMethod());
        withdrawal.setAmount(withdrawalDTO.getAmount());

        Withdrawal savedWithdrawal = withdrawalRepository.save(withdrawal);

        return convertToDTO(savedWithdrawal);
    }

    @Override
    public WithdrawalDTO findById(int id) {
        Optional<Withdrawal> withdrawal = withdrawalRepository.findById(id);
        if (withdrawal.isEmpty()) {
            throw new RuntimeException("Withdrawal not found with ID: " + id);
        }
        return convertToDTO(withdrawal.get());
    }

    @Override
    public List<WithdrawalDTO> findByUser(int userId) {
        return withdrawalRepository.findByAddUser_Id(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<WithdrawalDTO> findAllWithdrawal() {
        return withdrawalRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public WithdrawalDTO updateWithdrawal(int id, WithdrawalDTO withdrawalDTO) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(id);
        if (optionalWithdrawal.isEmpty()) {
            throw new RuntimeException("Withdrawal not found with ID: " + id);
        }

        Withdrawal existingWithdrawal = optionalWithdrawal.get();
        AddUser user = existingWithdrawal.getAddUser();

        // Calculate the difference in amount
        double amountDifference = withdrawalDTO.getAmount() - existingWithdrawal.getAmount();

        // Check if user has sufficient balance if amount is increased
        if (amountDifference > 0 && user.getAmount() < amountDifference) {
            throw new RuntimeException("Insufficient balance for withdrawal update");
        }

        // Update user's amount
        user.setAmount(user.getAmount() - amountDifference);
        addUserRepository.save(user);

        // Update withdrawal
        existingWithdrawal.setDate(withdrawalDTO.getDate());
        existingWithdrawal.setPaymentMethod(withdrawalDTO.getPaymentMethod());
        existingWithdrawal.setAmount(withdrawalDTO.getAmount());

        Withdrawal updatedWithdrawal = withdrawalRepository.save(existingWithdrawal);
        return convertToDTO(updatedWithdrawal);
    }

    @Override
    @Transactional
    public void deleteWithdrawal(int id) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(id);
        if (optionalWithdrawal.isEmpty()) {
            throw new RuntimeException("Withdrawal not found with ID: " + id);
        }

        Withdrawal withdrawal = optionalWithdrawal.get();
        AddUser user = withdrawal.getAddUser();

        // Return the amount to user
        user.setAmount(user.getAmount() + withdrawal.getAmount());
        addUserRepository.save(user);

        // Delete the withdrawal
        withdrawalRepository.delete(withdrawal);
    }

    private WithdrawalDTO convertToDTO(Withdrawal withdrawal) {
        WithdrawalDTO dto = new WithdrawalDTO();
        dto.setId(withdrawal.getId());
        dto.setDate(withdrawal.getDate());
        dto.setPaymentMethod(withdrawal.getPaymentMethod());
        dto.setAmount(withdrawal.getAmount());

        if (withdrawal.getAddUser() != null) {
            AddUserDTO userDTO = new AddUserDTO();
            userDTO.setId(withdrawal.getAddUser().getId());
            userDTO.setName(withdrawal.getAddUser().getName());
            userDTO.setMobileNo(withdrawal.getAddUser().getMobileNo());
            userDTO.setAmount(withdrawal.getAddUser().getAmount());
            dto.setAddUserDTO(userDTO);
        }

        return dto;
    }
}