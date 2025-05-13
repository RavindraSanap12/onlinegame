package com.OnlineGame.backend.Games.TripplePatti.service;

import com.OnlineGame.backend.Games.TripplePatti.dto.TripplePattiDTO;

import java.util.List;

public interface TripplePattiService {
    TripplePattiDTO saveTripplePatti(TripplePattiDTO tripplePattiDTO);
    TripplePattiDTO findById(int id);
    List<TripplePattiDTO> findByUser(int userId);
    List<TripplePattiDTO> findAllTripplePatti();
    TripplePattiDTO updateTripplePatti(int id, TripplePattiDTO tripplePattiDTO);
    void deleteTripplePatti(int id);

}
