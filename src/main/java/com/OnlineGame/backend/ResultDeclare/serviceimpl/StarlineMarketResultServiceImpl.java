package com.OnlineGame.backend.ResultDeclare.serviceimpl;

import com.OnlineGame.backend.ResultDeclare.dto.StarlineMarketResultDTO;
import com.OnlineGame.backend.ResultDeclare.entity.StarlineMarketResult;
import com.OnlineGame.backend.ResultDeclare.repository.StarlineMarketResultRepository;
import com.OnlineGame.backend.ResultDeclare.service.StarlineMarketResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarlineMarketResultServiceImpl implements StarlineMarketResultService {

    @Autowired
    private StarlineMarketResultRepository repository;

    @Override
    public StarlineMarketResultDTO saveResult(StarlineMarketResultDTO dto) {
        StarlineMarketResult result = mapToEntity(dto);
        result = repository.save(result);
        return mapToDTO(result);
    }

    @Override
    public List<StarlineMarketResultDTO> getAllResults() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StarlineMarketResultDTO getResultById(int id) {
        Optional<StarlineMarketResult> result = repository.findById(id);
        return result.map(this::mapToDTO).orElse(null);
    }

    @Override
    public StarlineMarketResultDTO updateResult(int id, StarlineMarketResultDTO dto) {
        Optional<StarlineMarketResult> existing = repository.findById(id);
        if (existing.isPresent()) {
            StarlineMarketResult result = mapToEntity(dto);
            result.setId(id); // Ensure the ID remains the same
            result = repository.save(result);
            return mapToDTO(result);
        }
        return null;
    }

    @Override
    public void deleteResult(int id) {
        repository.deleteById(id);
    }

    // Helper methods for mapping

    private StarlineMarketResultDTO mapToDTO(StarlineMarketResult entity) {
        StarlineMarketResultDTO dto = new StarlineMarketResultDTO();
        dto.setId(entity.getId());
        dto.setResultDate(entity.getResultDate());
        dto.setGame(entity.getGame());
        dto.setSession(entity.getSession());
        dto.setHighlight(entity.getHighlight());
        dto.setPanna(entity.getPanna());
        dto.setAnk(entity.getAnk());
        dto.setSendNotification(entity.isSendNotification());
        return dto;
    }

    private StarlineMarketResult mapToEntity(StarlineMarketResultDTO dto) {
        StarlineMarketResult entity = new StarlineMarketResult();
        entity.setId(dto.getId());
        entity.setResultDate(dto.getResultDate());
        entity.setGame(dto.getGame());
        entity.setSession(dto.getSession());
        entity.setHighlight(dto.getHighlight());
        entity.setPanna(dto.getPanna());
        entity.setAnk(dto.getAnk());
        entity.setSendNotification(dto.isSendNotification());
        return entity;
    }
}
