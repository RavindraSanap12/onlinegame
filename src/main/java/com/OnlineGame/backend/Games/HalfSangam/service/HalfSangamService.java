package com.OnlineGame.backend.Games.HalfSangam.service;

import com.OnlineGame.backend.Games.HalfSangam.dto.HalfSangamDTO;

import java.util.List;

public interface HalfSangamService {
    HalfSangamDTO saveHalfSangam(HalfSangamDTO halfSangamDTO);
    HalfSangamDTO findById(int id);
    List<HalfSangamDTO> findByUser(int userId);
    List<HalfSangamDTO> findAllHalfSangam();
    HalfSangamDTO updateHalfSangam(int id, HalfSangamDTO halfSangamDTO);
    void deleteHalfSangam(int id);

}
