package com.OnlineGame.backend.Banner.controller;

import com.OnlineGame.backend.Banner.dto.BannerDTO;
import com.OnlineGame.backend.Banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    // Endpoint to create a new banner
    @PostMapping
    public ResponseEntity<BannerDTO> createBanner(
            @RequestParam("bannerImage") MultipartFile bannerImage,
            @RequestParam("showInApp") boolean showInApp,
            @RequestParam("showInWeb") boolean showInWeb) {

        BannerDTO bannerDTO = new BannerDTO();
        bannerDTO.setShowInApp(showInApp);
        bannerDTO.setShowInWeb(showInWeb);

        // Call service to create the banner
        BannerDTO createdBanner = bannerService.createBanner(bannerDTO, bannerImage);
        return new ResponseEntity<>(createdBanner, HttpStatus.CREATED);
    }

    // Endpoint to update an existing banner
    @PutMapping("/{id}")
    public ResponseEntity<BannerDTO> updateBanner(
            @PathVariable int id,
            @RequestParam("bannerImage") MultipartFile bannerImage,
            @RequestParam("showInApp") boolean showInApp,
            @RequestParam("showInWeb") boolean showInWeb) {

        BannerDTO bannerDTO = new BannerDTO();
        bannerDTO.setShowInApp(showInApp);
        bannerDTO.setShowInWeb(showInWeb);

        // Call service to update the banner
        BannerDTO updatedBanner = bannerService.updateBanner(id, bannerDTO, bannerImage);
        return new ResponseEntity<>(updatedBanner, HttpStatus.OK);
    }

    // Endpoint to delete a banner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable int id) {
        // Call service to delete the banner
        bannerService.deleteBanner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get a banner by its ID
    @GetMapping("/{id}")
    public ResponseEntity<BannerDTO> getBannerById(@PathVariable int id) {
        Optional<BannerDTO> bannerDTO = bannerService.getBannerById(id);

        return bannerDTO.map(banner -> new ResponseEntity<>(banner, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to get all banners
    @GetMapping
    public ResponseEntity<List<BannerDTO>> getAllBanners() {
        List<BannerDTO> banners = bannerService.getAllBanners();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }
}
