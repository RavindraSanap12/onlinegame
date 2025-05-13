package com.OnlineGame.backend.Games.SPMotor.serviceimpl;

import com.OnlineGame.backend.Games.SPDPTP.service.SPMotorService;
import com.OnlineGame.backend.Games.SPMotor.dto.SPMotorDTO;
import com.OnlineGame.backend.Games.SPMotor.entity.SPMotor;
import com.OnlineGame.backend.Games.SPMotor.repository.SPMotorRepository;
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
public class SPMotorServiceImpl implements SPMotorService {

    private final SPMotorRepository spMotorRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public SPMotorServiceImpl(SPMotorRepository spMotorRepository,
                              AddUserRepository addUserRepository,
                              MainMarketRepository mainMarketRepository,
                              DelhiMarketRepository delhiMarketRepository,
                              StarlineMarketRepository starlineMarketRepository) {
        this.spMotorRepository = spMotorRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public List<SPMotorDTO> saveSPMotor(List<SPMotorDTO> spMotorDTOList) {
        List<SPMotor> spMotorList = spMotorDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        List<SPMotor> savedList = spMotorRepository.saveAll(spMotorList);
        return savedList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SPMotorDTO updateSPMotor(int id, SPMotorDTO spMotorDTO) {
        SPMotor existingSPMotor = spMotorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SPMotor not found with id: " + id));

        updateEntityFromDTO(existingSPMotor, spMotorDTO);
        SPMotor updatedSPMotor = spMotorRepository.save(existingSPMotor);
        return convertToDTO(updatedSPMotor);
    }

    @Override
    public void deleteSPMotor(int id) {
        spMotorRepository.deleteById(id);
    }

    @Override
    public SPMotorDTO getSPMotorById(int id) {
        SPMotor spMotor = spMotorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SPMotor not found with id: " + id));
        return convertToDTO(spMotor);
    }

    @Override
    public List<SPMotorDTO> findByUser(int userId) {
        List<SPMotor> spMotorList = spMotorRepository.findByAddUser_Id(userId);
        if (spMotorList.isEmpty()) {
            throw new RuntimeException("No SPMotor entries found for user with id: " + userId);
        }
        return spMotorList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SPMotorDTO> getAllSPMotor() {
        return spMotorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SPMotor convertToEntity(SPMotorDTO dto) {
        SPMotor entity = new SPMotor();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setDigit(dto.getDigit());
        entity.setPoints(dto.getPoints());
        entity.setGameType(dto.getGameType());

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

        return entity;
    }

    private SPMotorDTO convertToDTO(SPMotor entity) {
        SPMotorDTO dto = new SPMotorDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setDigit(entity.getDigit());
        dto.setPoints(entity.getPoints());
        dto.setGameType(entity.getGameType());

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

    private void updateEntityFromDTO(SPMotor entity, SPMotorDTO dto) {
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

        entity.setDigit(dto.getDigit());
        entity.setPoints(dto.getPoints());
        entity.setGameType(dto.getGameType());
    }
}