package com.OnlineGame.backend.ResultDeclare.serviceimpl;

import com.OnlineGame.backend.ResultDeclare.dto.DelhiMarketResultDTO;
import com.OnlineGame.backend.ResultDeclare.entity.DelhiMarketResult;
import com.OnlineGame.backend.ResultDeclare.repository.DelhiMarketResultRepository;
import com.OnlineGame.backend.ResultDeclare.service.DelhiMarketResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DelhiMarketResultServiceImpl implements DelhiMarketResultService {

    @Autowired
    private DelhiMarketResultRepository resultRepository;

    @Override
    public DelhiMarketResultDTO addResult(DelhiMarketResultDTO dto) {
        DelhiMarketResult entity = mapToEntity(dto);
        DelhiMarketResult savedEntity = resultRepository.save(entity);
        return mapToDTO(savedEntity);
    }

    @Override
    public DelhiMarketResultDTO updateResult(int id, DelhiMarketResultDTO dto) {
        Optional<DelhiMarketResult> optional = resultRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Result not found with id: " + id);
        }

        DelhiMarketResult entity = optional.get();
        entity.setResultDate(dto.getResultDate());
        entity.setGame(dto.getGame());
        entity.setHighlight(dto.getHighlight());
        entity.setJodi(dto.getJodi());
        entity.setSendNotification(dto.isSendNotification());

        DelhiMarketResult updatedEntity = resultRepository.save(entity);
        return mapToDTO(updatedEntity);
    }

    @Override
    public DelhiMarketResultDTO getResultById(int id) {
        DelhiMarketResult entity = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<DelhiMarketResultDTO> getAllResults() {
        List<DelhiMarketResult> results = resultRepository.findAll();
        return results.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteResult(int id) {
        if (!resultRepository.existsById(id)) {
            throw new RuntimeException("Result not found with id: " + id);
        }
        resultRepository.deleteById(id);
    }

    // Mapping DTO to Entity
    private DelhiMarketResult mapToEntity(DelhiMarketResultDTO dto) {
        DelhiMarketResult entity = new DelhiMarketResult();
        entity.setId(dto.getId());
        entity.setResultDate(dto.getResultDate());
        entity.setGame(dto.getGame());
        entity.setHighlight(dto.getHighlight());
        entity.setJodi(dto.getJodi());
        entity.setSendNotification(dto.isSendNotification());
        return entity;
    }

    // Mapping Entity to DTO
    private DelhiMarketResultDTO mapToDTO(DelhiMarketResult entity) {
        DelhiMarketResultDTO dto = new DelhiMarketResultDTO();
        dto.setId(entity.getId());
        dto.setResultDate(entity.getResultDate());
        dto.setGame(entity.getGame());
        dto.setHighlight(entity.getHighlight());
        dto.setJodi(entity.getJodi());
        dto.setSendNotification(entity.isSendNotification());
        return dto;
    }
}
