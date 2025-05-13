package com.OnlineGame.backend.User.serviceimpl;

import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.entity.AddUser;
import com.OnlineGame.backend.User.repository.AddUserRepository;
import com.OnlineGame.backend.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AddUserRepository addUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Add User
    @Override
    public AddUserDTO addUser(AddUserDTO addUserDTO) {
        AddUser addUser = mapToEntity(addUserDTO);
        addUser.setPassword(passwordEncoder.encode(addUserDTO.getPassword()));
        AddUser savedUser = addUserRepository.save(addUser);
        return mapToDTO(savedUser);
    }

    // Get All Users
    @Override
    public List<AddUserDTO> getAllUsers() {
        List<AddUser> users = addUserRepository.findAll();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddUserDTO> findByStatusTrue() {
        List<AddUser> users = addUserRepository.findByStatusTrue();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddUserDTO> findByStatusFalse() {
        List<AddUser> users = addUserRepository.findByStatusFalse();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get User by ID
    @Override
    public AddUserDTO getUserById(int id) {
        Optional<AddUser> optionalUser = addUserRepository.findById(id);
        return optionalUser.map(this::mapToDTO).orElse(null);
    }

    @Override
    public AddUserDTO login(String mobileNo, String password) {
        Optional<AddUser> optionalUser = addUserRepository.findByMobileNoAndPassword(mobileNo, password);
        return optionalUser.map(this::mapToDTO).orElse(null);
    }


    // Update User
    @Override
    public AddUserDTO updateUser(int id, AddUserDTO addUserDTO) {
        Optional<AddUser> optionalUser = addUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            AddUser addUser = optionalUser.get();
            addUser.setName(addUserDTO.getName());
            addUser.setMobileNo(addUserDTO.getMobileNo());
            addUser.setPassword(addUserDTO.getPassword());
            addUser.setAmount(addUserDTO.getAmount());
            addUser.setCustomWithdraw(addUserDTO.getCustomWithdraw());
            addUser.setCustomRate(addUserDTO.getCustomRate());
            addUser.setCustomClose(addUserDTO.getCustomClose());
            addUser.setStatus(addUserDTO.getStatus());

            AddUser updatedUser = addUserRepository.save(addUser);
            return mapToDTO(updatedUser);
        }
        return null;
    }
    @Override
    public AddUserDTO updateUserBooleans(int id, AddUserDTO addUserDTO) {
        Optional<AddUser> optionalUser = addUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            AddUser user = optionalUser.get();

            // Only update the boolean fields
            user.setCustomWithdraw(addUserDTO.getCustomWithdraw());
            user.setCustomRate(addUserDTO.getCustomRate());
            user.setCustomClose(addUserDTO.getCustomClose());
            user.setStatus(addUserDTO.getStatus());

            AddUser updatedUser = addUserRepository.save(user);
            return mapToDTO(updatedUser);
        }
        return null;
    }

    // Delete User
    @Override
    public void deleteUser(int id) {
        addUserRepository.deleteById(id);
    }

    // Mapping Methods
    private AddUserDTO mapToDTO(AddUser addUser) {
        AddUserDTO dto = new AddUserDTO();
        dto.setId(addUser.getId());
        dto.setName(addUser.getName());
        dto.setMobileNo(addUser.getMobileNo());
        dto.setAmount(addUser.getAmount());
        dto.setPassword(addUser.getPassword());
        dto.setCustomWithdraw(addUser.getCustomWithdraw());
        dto.setRegisterDate(addUser.getRegisterDate());
        dto.setRegisterTime(addUser.getRegisterTime());
        dto.setCustomRate(addUser.getCustomRate());
        dto.setCustomClose(addUser.getCustomClose());
        dto.setStatus(addUser.getStatus());
        return dto;
    }

    private AddUser mapToEntity(AddUserDTO dto) {
        AddUser addUser = new AddUser();
        addUser.setName(dto.getName());
        addUser.setMobileNo(dto.getMobileNo());
        addUser.setPassword(dto.getPassword());
        addUser.setAmount(dto.getAmount());
        addUser.setRegisterDate(LocalDate.now());
        addUser.setRegisterTime(LocalTime.now());
        addUser.setCustomWithdraw(dto.getCustomWithdraw());
        addUser.setCustomRate(dto.getCustomRate());
        addUser.setCustomClose(dto.getCustomClose());
        addUser.setStatus(true);
        return addUser;
    }
}
