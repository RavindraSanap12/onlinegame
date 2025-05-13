package com.OnlineGame.backend.config.services;

import com.OnlineGame.backend.User.entity.AddUser;
import com.OnlineGame.backend.User.repository.AddUserRepository;
import com.OnlineGame.backend.config.Util.JwtService;
import com.OnlineGame.backend.config.dto.AuthenticationRequest;
import com.OnlineGame.backend.config.dto.AuthenticationResponse;
import com.OnlineGame.backend.config.dto.UserResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements UserDetailsService {

    private final AddUserRepository addUserRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            AddUserRepository addUserRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationManager authenticationManager
    ) {
        this.addUserRepository = addUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNo) throws UsernameNotFoundException {
        AddUser user = addUserRepository.findByMobileNo(mobileNo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number: " + mobileNo));

        return new User(user.getMobileNo(), user.getPassword(), new ArrayList<>());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMobileNo(),
                        request.getPassword()
                )
        );

        AddUser user = addUserRepository.findByMobileNo(request.getMobileNo())
                .orElseThrow();

        UserDetails userDetails = loadUserByUsername(request.getMobileNo());
        var jwtToken = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwtToken, mapToUserResponse(user));
    }

    private UserResponse mapToUserResponse(AddUser user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getMobileNo(),
                user.getCustomWithdraw(),
                user.getCustomRate(),
                user.getCustomClose(),
                user.getStatus()
        );
    }
}
