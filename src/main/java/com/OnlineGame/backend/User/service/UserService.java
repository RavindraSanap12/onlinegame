package com.OnlineGame.backend.User.service;

import com.OnlineGame.backend.User.dto.AddUserDTO;
import java.util.List;

public interface UserService {

    AddUserDTO addUser(AddUserDTO addUserDTO);

    List<AddUserDTO> getAllUsers();

    AddUserDTO getUserById(int id);

    AddUserDTO updateUser(int id, AddUserDTO addUserDTO);

    void deleteUser(int id);

    AddUserDTO updateUserBooleans(int id, AddUserDTO addUserDTO);

    AddUserDTO login(String mobileNo, String password);

    List<AddUserDTO> findByStatusTrue();

    List<AddUserDTO> findByStatusFalse();

}
