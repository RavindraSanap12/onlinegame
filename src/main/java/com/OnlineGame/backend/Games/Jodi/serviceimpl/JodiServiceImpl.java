package com.OnlineGame.backend.Games.Jodi.serviceimpl;

import com.OnlineGame.backend.Games.Jodi.dto.JodiDTO;
import com.OnlineGame.backend.Games.Jodi.entity.Jodi;
import com.OnlineGame.backend.Games.Jodi.repository.JodiRepository;
import com.OnlineGame.backend.Games.Jodi.service.JodiService;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JodiServiceImpl implements JodiService {

    private final JodiRepository jodiRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public JodiServiceImpl(JodiRepository jodiRepository,
                           AddUserRepository addUserRepository,
                           MainMarketRepository mainMarketRepository,
                           DelhiMarketRepository delhiMarketRepository,
                           StarlineMarketRepository starlineMarketRepository) {
        this.jodiRepository = jodiRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public JodiDTO createJodi(JodiDTO jodiDTO) {
        Jodi jodi = convertToEntity(jodiDTO);
        calculateTotals(jodi); // Calculate totals before saving
        Jodi savedJodi = jodiRepository.save(jodi);
        return convertToDTO(savedJodi);
    }

    @Override
    public JodiDTO updateJodi(int id, JodiDTO jodiDTO) {
        Jodi existingJodi = jodiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jodi not found with id: " + id));

        updateEntityFromDTO(existingJodi, jodiDTO);
        calculateTotals(existingJodi); // Recalculate totals before updating
        Jodi updatedJodi = jodiRepository.save(existingJodi);
        return convertToDTO(updatedJodi);
    }

    @Override
    public void deleteJodi(int id) {
        jodiRepository.deleteById(id);
    }

    @Override
    public JodiDTO getJodiById(int id) {
        Jodi jodi = jodiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jodi not found with id: " + id));
        return convertToDTO(jodi);
    }

    @Override
    public List<JodiDTO> findByUser(int userId) {
        List<Jodi> jodis = jodiRepository.findByAddUser_Id(userId);
        if (jodis.isEmpty()) {
            throw new RuntimeException("No Jodi entries found for user with id: " + userId);
        }
        return jodis.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JodiDTO> getAllJodis() {
        return jodiRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to calculate total bids and total bid amount
    private void calculateTotals(Jodi jodi) {
        Map<String, Integer> digitValues = jodi.getDigitValues();

        int totalBids = 0;
        double totalBidAmount = 0.0;

        if (digitValues != null) {
            for (Integer value : digitValues.values()) {
                if (value != null && value > 0) {
                    totalBids++;
                    totalBidAmount += value;
                }
            }
        }

        jodi.setTotalBids(totalBids);
        jodi.setTotalBidAmount(totalBidAmount);
    }

    // Helper methods for conversion between DTO and Entity
    private Jodi convertToEntity(JodiDTO dto) {
        Jodi entity = new Jodi();
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
        entity.setDigitValues(dto.getDigitValues());

        // Calculate totals
        calculateTotals(entity);

        return entity;
    }

    private JodiDTO convertToDTO(Jodi entity) {
        JodiDTO dto = new JodiDTO();
        dto.setId(entity.getId());

        if (entity.getAddUser() != null) {
            AddUserDTO userDTO = convertUserToDTO(entity.getAddUser());
            dto.setAddUserDTO(userDTO);
        }

        dto.setDate(entity.getDate());
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

    private void updateEntityFromDTO(Jodi entity, JodiDTO dto) {
        if (dto.getAddUserDTO() != null) {
            AddUser user = addUserRepository.findById(dto.getAddUserDTO().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getAddUserDTO().getId()));
            entity.setAddUser(user);
        }

        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }

        if (dto.getDigitValues() != null) {
            entity.setDigitValues(dto.getDigitValues());
        }

        // Recalculate totals when updating
        calculateTotals(entity);
    }
}