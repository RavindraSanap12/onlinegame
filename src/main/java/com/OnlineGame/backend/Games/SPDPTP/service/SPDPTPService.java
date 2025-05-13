package com.OnlineGame.backend.Games.SPDPTP.service;

import com.OnlineGame.backend.Games.SPDPTP.dto.SPDPTPDTO;

import java.util.List;

public interface SPDPTPService {

    List<SPDPTPDTO> saveAllSPDPTP(List<SPDPTPDTO> spdptpDTOList);
    SPDPTPDTO updateSPDPTP(int id, SPDPTPDTO jodiDTO);
    void deleteSPDPTP(int id);
    SPDPTPDTO getSPDPTPById(int id);
    List<SPDPTPDTO> findByUser(int userId);
    List<SPDPTPDTO> getAllSPDPTP();

}