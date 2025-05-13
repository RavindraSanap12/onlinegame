package com.OnlineGame.backend.Games.TripplePatti.serviceimpl;

import com.OnlineGame.backend.Games.TripplePatti.dto.TripplePattiDTO;
import com.OnlineGame.backend.Games.TripplePatti.entity.TripplePatti;
import com.OnlineGame.backend.Games.TripplePatti.repository.TripplePattiRepository;
import com.OnlineGame.backend.Games.TripplePatti.service.TripplePattiService;
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
public class TripplePattiServiceImpl implements TripplePattiService {

    private final TripplePattiRepository tripplePattiRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public TripplePattiServiceImpl(TripplePattiRepository tripplePattiRepository,
                                   AddUserRepository addUserRepository,
                                   MainMarketRepository mainMarketRepository,
                                   DelhiMarketRepository delhiMarketRepository,
                                   StarlineMarketRepository starlineMarketRepository) {
        this.tripplePattiRepository = tripplePattiRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public TripplePattiDTO saveTripplePatti(TripplePattiDTO tripplePattiDTO) {
        TripplePatti tripplePatti = convertToEntity(tripplePattiDTO);
        calculateTotals(tripplePatti);
        TripplePatti savedTripplePatti = tripplePattiRepository.save(tripplePatti);
        return convertToDTO(savedTripplePatti);
    }

    @Override
    public TripplePattiDTO findById(int id) {
        TripplePatti tripplePatti = tripplePattiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TripplePatti not found with id: " + id));
        return convertToDTO(tripplePatti);
    }

    @Override
    public List<TripplePattiDTO> findByUser(int userId) {
        List<TripplePatti> tripplePattis = tripplePattiRepository.findByAddUser_Id(userId);
        if (tripplePattis.isEmpty()) {
            throw new RuntimeException("No TripplePatti entries found for user with id: " + userId);
        }
        return tripplePattis.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripplePattiDTO> findAllTripplePatti() {
        return tripplePattiRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TripplePattiDTO updateTripplePatti(int id, TripplePattiDTO tripplePattiDTO) {
        TripplePatti existingTripplePatti = tripplePattiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TripplePatti not found with id: " + id));

        updateEntityFromDTO(existingTripplePatti, tripplePattiDTO);
        calculateTotals(existingTripplePatti);
        TripplePatti updatedTripplePatti = tripplePattiRepository.save(existingTripplePatti);
        return convertToDTO(updatedTripplePatti);
    }

    @Override
    public void deleteTripplePatti(int id) {
        tripplePattiRepository.deleteById(id);
    }

    private void calculateTotals(TripplePatti tripplePatti) {
        int[] digitValues = {
                tripplePatti.getZero3(),
                tripplePatti.getOne3(),
                tripplePatti.getTwo3(),
                tripplePatti.getThree3(),
                tripplePatti.getFour3(),
                tripplePatti.getFive3(),
                tripplePatti.getSix3(),
                tripplePatti.getSeven3(),
                tripplePatti.getEight3(),
                tripplePatti.getNine3()
        };

        int totalBids = 0;
        double totalBidAmount = 0.0;

        for (int value : digitValues) {
            if (value > 0) {
                totalBids++;
                totalBidAmount += value;
            }
        }

        tripplePatti.setTotalBids(totalBids);
        tripplePatti.setTotalBidAmount(totalBidAmount);
    }

    private TripplePatti convertToEntity(TripplePattiDTO dto) {
        TripplePatti entity = new TripplePatti();
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
        entity.setZero3(dto.getZero3());
        entity.setOne3(dto.getOne3());
        entity.setTwo3(dto.getTwo3());
        entity.setThree3(dto.getThree3());
        entity.setFour3(dto.getFour3());
        entity.setFive3(dto.getFive3());
        entity.setSix3(dto.getSix3());
        entity.setSeven3(dto.getSeven3());
        entity.setEight3(dto.getEight3());
        entity.setNine3(dto.getNine3());

        calculateTotals(entity);

        return entity;
    }

    private TripplePattiDTO convertToDTO(TripplePatti entity) {
        TripplePattiDTO dto = new TripplePattiDTO();
        dto.setId(entity.getId());

        if (entity.getAddUser() != null) {
            AddUserDTO userDTO = convertUserToDTO(entity.getAddUser());
            dto.setAddUserDTO(userDTO);
        }

        if (entity.getMainMarket() != null) {
            dto.setMainMarketDTO(convertMainMarketToDTO(entity.getMainMarket()));
        }

        if (entity.getDelhiMarket() != null) {
            dto.setDelhiMarketDTO(convertDelhiMarketToDTO(entity.getDelhiMarket()));
        }

        if (entity.getStarlineMarket() != null) {
            dto.setStarlineMarketDTO(convertStarlineMarketToDTO(entity.getStarlineMarket()));
        }

        dto.setDate(entity.getDate());
        dto.setType(entity.getType());
        dto.setZero3(entity.getZero3());
        dto.setOne3(entity.getOne3());
        dto.setTwo3(entity.getTwo3());
        dto.setThree3(entity.getThree3());
        dto.setFour3(entity.getFour3());
        dto.setFive3(entity.getFive3());
        dto.setSix3(entity.getSix3());
        dto.setSeven3(entity.getSeven3());
        dto.setEight3(entity.getEight3());
        dto.setNine3(entity.getNine3());
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

    private void updateEntityFromDTO(TripplePatti entity, TripplePattiDTO dto) {
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

        entity.setZero3(dto.getZero3());
        entity.setOne3(dto.getOne3());
        entity.setTwo3(dto.getTwo3());
        entity.setThree3(dto.getThree3());
        entity.setFour3(dto.getFour3());
        entity.setFive3(dto.getFive3());
        entity.setSix3(dto.getSix3());
        entity.setSeven3(dto.getSeven3());
        entity.setEight3(dto.getEight3());
        entity.setNine3(dto.getNine3());

        calculateTotals(entity);
    }
}