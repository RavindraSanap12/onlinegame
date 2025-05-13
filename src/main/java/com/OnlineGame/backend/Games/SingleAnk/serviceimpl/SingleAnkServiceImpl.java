package com.OnlineGame.backend.Games.SingleAnk.serviceimpl;

import com.OnlineGame.backend.Games.SingleAnk.dto.SingleAnkDTO;
import com.OnlineGame.backend.Games.SingleAnk.entity.SingleAnk;
import com.OnlineGame.backend.Games.SingleAnk.service.SingleAnkService;
import com.OnlineGame.backend.Market.entity.DelhiMarket;
import com.OnlineGame.backend.Market.entity.MainMarket;
import com.OnlineGame.backend.Market.entity.StarlineMarket;
import com.OnlineGame.backend.Market.repository.DelhiMarketRepository;
import com.OnlineGame.backend.Market.repository.MainMarketRepository;
import com.OnlineGame.backend.Market.repository.StarlineMarketRepository;
import com.OnlineGame.backend.User.dto.AddUserDTO;
import com.OnlineGame.backend.Games.SingleAnk.repository.SingleAnkRepository;
import com.OnlineGame.backend.User.entity.AddUser;
import com.OnlineGame.backend.User.repository.AddUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SingleAnkServiceImpl implements SingleAnkService {

    private final SingleAnkRepository singleAnkRepository;
    private final AddUserRepository addUserRepository;
    private final MainMarketRepository mainMarketRepository;
    private final DelhiMarketRepository delhiMarketRepository;
    private final StarlineMarketRepository starlineMarketRepository;

    @Autowired
    public SingleAnkServiceImpl(SingleAnkRepository singleAnkRepository,
                                AddUserRepository addUserRepository, MainMarketRepository mainMarketRepository,
                                DelhiMarketRepository delhiMarketRepository, StarlineMarketRepository starlineMarketRepository) {
        this.singleAnkRepository = singleAnkRepository;
        this.addUserRepository = addUserRepository;
        this.mainMarketRepository = mainMarketRepository;
        this.delhiMarketRepository = delhiMarketRepository;
        this.starlineMarketRepository = starlineMarketRepository;
    }

    @Override
    public SingleAnkDTO saveSingleAnk(SingleAnkDTO singleAnkDTO) {
        SingleAnk singleAnk = convertToEntity(singleAnkDTO);
        calculateTotals(singleAnk); // Calculate totals before saving
        SingleAnk savedSingleAnk = singleAnkRepository.save(singleAnk);
        return convertToDTO(savedSingleAnk);
    }

    @Override
    public SingleAnkDTO findById(int id) {
        SingleAnk singleAnk = singleAnkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SingleAnk not found with id: " + id));
        return convertToDTO(singleAnk);
    }

    @Override
    public List<SingleAnkDTO> findByUser(int userId) {
        List<SingleAnk> singleAnks = singleAnkRepository.findByAddUser_Id(userId);
        if (singleAnks.isEmpty()) {
            throw new RuntimeException("No SingleAnk entries found for user with id: " + userId);
        }
        return singleAnks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SingleAnkDTO> findAllSingleAnk() {
        return singleAnkRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SingleAnkDTO updateSingleAnk(int id, SingleAnkDTO singleAnkDTO) {
        SingleAnk existingSingleAnk = singleAnkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SingleAnk not found with id: " + id));

        updateEntityFromDTO(existingSingleAnk, singleAnkDTO);
        calculateTotals(existingSingleAnk); // Recalculate totals before updating
        SingleAnk updatedSingleAnk = singleAnkRepository.save(existingSingleAnk);
        return convertToDTO(updatedSingleAnk);
    }

    @Override
    public void deleteSingleAnk(int id) {
        singleAnkRepository.deleteById(id);
    }

    // Helper method to calculate total bids and total bid amount
    private void calculateTotals(SingleAnk singleAnk) {
        int[] digitValues = {
                singleAnk.getZero(),
                singleAnk.getOne(),
                singleAnk.getTwo(),
                singleAnk.getThree(),
                singleAnk.getFour(),
                singleAnk.getFive(),
                singleAnk.getSix(),
                singleAnk.getSeven(),
                singleAnk.getEight(),
                singleAnk.getNine()
        };

        int totalBids = 0;
        double totalBidAmount = 0.0;

        for (int value : digitValues) {
            if (value > 0) {
                totalBids++;
                totalBidAmount += value;
            }
        }

        singleAnk.setTotalBids(totalBids);
        singleAnk.setTotalBidAmount(totalBidAmount);
    }

    // Helper methods for conversion between DTO and Entity
    private SingleAnk convertToEntity(SingleAnkDTO dto) {
        SingleAnk entity = new SingleAnk();
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
                    .orElseThrow(() -> new RuntimeException("Starline Market not found with id: " + dto.getMainMarketDTO().getMarketId()));
            entity.setStarlineMarket(starlineMarket);
        }

        entity.setDate(dto.getDate());
        entity.setType(dto.getType());
        entity.setZero(dto.getZero());
        entity.setOne(dto.getOne());
        entity.setTwo(dto.getTwo());
        entity.setThree(dto.getThree());
        entity.setFour(dto.getFour());
        entity.setFive(dto.getFive());
        entity.setSix(dto.getSix());
        entity.setSeven(dto.getSeven());
        entity.setEight(dto.getEight());
        entity.setNine(dto.getNine());

        // Calculate totals
        calculateTotals(entity);

        return entity;
    }

    private SingleAnkDTO convertToDTO(SingleAnk entity) {
        SingleAnkDTO dto = new SingleAnkDTO();
        dto.setId(entity.getId());

        if (entity.getAddUser() != null) {
            AddUserDTO userDTO = convertUserToDTO(entity.getAddUser());
            dto.setAddUserDTO(userDTO);
        }

        dto.setDate(entity.getDate());
        dto.setType(entity.getType());
        dto.setZero(entity.getZero());
        dto.setOne(entity.getOne());
        dto.setTwo(entity.getTwo());
        dto.setThree(entity.getThree());
        dto.setFour(entity.getFour());
        dto.setFive(entity.getFive());
        dto.setSix(entity.getSix());
        dto.setSeven(entity.getSeven());
        dto.setEight(entity.getEight());
        dto.setNine(entity.getNine());
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

    private void updateEntityFromDTO(SingleAnk entity, SingleAnkDTO dto) {
        if (dto.getAddUserDTO() != null) {
            AddUser user = addUserRepository.findById(dto.getAddUserDTO().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getAddUserDTO().getId()));
            entity.setAddUser(user);
        }

        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }

        if (dto.getType() != null) {
            entity.setType(dto.getType());
        }

        entity.setZero(dto.getZero());
        entity.setOne(dto.getOne());
        entity.setTwo(dto.getTwo());
        entity.setThree(dto.getThree());
        entity.setFour(dto.getFour());
        entity.setFive(dto.getFive());
        entity.setSix(dto.getSix());
        entity.setSeven(dto.getSeven());
        entity.setEight(dto.getEight());
        entity.setNine(dto.getNine());

        // Recalculate totals when updating
        calculateTotals(entity);
    }
}