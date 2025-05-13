package com.OnlineGame.backend.Games.DPMotor.service;

import com.OnlineGame.backend.Games.DPMotor.dto.DPMotorDTO;

import java.util.List;

public interface DPMotorService {

    List<DPMotorDTO> saveDPMotor(List<DPMotorDTO> dpMotorDTOList);
    DPMotorDTO updateDPMotor(int id, DPMotorDTO dpMotorDTO);
    void deleteDPMotor(int id);
    DPMotorDTO getDPMotorById(int id);
    List<DPMotorDTO> findByUser(int userId);
    List<DPMotorDTO> getAllDPMotor();

}