package com.OnlineGame.backend.Market.serviceimpl;

import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.repository.MainMarketRepository;
import com.OnlineGame.backend.Market.service.MainMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MainMarketServiceImpl implements MainMarketService {

    @Autowired
    private MainMarketRepository mainMarketRepository;

    @Override
    public MainMarketDTO saveMainMarket(MainMarketDTO mainMarketDTO) {
        MainMarket saved = mainMarketRepository.save(convertToEntity(mainMarketDTO));
        return convertToDTO(saved);
    }

    @Override
    public Optional<MainMarketDTO> getMainMarketById(int id) {
        return mainMarketRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<MainMarketDTO> getAllMainMarkets() {
        return mainMarketRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MainMarket> getActiveMarkets() {
        return mainMarketRepository.findByStatusTrue();
    }

    @Override
    public MainMarketDTO updateMainMarket(int id, MainMarketDTO mainMarketDTO) {
        MainMarket existing = mainMarketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MainMarket not found with ID: " + id));

        updateEntityFromDto(existing, mainMarketDTO);
        MainMarket updated = mainMarketRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deleteMainMarket(int id) {
        mainMarketRepository.deleteById(id);
    }
    @Override
    public void changeMainMarketStatus(int id, boolean status) {
        Optional<MainMarket> optionalMarket = mainMarketRepository.findById(id);
        if (optionalMarket.isPresent()) {
            MainMarket market = optionalMarket.get();
            market.setStatus(status);  // assuming 'status' is a field in the entity
            mainMarketRepository.save(market);
        } else {
            throw new RuntimeException("Main Market with ID " + id + " not found.");
        }
    }
    // Helper Methods
    private MainMarketDTO convertToDTO(MainMarket entity) {
        MainMarketDTO dto = new MainMarketDTO();
        dto.setMarketId(entity.getMarketId());
        dto.setMarketType(entity.getMarketType());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setOpenTime(entity.getOpenTime());
        dto.setCloseTime(entity.getCloseTime());
        dto.setCloseMin(entity.getCloseMin());
        dto.setHighlight(entity.isHighlight());
        dto.setShowInApp(entity.isShowInApp());
        dto.setShowInWeb(entity.isShowInWeb());
        dto.setMonday(entity.isMonday());
        dto.setTuesday(entity.isTuesday());
        dto.setWednesday(entity.isWednesday());
        dto.setThursday(entity.isThursday());
        dto.setFriday(entity.isFriday());
        dto.setSaturday(entity.isSaturday());
        dto.setSunday(entity.isSunday());
        return dto;
    }

    private MainMarket convertToEntity(MainMarketDTO dto) {
        MainMarket entity = new MainMarket();
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(MainMarket entity, MainMarketDTO dto) {
        entity.setMarketType(dto.getMarketType());
        entity.setTitle(dto.getTitle());
        entity.setOpenTime(dto.getOpenTime());
        entity.setCloseTime(dto.getCloseTime());
        entity.setStatus(true);
        entity.setCloseMin(dto.getCloseMin());
        entity.setHighlight(dto.isHighlight());
        entity.setShowInApp(dto.isShowInApp());
        entity.setShowInWeb(dto.isShowInWeb());
        entity.setMonday(dto.isMonday());
        entity.setTuesday(dto.isTuesday());
        entity.setWednesday(dto.isWednesday());
        entity.setThursday(dto.isThursday());
        entity.setFriday(dto.isFriday());
        entity.setSaturday(dto.isSaturday());
        entity.setSunday(dto.isSunday());
    }
}