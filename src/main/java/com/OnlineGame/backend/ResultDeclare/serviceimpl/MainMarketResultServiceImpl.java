package com.OnlineGame.backend.ResultDeclare.serviceimpl;

import com.OnlineGame.backend.ResultDeclare.dto.MainMarketResultDTO;
import com.OnlineGame.backend.ResultDeclare.entity.MainMarketResult;
import com.OnlineGame.backend.ResultDeclare.repository.MainMarketResultRepository;
import com.OnlineGame.backend.ResultDeclare.service.MainMarketResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainMarketResultServiceImpl implements MainMarketResultService {

    @Autowired
    private MainMarketResultRepository resultRepository;

    @Override
    public MainMarketResultDTO addResult(MainMarketResultDTO dto) {
        MainMarketResult entity = mapToEntity(dto);
        MainMarketResult saved = resultRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public MainMarketResultDTO updateResult(int id, MainMarketResultDTO dto) {
        Optional<MainMarketResult> optional = resultRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Result not found with id: " + id);
        }

        MainMarketResult entity = optional.get();
        entity.setResultDate(dto.getResultDate());
        entity.setGame(dto.getGame());
        entity.setSession(dto.getSession());
        entity.setHighlight(dto.getHighlight());
        entity.setOpenPanna(dto.getOpenPanna());
        entity.setOpenAnk(dto.getOpenAnk());
        entity.setClosePanna(dto.getClosePanna());
        entity.setCloseAnk(dto.getCloseAnk());
        entity.setJodi(dto.getJodi());
        entity.setSendNotification(dto.isSendNotification());

        MainMarketResult updated = resultRepository.save(entity);
        return mapToDTO(updated);
    }

    @Override
    public MainMarketResultDTO getResultById(int id) {
        MainMarketResult entity = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<MainMarketResultDTO> getAllResults() {
        return resultRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteResult(int id) {
        if (!resultRepository.existsById(id)) {
            throw new RuntimeException("Result not found with id: " + id);
        }
        resultRepository.deleteById(id);
    }

    private MainMarketResultDTO mapToDTO(MainMarketResult entity) {
        MainMarketResultDTO dto = new MainMarketResultDTO();
        dto.setId(entity.getId());
        dto.setResultDate(entity.getResultDate());
        dto.setGame(entity.getGame());
        dto.setSession(entity.getSession());
        dto.setHighlight(entity.getHighlight());
        dto.setOpenPanna(entity.getOpenPanna());
        dto.setOpenAnk(entity.getOpenAnk());
        dto.setClosePanna(entity.getClosePanna());
        dto.setCloseAnk(entity.getCloseAnk());
        dto.setJodi(entity.getJodi());
        dto.setSendNotification(entity.isSendNotification());
        return dto;
    }

    private MainMarketResult mapToEntity(MainMarketResultDTO dto) {
        MainMarketResult entity = new MainMarketResult();
        entity.setId(dto.getId());
        entity.setResultDate(dto.getResultDate());
        entity.setGame(dto.getGame());
        entity.setSession(dto.getSession());
        entity.setHighlight(dto.getHighlight());
        entity.setOpenPanna(dto.getOpenPanna());
        entity.setOpenAnk(dto.getOpenAnk());
        entity.setClosePanna(dto.getClosePanna());
        entity.setCloseAnk(dto.getCloseAnk());
        entity.setJodi(dto.getJodi());
        entity.setSendNotification(dto.isSendNotification());
        return entity;
    }
}
