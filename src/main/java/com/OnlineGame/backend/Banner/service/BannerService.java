package com.OnlineGame.backend.Banner.service;

import com.OnlineGame.backend.Banner.dto.BannerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BannerService {

    BannerDTO createBanner(BannerDTO bannerDTO, MultipartFile bannerImage);

    BannerDTO updateBanner(int id, BannerDTO bannerDTO, MultipartFile bannerImage);

    void deleteBanner(int id);

    Optional<BannerDTO> getBannerById(int id);

    List<BannerDTO> getAllBanners();
}
