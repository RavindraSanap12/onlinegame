package com.OnlineGame.backend.Games.HalfSangam.serviceimpl;

import com.OnlineGame.backend.Games.HalfSangam.dto.HalfSangamDTO;
import com.OnlineGame.backend.Games.HalfSangam.entity.HalfSangam;
import com.OnlineGame.backend.Games.HalfSangam.repository.HalfSangamRepository;
import com.OnlineGame.backend.Games.HalfSangam.service.HalfSangamService;
import com.OnlineGame.backend.Market.dto.DelhiMarketDTO;
import com.OnlineGame.backend.Market.dto.MainMarketDTO;
import com.OnlineGame.backend.Market.dto.StarlineMarketDTO;
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
import java.util.stream.Collectors;

@Service
public class HalfSangamServiceImpl implements HalfSangamService {

    private final HalfSangamRepository halfSangamRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public HalfSangamServiceImpl(HalfSangamRepository halfSangamRepository,
                                 AddUserRepository addUserRepository,
                                 MainMarketRepository mainMarketRepository,
                                 DelhiMarketRepository delhiMarketRepository,
                                 StarlineMarketRepository starlineMarketRepository) {
        this.halfSangamRepository = halfSangamRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public HalfSangamDTO saveHalfSangam(HalfSangamDTO halfSangamDTO) {
        HalfSangam halfSangam = convertToEntity(halfSangamDTO);
        HalfSangam savedHalfSangam = halfSangamRepository.save(halfSangam);
        return convertToDTO(savedHalfSangam);
    }

    @Override
    public HalfSangamDTO findById(int id) {
        HalfSangam halfSangam = halfSangamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HalfSangam not found with id: " + id));
        return convertToDTO(halfSangam);
    }

    @Override
    public List<HalfSangamDTO> findByUser(int userId) {
        List<HalfSangam> halfSangams = halfSangamRepository.findByAddUser_Id(userId);
        if (halfSangams.isEmpty()) {
            throw new RuntimeException("No HalfSangam entries found for user with id: " + userId);
        }
        return halfSangams.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HalfSangamDTO> findAllHalfSangam() {
        return halfSangamRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HalfSangamDTO updateHalfSangam(int id, HalfSangamDTO halfSangamDTO) {
        HalfSangam existingHalfSangam = halfSangamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HalfSangam not found with id: " + id));

        updateEntityFromDTO(existingHalfSangam, halfSangamDTO);
        HalfSangam updatedHalfSangam = halfSangamRepository.save(existingHalfSangam);
        return convertToDTO(updatedHalfSangam);
    }

    @Override
    public void deleteHalfSangam(int id) {
        halfSangamRepository.deleteById(id);
    }

    // Helper methods for conversion between DTO and Entity
    private HalfSangam convertToEntity(HalfSangamDTO dto) {
        HalfSangam entity = new HalfSangam();
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
        entity.setOpenPanna(dto.getOpenPanna());
        entity.setCloseDigit(dto.getCloseDigit());
        entity.setPoints(dto.getPoints());

        return entity;
    }

    private HalfSangamDTO convertToDTO(HalfSangam entity) {
        HalfSangamDTO dto = new HalfSangamDTO();
        dto.setId(entity.getId());

        if (entity.getAddUser() != null) {
            AddUserDTO userDTO = convertUserToDTO(entity.getAddUser());
            dto.setAddUserDTO(userDTO);
        }

        if (entity.getMainMarket() != null) {
            MainMarketDTO mainMarketDTO = convertMainMarketToDTO(entity.getMainMarket());
            dto.setMainMarketDTO(mainMarketDTO);
        }

        if (entity.getDelhiMarket() != null) {
            DelhiMarketDTO delhiMarketDTO = convertDelhiMarketToDTO(entity.getDelhiMarket());
            dto.setDelhiMarketDTO(delhiMarketDTO);
        }

        if (entity.getStarlineMarket() != null) {
            StarlineMarketDTO starlineMarketDTO = convertStarlineMarketToDTO(entity.getStarlineMarket());
            dto.setStarlineMarketDTO(starlineMarketDTO);
        }

        dto.setDate(entity.getDate());
        dto.setType(entity.getType());
        dto.setOpenPanna(entity.getOpenPanna());
        dto.setCloseDigit(entity.getCloseDigit());
        dto.setPoints(entity.getPoints());

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

    private MainMarketDTO convertMainMarketToDTO(MainMarket mainMarket) {
        MainMarketDTO dto = new MainMarketDTO();
        dto.setMarketId(mainMarket.getMarketId());
        dto.setMarketType(mainMarket.getMarketType());
        dto.setTitle(mainMarket.getTitle());
        dto.setOpenTime(mainMarket.getOpenTime());
        dto.setCloseTime(mainMarket.getCloseTime());
        dto.setStatus(mainMarket.getStatus());
        return dto;
    }

    private DelhiMarketDTO convertDelhiMarketToDTO(DelhiMarket delhiMarket) {
        DelhiMarketDTO dto = new DelhiMarketDTO();
        dto.setMarketId(delhiMarket.getMarketId());
        dto.setMarketType(delhiMarket.getMarketType());
        dto.setTitle(delhiMarket.getTitle());
        dto.setOpenTime(delhiMarket.getOpenTime());
        dto.setCloseTime(delhiMarket.getCloseTime());
        dto.setStatus(delhiMarket.getStatus());
        return dto;
    }

    private StarlineMarketDTO convertStarlineMarketToDTO(StarlineMarket starlineMarket) {
        StarlineMarketDTO dto = new StarlineMarketDTO();
        dto.setId(starlineMarket.getId());
        dto.setMarketType(starlineMarket.getMarketType());
        dto.setTitle(starlineMarket.getTitle());
        dto.setStatus(starlineMarket.getStatus());
        return dto;
    }

    private void updateEntityFromDTO(HalfSangam entity, HalfSangamDTO dto) {
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

        entity.setOpenPanna(dto.getOpenPanna());
        entity.setCloseDigit(dto.getCloseDigit());
        entity.setPoints(dto.getPoints());
    }
}