package com.OnlineGame.backend.Games.SPDPTP.serviceimpl;

import com.OnlineGame.backend.Games.SPDPTP.dto.SPDPTPDTO;
import com.OnlineGame.backend.Games.SPDPTP.entity.SPDPTP;
import com.OnlineGame.backend.Games.SPDPTP.repository.SPDPTPRepository;
import com.OnlineGame.backend.Games.SPDPTP.service.SPDPTPService;
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
public class SPDPTPServiceImpl implements SPDPTPService {

    private final SPDPTPRepository spdptpRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public SPDPTPServiceImpl(SPDPTPRepository spdptpRepository,
                             AddUserRepository addUserRepository,
                             MainMarketRepository mainMarketRepository,
                             DelhiMarketRepository delhiMarketRepository,
                             StarlineMarketRepository starlineMarketRepository) {
        this.spdptpRepository = spdptpRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public List<SPDPTPDTO> saveAllSPDPTP(List<SPDPTPDTO> spdptpDTOList) {
        List<SPDPTP> spdptpEntities = spdptpDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        List<SPDPTP> savedEntities = spdptpRepository.saveAll(spdptpEntities);

        return savedEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public SPDPTPDTO updateSPDPTP(int id, SPDPTPDTO spdptpDTO) {
        SPDPTP existingSPDPTP = spdptpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SPDPTP not found with id: " + id));

        updateEntityFromDTO(existingSPDPTP, spdptpDTO);
        SPDPTP updatedSPDPTP = spdptpRepository.save(existingSPDPTP);
        return convertToDTO(updatedSPDPTP);
    }

    @Override
    public void deleteSPDPTP(int id) {
        spdptpRepository.deleteById(id);
    }

    @Override
    public SPDPTPDTO getSPDPTPById(int id) {
        SPDPTP spdptp = spdptpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SPDPTP not found with id: " + id));
        return convertToDTO(spdptp);
    }

    @Override
    public List<SPDPTPDTO> findByUser(int userId) {
        List<SPDPTP> spdptpList = spdptpRepository.findByAddUser_Id(userId);
        if (spdptpList.isEmpty()) {
            throw new RuntimeException("No SPDPTP entries found for user with id: " + userId);
        }
        return spdptpList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SPDPTPDTO> getAllSPDPTP() {
        return spdptpRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SPDPTP convertToEntity(SPDPTPDTO dto) {
        SPDPTP entity = new SPDPTP();
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

    private SPDPTPDTO convertToDTO(SPDPTP entity) {
        SPDPTPDTO dto = new SPDPTPDTO();
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

    private void updateEntityFromDTO(SPDPTP entity, SPDPTPDTO dto) {
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