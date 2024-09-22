package ru.javacode.student.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javacode.student.security.model.dto.JwtTokensDto;
import ru.javacode.student.security.model.dto.LoginDto;
import ru.javacode.student.security.util.JwtUtility;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;

    @PostMapping("/login")
    public JwtTokensDto login(@RequestBody LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        JwtTokensDto jwtTokensDto = new JwtTokensDto();
        jwtTokensDto.setAccessToken(jwtUtility.createToken(loginDto.getUsername(), JwtUtility.ACCESS_TOKEN_DURATION));
        jwtTokensDto.setRefreshToken(jwtUtility.createToken(loginDto.getUsername(), JwtUtility.REFRESH_TOKEN_DURATION));

        return jwtTokensDto;
    }

    @PostMapping("/refresh")
    public JwtTokensDto refresh(@RequestBody JwtTokensDto jwtTokensDto) {
        String username = jwtUtility.getUsername(jwtTokensDto.getRefreshToken());

        if (jwtUtility.isTokenValid(jwtTokensDto.getRefreshToken())) {
            JwtTokensDto resfreshedJwtTokensDto = new JwtTokensDto();
            resfreshedJwtTokensDto.setAccessToken(jwtUtility.createToken(username, JwtUtility.ACCESS_TOKEN_DURATION));

            return resfreshedJwtTokensDto;
        } else {
            throw new RuntimeException("Refresh token is invalid.");
        }
    }

}
