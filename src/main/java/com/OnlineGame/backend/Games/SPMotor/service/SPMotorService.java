package com.OnlineGame.backend.Games.SPDPTP.service;

import com.OnlineGame.backend.Games.SPDPTP.dto.SPDPTPDTO;
import com.OnlineGame.backend.Games.SPMotor.dto.SPMotorDTO;

import java.util.List;

public interface SPMotorService {

    List<SPMotorDTO> saveSPMotor(List<SPMotorDTO> spMotorDTOList);
    SPMotorDTO updateSPMotor(int id, SPMotorDTO spMotorDTO);
    void deleteSPMotor(int id);
    SPMotorDTO getSPMotorById(int id);
    List<SPMotorDTO> findByUser(int userId);
    List<SPMotorDTO> getAllSPMotor();

}