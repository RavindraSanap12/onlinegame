package com.OnlineGame.backend.Games.DoublePatti.serviceimpl;

import com.OnlineGame.backend.Games.DoublePatti.dto.DoublePattiDTO;
import com.OnlineGame.backend.Games.DoublePatti.entity.DoublePatti;
import com.OnlineGame.backend.Games.DoublePatti.repository.DoublePattiRepository;
import com.OnlineGame.backend.Games.DoublePatti.service.DoublePattiService;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoublePattiServiceImpl implements DoublePattiService {

    private final DoublePattiRepository doublePattiRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public DoublePattiServiceImpl(DoublePattiRepository doublePattiRepository,
                                  AddUserRepository addUserRepository,
                                  MainMarketRepository mainMarketRepository,
                                  DelhiMarketRepository delhiMarketRepository,
                                  StarlineMarketRepository starlineMarketRepository) {
        this.doublePattiRepository = doublePattiRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public DoublePattiDTO createDoublePatti(DoublePattiDTO doublePattiDTO) {
        validateDigitValues(doublePattiDTO.getDigitValues());
        DoublePatti doublePatti = convertToEntity(doublePattiDTO);
        calculateTotals(doublePatti);
        DoublePatti savedDoublePatti = doublePattiRepository.save(doublePatti);
        return convertToDTO(savedDoublePatti);
    }

    @Override
    public DoublePattiDTO updateDoublePatti(int id, DoublePattiDTO doublePattiDTO) {
        DoublePatti existingDoublePatti = doublePattiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoublePatti not found with id: " + id));

        if (doublePattiDTO.getDigitValues() != null) {
            validateDigitValues(doublePattiDTO.getDigitValues());
        }

        updateEntityFromDTO(existingDoublePatti, doublePattiDTO);
        calculateTotals(existingDoublePatti);
        DoublePatti updatedDoublePatti = doublePattiRepository.save(existingDoublePatti);
        return convertToDTO(updatedDoublePatti);
    }

    @Override
    public void deleteDoublePatti(int id) {
        if (!doublePattiRepository.existsById(id)) {
            throw new RuntimeException("DoublePatti not found with id: " + id);
        }
        doublePattiRepository.deleteById(id);
    }

    @Override
    public DoublePattiDTO getDoublePattiById(int id) {
        DoublePatti doublePatti = doublePattiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoublePatti not found with id: " + id));
        return convertToDTO(doublePatti);
    }

    @Override
    public List<DoublePattiDTO> getAllDoublePattis() {
        return doublePattiRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoublePattiDTO> findByUser(int userId) {
        List<DoublePatti> doublePattis = doublePattiRepository.findByAddUser_Id(userId);
        if (doublePattis.isEmpty()) {
            throw new RuntimeException("No DoublePatti entries found for user with id: " + userId);
        }
        return doublePattis.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void calculateTotals(DoublePatti doublePatti) {
        Map<String, Map<String, Integer>> digitValues = doublePatti.getDigitValues();

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

        doublePatti.setTotalBids(totalBids);
        doublePatti.setTotalBidAmount(totalBidAmount);
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

    private DoublePatti convertToEntity(DoublePattiDTO dto) {
        DoublePatti entity = new DoublePatti();
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

        calculateTotals(entity);

        return entity;
    }

    private DoublePattiDTO convertToDTO(DoublePatti entity) {
        DoublePattiDTO dto = new DoublePattiDTO();
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

    private void updateEntityFromDTO(DoublePatti entity, DoublePattiDTO dto) {
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

        calculateTotals(entity);
    }
}