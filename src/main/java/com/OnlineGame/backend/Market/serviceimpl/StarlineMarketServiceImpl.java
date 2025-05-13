package com.OnlineGame.backend.Market.serviceimpl;

import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.Market.repository.StarlineMarketRepository;
import com.OnlineGame.backend.Market.service.StarlineMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarlineMarketServiceImpl implements StarlineMarketService {

    @Autowired
    private StarlineMarketRepository starlineMarketRepository;


    @Override
    public StarlineMarketDTO createStarline(StarlineMarketDTO starlineDTO) {
        StarlineMarket starlineMarket = convertToEntity(starlineDTO);
        StarlineMarket savedStarline = starlineMarketRepository.save(starlineMarket);
        return convertToDTO(savedStarline);
    }

    @Override
    public Optional<StarlineMarketDTO> getStarlineById(int id) {
        return starlineMarketRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<StarlineMarketDTO> getAllStarlines() {
        return starlineMarketRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StarlineMarket> getActiveMarkets() {
        return starlineMarketRepository.findByStatusTrue();
    }

    @Override
    public void changeStarlineMarketStatus(int id, boolean status) {
        Optional<StarlineMarket> optionalMarket = starlineMarketRepository.findById(id);
        if (optionalMarket.isPresent()) {
            StarlineMarket market = optionalMarket.get();
            market.setStatus(status);  // assuming 'status' is a field in the entity
            starlineMarketRepository.save(market);
        } else {
            throw new RuntimeException("Starline Market with ID " + id + " not found.");
        }
    }

    @Override
    public StarlineMarketDTO updateStarline(int id, StarlineMarketDTO starlineDTO) {
        return starlineMarketRepository.findById(id)
                .map(existingStarline -> {
                    // Update all fields from DTO to entity
                    existingStarline.setMarketType(starlineDTO.getMarketType());
                    existingStarline.setTitle(starlineDTO.getTitle());
                    existingStarline.setCloseMin(starlineDTO.getCloseMin());
                    existingStarline.setStatus(true);
                    existingStarline.setHighlight(starlineDTO.isHighlight());
                    existingStarline.setShowInApp(starlineDTO.isShowInApp());
                    existingStarline.setShowInWeb(starlineDTO.isShowInWeb());

                    // Update weekdays
                    existingStarline.setMonday(starlineDTO.isMonday());
                    existingStarline.setTuesday(starlineDTO.isTuesday());
                    existingStarline.setWednesday(starlineDTO.isWednesday());
                    existingStarline.setThursday(starlineDTO.isThursday());
                    existingStarline.setFriday(starlineDTO.isFriday());
                    existingStarline.setSaturday(starlineDTO.isSaturday());
                    existingStarline.setSunday(starlineDTO.isSunday());

                    // Update time slots
                    existingStarline.setTimeSlots(new HashSet<>(starlineDTO.getTimeSlots()));

                    StarlineMarket updatedStarline = starlineMarketRepository.save(existingStarline);
                    return convertToDTO(updatedStarline);
                })
                .orElseThrow(() -> new RuntimeException("Starline market not found with id: " + id));
    }

    @Override
    public void deleteStarline(int id) {
        starlineMarketRepository.deleteById(id);
    }

    // Helper methods for conversion between Entity and DTO
    private StarlineMarketDTO convertToDTO(StarlineMarket starlineMarket) {
        StarlineMarketDTO dto = new StarlineMarketDTO();
        dto.setId(starlineMarket.getId());
        dto.setMarketType(starlineMarket.getMarketType());
        dto.setTitle(starlineMarket.getTitle());
        dto.setStatus(starlineMarket.getStatus());
        dto.setCloseMin(starlineMarket.getCloseMin());
        dto.setHighlight(starlineMarket.isHighlight());
        dto.setShowInApp(starlineMarket.isShowInApp());
        dto.setShowInWeb(starlineMarket.isShowInWeb());

        // Set weekdays
        dto.setMonday(starlineMarket.isMonday());
        dto.setTuesday(starlineMarket.isTuesday());
        dto.setWednesday(starlineMarket.isWednesday());
        dto.setThursday(starlineMarket.isThursday());
        dto.setFriday(starlineMarket.isFriday());
        dto.setSaturday(starlineMarket.isSaturday());
        dto.setSunday(starlineMarket.isSunday());

        // Set time slots
        dto.setTimeSlots(new HashSet<>(starlineMarket.getTimeSlots()));

        return dto;
    }

    private StarlineMarket convertToEntity(StarlineMarketDTO dto) {
        StarlineMarket entity = new StarlineMarket();
        entity.setId(dto.getId());
        entity.setMarketType(dto.getMarketType());
        entity.setTitle(dto.getTitle());
        entity.setStatus(true);
        entity.setCloseMin(dto.getCloseMin());
        entity.setHighlight(dto.isHighlight());
        entity.setShowInApp(dto.isShowInApp());
        entity.setShowInWeb(dto.isShowInWeb());

        // Set weekdays
        entity.setMonday(dto.isMonday());
        entity.setTuesday(dto.isTuesday());
        entity.setWednesday(dto.isWednesday());
        entity.setThursday(dto.isThursday());
        entity.setFriday(dto.isFriday());
        entity.setSaturday(dto.isSaturday());
        entity.setSunday(dto.isSunday());

        // Set time slots
        entity.setTimeSlots(new HashSet<>(dto.getTimeSlots()));

        return entity;
    }
}