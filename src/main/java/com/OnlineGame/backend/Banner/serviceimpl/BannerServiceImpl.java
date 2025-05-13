package com.OnlineGame.backend.Banner.serviceimpl;

import com.OnlineGame.backend.Banner.dto.BannerDTO;
import com.OnlineGame.backend.Banner.entity.Banner;
import com.OnlineGame.backend.Banner.repository.BannerRepository;
import com.OnlineGame.backend.Banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public BannerDTO createBanner(BannerDTO bannerDTO, MultipartFile bannerImage) {
        try {
            // Create a new Banner entity
            Banner banner = new Banner();
            banner.setShowInApp(bannerDTO.isShowInApp());
            banner.setShowInWeb(bannerDTO.isShowInWeb());

            // Save the banner image as a byte array
            banner.setBannerImage(bannerImage.getBytes());

            // Save the Banner entity to the repository
            banner = bannerRepository.save(banner);

            // Set the ID in the DTO and return
            bannerDTO.setId(banner.getId());
            return bannerDTO;
        } catch (IOException e) {
            throw new RuntimeException("Error while saving banner image", e);
        }
    }

    @Override
    public BannerDTO updateBanner(int id, BannerDTO bannerDTO, MultipartFile bannerImage) {
        try {
            Optional<Banner> existingBannerOpt = bannerRepository.findById(id);
            if (existingBannerOpt.isPresent()) {
                Banner existingBanner = existingBannerOpt.get();

                // Update the properties
                existingBanner.setShowInApp(bannerDTO.isShowInApp());
                existingBanner.setShowInWeb(bannerDTO.isShowInWeb());
                existingBanner.setBannerImage(bannerImage.getBytes()); // Update the image

                // Save the updated Banner entity
                existingBanner = bannerRepository.save(existingBanner);

                // Update the DTO with the new ID and return
                bannerDTO.setId(existingBanner.getId());
                return bannerDTO;
            } else {
                throw new RuntimeException("Banner not found with ID " + id);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while updating banner image", e);
        }
    }

    @Override
    public void deleteBanner(int id) {
        // Delete the Banner by its ID
        bannerRepository.deleteById(id);
    }

    @Override
    public Optional<BannerDTO> getBannerById(int id) {
        Optional<Banner> bannerOpt = bannerRepository.findById(id);
        if (bannerOpt.isPresent()) {
            Banner banner = bannerOpt.get();
            // Convert Banner entity to BannerDTO
            BannerDTO bannerDTO = new BannerDTO();
            bannerDTO.setId(banner.getId());
            bannerDTO.setShowInApp(banner.isShowInApp());
            bannerDTO.setShowInWeb(banner.isShowInWeb());
            bannerDTO.setBannerImage(banner.getBannerImage());
            return Optional.of(bannerDTO);
        }
        return Optional.empty();
    }

    @Override
    public List<BannerDTO> getAllBanners() {
        List<Banner> banners = bannerRepository.findAll();
        return banners.stream()
                .map(b -> {
                    BannerDTO bannerDTO = new BannerDTO();
                    bannerDTO.setId(b.getId());
                    bannerDTO.setShowInApp(b.isShowInApp());
                    bannerDTO.setShowInWeb(b.isShowInWeb());
                    bannerDTO.setBannerImage(b.getBannerImage());
                    return bannerDTO;
                })
                .collect(Collectors.toList());
    }
}
