package com.OnlineGame.backend.GameRates.serviceimpl;

import com.OnlineGame.backend.GameRates.dto.GameConfigDTO;
import com.OnlineGame.backend.GameRates.dto.GameRatesDTO;
import com.OnlineGame.backend.GameRates.entity.GameConfig;
import com.OnlineGame.backend.GameRates.entity.GameRates;
import com.OnlineGame.backend.GameRates.repository.GameConfigRepository;
import com.OnlineGame.backend.GameRates.repository.GameRatesRepository;
import com.OnlineGame.backend.GameRates.service.GameRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameRatesServiceImpl implements GameRatesService {

    private final GameRatesRepository gameRatesRepository;
    private final GameConfigRepository gameConfigRepository; // Add this repository

    @Autowired
    public GameRatesServiceImpl(GameRatesRepository gameRatesRepository,
                                GameConfigRepository gameConfigRepository) {
        this.gameRatesRepository = gameRatesRepository;
        this.gameConfigRepository = gameConfigRepository;
    }

    @Override
    public List<GameRatesDTO> saveAll(List<GameRatesDTO> gameRatesDTOList) {
        List<GameRatesDTO> result = new ArrayList<>();

        for (GameRatesDTO dto : gameRatesDTOList) {
            GameRates gameRates;
            if (dto.getId() != 0) {
                // Try to find existing GameRates
                gameRates = gameRatesRepository.findById(dto.getId())
                        .orElseGet(() -> createNewGameRates(dto));
            } else {
                // Try to find by name if no ID provided
                gameRates = gameRatesRepository.findByName(dto.getName())
                        .orElseGet(() -> createNewGameRates(dto));
            }

            // Update the entity
            updateGameRatesFromDTO(gameRates, dto);

            // Save and add to result
            GameRates saved = gameRatesRepository.save(gameRates);
            result.add(convertToDTO(saved));
        }

        return result;
    }


    @Override
    public List<GameRatesDTO> getAll() {
        List<GameRates> gameRatesList = gameRatesRepository.findAll();
        List<GameRatesDTO> result = new ArrayList<>();

        for (GameRates gameRates : gameRatesList) {
            result.add(convertToDTO(gameRates));
        }

        return result;
    }

    @Override
    public GameRatesDTO getById(int id) {
        return gameRatesRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<GameRatesDTO> updateAll(List<GameRatesDTO> gameRatesDTOList) {
        if (gameRatesDTOList == null) {
            throw new IllegalArgumentException("Game rates list cannot be null");
        }

        List<GameRatesDTO> result = new ArrayList<>();

        for (GameRatesDTO dto : gameRatesDTOList) {
            if (dto == null) {
                continue;
            }

            if (dto.getId() == 0) {
                throw new IllegalArgumentException("Cannot update GameRates without ID");
            }

            GameRates gameRates = gameRatesRepository.findById(dto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("GameRates not found with id: " + dto.getId()));

            updateGameRatesFromDTO(gameRates, dto);
            GameRates saved = gameRatesRepository.save(gameRates);
            result.add(convertToDTO(saved));
        }

        return result;
    }

    @Override
    public void deleteById(int id) {
        gameRatesRepository.deleteById(id);
    }

    public void deleteAll() {
        gameRatesRepository.deleteAll();
    }

    private GameRates createNewGameRates(GameRatesDTO dto) {
        GameRates gameRates = new GameRates();
        gameRates.setName(dto.getName());
        gameRates.setGameConfigs(new ArrayList<>());
        return gameRates;
    }

    private void updateGameRatesFromDTO(GameRates gameRates, GameRatesDTO dto) {
        gameRates.setName(dto.getName());

        // Handle GameConfig updates
        if (dto.getGameConfigs() != null) {
            // First handle updates and new configs
            for (GameConfigDTO configDTO : dto.getGameConfigs()) {
                GameConfig config;
                if (configDTO.getId() != 0) {
                    // Try to find existing config
                    config = gameConfigRepository.findById(configDTO.getId())
                            .orElse(new GameConfig());
                } else {
                    // Create new config
                    config = new GameConfig();
                }

                // Update config properties
                config.setId(configDTO.getId());
                config.setGameType(configDTO.getGameType());
                config.setGameRate(configDTO.getGameRate());
                config.setMinBid(configDTO.getMinBid());
                config.setMaxBid(configDTO.getMaxBid());
                config.setGameRates(gameRates);

                // Add to GameRates if not already present
                if (!gameRates.getGameConfigs().contains(config)) {
                    gameRates.getGameConfigs().add(config);
                }
            }

            // Handle deletions (configs present in DB but not in DTO)
            List<GameConfig> configsToRemove = new ArrayList<>();
            for (GameConfig existingConfig : gameRates.getGameConfigs()) {
                boolean foundInDTO = dto.getGameConfigs().stream()
                        .anyMatch(dtoConfig -> dtoConfig.getId() == existingConfig.getId());

                if (!foundInDTO) {
                    configsToRemove.add(existingConfig);
                }
            }
            gameRates.getGameConfigs().removeAll(configsToRemove);
        }
    }
    private GameRatesDTO convertToDTO(GameRates gameRates) {
        if (gameRates == null) {
            return null;
        }

        GameRatesDTO dto = new GameRatesDTO();
        dto.setId(gameRates.getId());
        dto.setName(gameRates.getName());

        List<GameConfigDTO> configDTOs = new ArrayList<>();
        if (gameRates.getGameConfigs() != null) {
            for (GameConfig config : gameRates.getGameConfigs()) {
                if (config != null) {
                    GameConfigDTO configDTO = new GameConfigDTO();
                    configDTO.setId(config.getId());
                    configDTO.setGameType(config.getGameType());
                    configDTO.setGameRate(config.getGameRate());
                    configDTO.setMinBid(config.getMinBid());
                    configDTO.setMaxBid(config.getMaxBid());
                    configDTOs.add(configDTO);
                }
            }
        }

        dto.setGameConfigs(configDTOs);
        return dto;
    }
}