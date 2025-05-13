package com.OnlineGame.backend.Games.FullSangam.service;

import com.OnlineGame.backend.Games.FullSangam.dto.FullSangamDTO;
import com.OnlineGame.backend.Games.HalfSangam.dto.HalfSangamDTO;

import java.util.List;

public interface FullSangamService {
    FullSangamDTO saveFullSangam(FullSangamDTO fullSangamDTO);
    FullSangamDTO findById(int id);
    List<FullSangamDTO> findByUser(int userId);
    List<FullSangamDTO> findAllFullSangam();
    FullSangamDTO updateFullSangam(int id, FullSangamDTO fullSangamDTO);
    void deleteFullSangam(int id);

}
