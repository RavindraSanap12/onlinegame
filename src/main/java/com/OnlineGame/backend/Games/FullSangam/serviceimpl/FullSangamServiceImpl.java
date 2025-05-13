package com.OnlineGame.backend.Games.FullSangam.serviceimpl;

import com.OnlineGame.backend.Games.FullSangam.dto.FullSangamDTO;
import com.OnlineGame.backend.Games.FullSangam.entity.FullSangam;
import com.OnlineGame.backend.Games.FullSangam.repository.FullSangamRepository;
import com.OnlineGame.backend.Games.FullSangam.service.FullSangamService;
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
public class FullSangamServiceImpl implements FullSangamService {

    private final FullSangamRepository fullSangamRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public FullSangamServiceImpl(FullSangamRepository fullSangamRepository,
                                 AddUserRepository addUserRepository,
                                 MainMarketRepository mainMarketRepository,
                                 DelhiMarketRepository delhiMarketRepository,
                                 StarlineMarketRepository starlineMarketRepository) {
        this.fullSangamRepository = fullSangamRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public FullSangamDTO saveFullSangam(FullSangamDTO fullSangamDTO) {
        FullSangam fullSangam = convertToEntity(fullSangamDTO);
        FullSangam savedFullSangam = fullSangamRepository.save(fullSangam);
        return convertToDTO(savedFullSangam);
    }

    @Override
    public FullSangamDTO findById(int id) {
        FullSangam fullSangam = fullSangamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FullSangam not found with id: " + id));
        return convertToDTO(fullSangam);
    }

    @Override
    public List<FullSangamDTO> findByUser(int userId) {
        List<FullSangam> fullSangams = fullSangamRepository.findByAddUser_Id(userId);
        if (fullSangams.isEmpty()) {
            throw new RuntimeException("No FullSangam entries found for user with id: " + userId);
        }
        return fullSangams.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FullSangamDTO> findAllFullSangam() {
        return fullSangamRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FullSangamDTO updateFullSangam(int id, FullSangamDTO fullSangamDTO) {
        FullSangam existingFullSangam = fullSangamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FullSangam not found with id: " + id));

        updateEntityFromDTO(existingFullSangam, fullSangamDTO);
        FullSangam updatedFullSangam = fullSangamRepository.save(existingFullSangam);
        return convertToDTO(updatedFullSangam);
    }

    @Override
    public void deleteFullSangam(int id) {
        fullSangamRepository.deleteById(id);
    }

    private FullSangam convertToEntity(FullSangamDTO dto) {
        FullSangam entity = new FullSangam();
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

    private FullSangamDTO convertToDTO(FullSangam entity) {
        FullSangamDTO dto = new FullSangamDTO();
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

    private void updateEntityFromDTO(FullSangam entity, FullSangamDTO dto) {
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