package com.pangan.animedb.auth.dao;

import com.pangan.animedb.auth.dto.UserAuthRequestDto;
import com.pangan.animedb.auth.dto.UserAuthResponseDto;
import com.pangan.animedb.auth.exception.RoleNotFoundException;
import com.pangan.animedb.auth.exception.UsernameAlreadyRegisteredException;
import com.pangan.animedb.auth.exception.UsernameNotFoundException;
import com.pangan.animedb.auth.jwt.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserAuthRepository userAuthRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserAuthService(AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder,
                              JwtTokenGenerator jwtTokenGenerator,
                              UserAuthRepository userAuthRepository,
                              RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.userAuthRepository = userAuthRepository;
        this.roleRepository = roleRepository;
    }

    public UserAuth register(UserAuthRequestDto userAuthRequestDto) throws UsernameAlreadyRegisteredException, RoleNotFoundException {
        if (userAuthRepository.existsByUsername(userAuthRequestDto.username())) {
            throw  new UsernameAlreadyRegisteredException();
        }

        if (!roleRepository.existsByName(Role.USER)) {
            throw new RoleNotFoundException(Role.USER);
        }

        Role role = roleRepository.findByName("User").get();
        UserAuth userAuth = UserAuth.builder()
                .username(userAuthRequestDto.username())
                .password(passwordEncoder.encode(userAuthRequestDto.password()))
                .roleList(List.of(role))
                .build();

        return userAuthRepository.save(userAuth);
    }

    public UserAuthResponseDto login(UserAuthRequestDto userAuthRequestDto) throws UsernameNotFoundException {
        if (!userAuthRepository.existsByUsername(userAuthRequestDto.username())) {
            throw new UsernameNotFoundException();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthRequestDto.username(), userAuthRequestDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateToken(authentication);
        UserAuthResponseDto userAuthResponseDto = new UserAuthResponseDto();
        userAuthResponseDto.setAccessToken(token);

        return userAuthResponseDto;
    }
}
