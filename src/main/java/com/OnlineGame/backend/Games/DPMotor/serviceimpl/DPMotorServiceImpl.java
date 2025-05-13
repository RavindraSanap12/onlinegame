package com.OnlineGame.backend.Games.DPMotor.serviceimpl;

import com.OnlineGame.backend.Games.DPMotor.dto.DPMotorDTO;
import com.OnlineGame.backend.Games.DPMotor.entity.DPMotor;
import com.OnlineGame.backend.Games.DPMotor.repository.DPMotorRepository;
import com.OnlineGame.backend.Games.DPMotor.service.DPMotorService;
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
public class DPMotorServiceImpl implements DPMotorService {

    private final DPMotorRepository dpMotorRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public DPMotorServiceImpl(DPMotorRepository dpMotorRepository,
                              AddUserRepository addUserRepository,
                              MainMarketRepository mainMarketRepository,
                              DelhiMarketRepository delhiMarketRepository,
                              StarlineMarketRepository starlineMarketRepository) {
        this.dpMotorRepository = dpMotorRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public List<DPMotorDTO> saveDPMotor(List<DPMotorDTO> dpMotorDTOList) {
        List<DPMotor> dpMotorList = dpMotorDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        List<DPMotor> savedList = dpMotorRepository.saveAll(dpMotorList);
        return savedList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DPMotorDTO updateDPMotor(int id, DPMotorDTO dpMotorDTO) {
        DPMotor existingDPMotor = dpMotorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DPMotor not found with id: " + id));

        updateEntityFromDTO(existingDPMotor, dpMotorDTO);
        DPMotor updatedDPMotor = dpMotorRepository.save(existingDPMotor);
        return convertToDTO(updatedDPMotor);
    }

    @Override
    public void deleteDPMotor(int id) {
        dpMotorRepository.deleteById(id);
    }

    @Override
    public DPMotorDTO getDPMotorById(int id) {
        DPMotor dpMotor = dpMotorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DPMotor not found with id: " + id));
        return convertToDTO(dpMotor);
    }

    @Override
    public List<DPMotorDTO> findByUser(int userId) {
        List<DPMotor> dpMotorList = dpMotorRepository.findByAddUser_Id(userId);
        if (dpMotorList.isEmpty()) {
            throw new RuntimeException("No DPMotor entries found for user with id: " + userId);
        }
        return dpMotorList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DPMotorDTO> getAllDPMotor() {
        return dpMotorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DPMotor convertToEntity(DPMotorDTO dto) {
        DPMotor entity = new DPMotor();
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

    private DPMotorDTO convertToDTO(DPMotor entity) {
        DPMotorDTO dto = new DPMotorDTO();
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

    private void updateEntityFromDTO(DPMotor entity, DPMotorDTO dto) {
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