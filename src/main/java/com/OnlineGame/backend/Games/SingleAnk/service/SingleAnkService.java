package com.OnlineGame.backend.Games.SingleAnk.service;

import com.OnlineGame.backend.Games.SingleAnk.dto.SingleAnkDTO;

import java.util.List;

public interface SingleAnkService {
    SingleAnkDTO saveSingleAnk(SingleAnkDTO singleAnkDTO);
    SingleAnkDTO findById(int id);
    List<SingleAnkDTO> findByUser(int userId);
    List<SingleAnkDTO> findAllSingleAnk();
    SingleAnkDTO updateSingleAnk(int id, SingleAnkDTO singleAnkDTO);
    void deleteSingleAnk(int id);

}
