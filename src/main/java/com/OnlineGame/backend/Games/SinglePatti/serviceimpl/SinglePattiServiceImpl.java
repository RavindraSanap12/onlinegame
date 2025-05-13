package com.OnlineGame.backend.Games.SinglePatti.serviceimpl;

import com.OnlineGame.backend.Games.SinglePatti.dto.SinglePattiDTO;
import com.OnlineGame.backend.Games.SinglePatti.entity.SinglePatti;
import com.OnlineGame.backend.Games.SinglePatti.repository.SinglePattiRepository;
import com.OnlineGame.backend.Games.SinglePatti.service.SinglePattiService;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.Market.repository.DelhiMarketRepository;
import com.OnlineGame.backend.Market.repository.MainMarketRepository;
import com.OnlineGame.backend.Market.repository.StarlineMarketRepository;
import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.User.entity.AddUser;
import com.OnlineGame.backend.User.repository.AddUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SinglePattiServiceImpl implements SinglePattiService {

    private final SinglePattiRepository singlePattiRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public SinglePattiServiceImpl(SinglePattiRepository singlePattiRepository,
                                  AddUserRepository addUserRepository,
                                  MainMarketRepository mainMarketRepository,
                                  DelhiMarketRepository delhiMarketRepository,
                                  StarlineMarketRepository starlineMarketRepository) {
        this.singlePattiRepository = singlePattiRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public SinglePattiDTO createSinglePatti(SinglePattiDTO singlePattiDTO) {
        validateDigitValues(singlePattiDTO.getDigitValues());
        SinglePatti singlePatti = convertToEntity(singlePattiDTO);
        calculateTotals(singlePatti); // Calculate totals before saving
        SinglePatti savedSinglePatti = singlePattiRepository.save(singlePatti);
        return convertToDTO(savedSinglePatti);
    }

    @Override
    public SinglePattiDTO updateSinglePatti(int id, SinglePattiDTO singlePattiDTO) {
        SinglePatti existingSinglePatti = singlePattiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SinglePatti not found with id: " + id));

        if (singlePattiDTO.getDigitValues() != null) {
            validateDigitValues(singlePattiDTO.getDigitValues());
        }

        updateEntityFromDTO(existingSinglePatti, singlePattiDTO);
        calculateTotals(existingSinglePatti); // Recalculate totals before updating
        SinglePatti updatedSinglePatti = singlePattiRepository.save(existingSinglePatti);
        return convertToDTO(updatedSinglePatti);
    }

    @Override
    public void deleteSinglePatti(int id) {
        if (!singlePattiRepository.existsById(id)) {
            throw new RuntimeException("SinglePatti not found with id: " + id);
        }
        singlePattiRepository.deleteById(id);
    }

    @Override
    public SinglePattiDTO getSinglePattiById(int id) {
        SinglePatti singlePatti = singlePattiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SinglePatti not found with id: " + id));
        return convertToDTO(singlePatti);
    }

    @Override
    public List<SinglePattiDTO> getAllSinglePattis() {
        return singlePattiRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SinglePattiDTO> findByUser(int userId) {
        List<SinglePatti> singlePattis = singlePattiRepository.findByAddUser_Id(userId);
        if (singlePattis.isEmpty()) {
            throw new RuntimeException("No SinglePatti entries found for user with id: " + userId);
        }
        return singlePattis.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to calculate total bids and total bid amount
    private void calculateTotals(SinglePatti singlePatti) {
        Map<String, Map<String, Integer>> digitValues = singlePatti.getDigitValues();

        int totalBids = 0;
        double totalBidAmount = 0.0;

        if (digitValues != null) {
            for (Map<String, Integer> innerMap : digitValues.values()) {
                if (innerMap != null) {
                    for (Integer value : innerMap.values()) {
                        if (value != null && value > 0) {
                            totalBids++;
                            totalBidAmount += value;
                        }
                    }
                }
            }
        }

        singlePatti.setTotalBids(totalBids);
        singlePatti.setTotalBidAmount(totalBidAmount);
    }

    private void validateDigitValues(Map<String, Map<String, Integer>> digitValues) {
        if (digitValues == null) {
            throw new IllegalArgumentException("Digit values cannot be null");
        }

        digitValues.forEach((digitKey, innerMap) -> {
            if (innerMap == null) {
                throw new IllegalArgumentException("Inner map for " + digitKey + " cannot be null");
            }

            innerMap.forEach((key, value) -> {
                if (value == null) {
                    throw new IllegalArgumentException("Value for " + digitKey + "/" + key + " cannot be null");
                }
                if (value < 0) {
                    throw new IllegalArgumentException("Value for " + digitKey + "/" + key + " cannot be negative");
                }
            });
        });
    }

    private SinglePatti convertToEntity(SinglePattiDTO dto) {
        SinglePatti entity = new SinglePatti();
        entity.setId(dto.getId());

        if (dto.getAddUserDTO() != null) {
            AddUser user = addUserRepository.findById(dto.getAddUserDTO().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getAddUserDTO().getId()));
            entity.setAddUser(user);
        }

        if (dto.getMainMarketDTO() != null) {
            MainMarket mainMarket = mainMarketRepository.findById(dto.getMainMarketDTO().getMarketId())
                    .orElseThrow(() -> new RuntimeException("Main Market not found with id: " + dto.getMainMarketDTO().getMarketId()));
            entity.setMainMarket(mainMarket);
        }

        if (dto.getDelhiMarketDTO() != null) {
            DelhiMarket delhiMarket = delhiMarketRepository.findById(dto.getDelhiMarketDTO().getMarketId())
                    .orElseThrow(() -> new RuntimeException("Delhi Market not found with id: " + dto.getDelhiMarketDTO().getMarketId()));
            entity.setDelhiMarket(delhiMarket);
        }

        if (dto.getStarlineMarketDTO() != null) {
            StarlineMarket starlineMarket = starlineMarketRepository.findById(dto.getStarlineMarketDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Starline Market not found with id: " + dto.getStarlineMarketDTO().getId()));
            entity.setStarlineMarket(starlineMarket);
        }

        entity.setDate(dto.getDate());
        entity.setType(dto.getType());
        entity.setDigitValues(dto.getDigitValues());

        // Calculate totals
        calculateTotals(entity);

        return entity;
    }

    private SinglePattiDTO convertToDTO(SinglePatti entity) {
        SinglePattiDTO dto = new SinglePattiDTO();
        dto.setId(entity.getId());

        if (entity.getAddUser() != null) {
            AddUserDTO userDTO = convertUserToDTO(entity.getAddUser());
            dto.setAddUserDTO(userDTO);
        }

        dto.setDate(entity.getDate());
        dto.setType(entity.getType());
        dto.setDigitValues(entity.getDigitValues());
        dto.setTotalBids(entity.getTotalBids());
        dto.setTotalBidAmount(entity.getTotalBidAmount());

        return dto;
    }

    private AddUserDTO convertUserToDTO(AddUser user) {
        AddUserDTO userDTO = new AddUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setMobileNo(user.getMobileNo());
        userDTO.setPassword(user.getPassword());
        userDTO.setRegisterDate(user.getRegisterDate());
        userDTO.setRegisterTime(user.getRegisterTime());
        userDTO.setCustomWithdraw(user.getCustomWithdraw());
        userDTO.setCustomRate(user.getCustomRate());
        userDTO.setCustomClose(user.getCustomClose());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

    private void updateEntityFromDTO(SinglePatti entity, SinglePattiDTO dto) {
        if (dto.getAddUserDTO() != null) {
            AddUser user = addUserRepository.findById(dto.getAddUserDTO().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getAddUserDTO().getId()));
            entity.setAddUser(user);
        }

        if (dto.getMainMarketDTO() != null) {
            MainMarket mainMarket = mainMarketRepository.findById(dto.getMainMarketDTO().getMarketId())
                    .orElseThrow(() -> new RuntimeException("Main Market not found with id: " + dto.getMainMarketDTO().getMarketId()));
            entity.setMainMarket(mainMarket);
        }

        if (dto.getDelhiMarketDTO() != null) {
            DelhiMarket delhiMarket = delhiMarketRepository.findById(dto.getDelhiMarketDTO().getMarketId())
                    .orElseThrow(() -> new RuntimeException("Delhi Market not found with id: " + dto.getDelhiMarketDTO().getMarketId()));
            entity.setDelhiMarket(delhiMarket);
        }

        if (dto.getStarlineMarketDTO() != null) {
            StarlineMarket starlineMarket = starlineMarketRepository.findById(dto.getStarlineMarketDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Starline Market not found with id: " + dto.getStarlineMarketDTO().getId()));
            entity.setStarlineMarket(starlineMarket);
        }

        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }

        if (dto.getType() != null) {
            entity.setType(dto.getType());
        }

        if (dto.getDigitValues() != null) {
            entity.setDigitValues(dto.getDigitValues());
        }

        // Recalculate totals when updating
        calculateTotals(entity);
    }
}