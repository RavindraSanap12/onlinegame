package com.OnlineGame.backend.Games.SinglePatti.service;

import com.OnlineGame.backend.Games.SinglePatti.dto.SinglePattiDTO;

import java.util.List;

public interface SinglePattiService {
    SinglePattiDTO createSinglePatti(SinglePattiDTO singlePattiDTO);
    SinglePattiDTO updateSinglePatti(int id, SinglePattiDTO singlePattiDTO);
    void deleteSinglePatti(int id);
    SinglePattiDTO getSinglePattiById(int id);
    List<SinglePattiDTO> getAllSinglePattis();
    List<SinglePattiDTO> findByUser(int userId);

}
