package com.OnlineGame.backend.Market.serviceimpl;

import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.repository.DelhiMarketRepository;
import com.OnlineGame.backend.Market.service.DelhiMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DelhiMarketServiceImpl implements DelhiMarketService {

    @Autowired
    private DelhiMarketRepository delhiMarketRepository;

    @Override
    public DelhiMarketDTO saveDelhiMarket(DelhiMarketDTO delhiMarketDTO) {
        DelhiMarket saved = delhiMarketRepository.save(convertToEntity(delhiMarketDTO));
        return convertToDTO(saved);
    }

    @Override
    public Optional<DelhiMarketDTO> getDelhiMarketById(int id) {
        return delhiMarketRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<DelhiMarketDTO> getAllMarkets() {
        return delhiMarketRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DelhiMarket> getActiveMarkets() {
        return delhiMarketRepository.findByStatusTrue();
    }

    @Override
    public DelhiMarketDTO updateDelhiMarket(int id, DelhiMarketDTO delhiMarketDTO) {
        DelhiMarket existing = delhiMarketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DelhiMarket not found with ID: " + id));

        updateEntityFromDto(existing, delhiMarketDTO);
        DelhiMarket updated = delhiMarketRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deleteDelhiMarket(int id) {
        delhiMarketRepository.deleteById(id);
    }
    @Override
    public void changeDelhiMarketStatus(int id, boolean status) {
        Optional<DelhiMarket> optionalMarket = delhiMarketRepository.findById(id);
        if (optionalMarket.isPresent()) {
            DelhiMarket market = optionalMarket.get();
            market.setStatus(status);  // assuming 'status' is a field in the entity
            delhiMarketRepository.save(market);
        } else {
            throw new RuntimeException("Delhi Market with ID " + id + " not found.");
        }
    }

    // Helper Methods
    private DelhiMarketDTO convertToDTO(DelhiMarket entity) {
        DelhiMarketDTO dto = new DelhiMarketDTO();
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

    private DelhiMarket convertToEntity(DelhiMarketDTO dto) {
        DelhiMarket entity = new DelhiMarket();
        updateEntityFromDto(entity, dto);
        return entity;
    }

    private void updateEntityFromDto(DelhiMarket entity, DelhiMarketDTO dto) {
        entity.setMarketType(dto.getMarketType());
        entity.setTitle(dto.getTitle());
        entity.setOpenTime(dto.getOpenTime());
        entity.setCloseTime(dto.getCloseTime());
        entity.setCloseMin(dto.getCloseMin());
        entity.setStatus(true);
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