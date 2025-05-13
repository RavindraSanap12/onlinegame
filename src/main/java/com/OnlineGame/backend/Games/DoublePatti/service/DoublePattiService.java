package com.OnlineGame.backend.Games.DoublePatti.service;

import com.OnlineGame.backend.Games.DoublePatti.dto.DoublePattiDTO;

import java.util.List;

public interface DoublePattiService {
    DoublePattiDTO createDoublePatti(DoublePattiDTO doublePattiDTO);
    DoublePattiDTO updateDoublePatti(int id, DoublePattiDTO doublePattiDTO);
    void deleteDoublePatti(int id);
    DoublePattiDTO getDoublePattiById(int id);
    List<DoublePattiDTO> getAllDoublePattis();
    List<DoublePattiDTO> findByUser(int userId);

}
