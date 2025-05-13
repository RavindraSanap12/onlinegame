package com.OnlineGame.backend.Games.Jodi.service;

import com.OnlineGame.backend.Games.Jodi.dto.JodiDTO;
import java.util.List;

public interface JodiService {

    JodiDTO createJodi(JodiDTO jodiDTO);
    JodiDTO updateJodi(int id, JodiDTO jodiDTO);
    void deleteJodi(int id);
    JodiDTO getJodiById(int id);
    List<JodiDTO> findByUser(int userId);
    List<JodiDTO> getAllJodis();

}