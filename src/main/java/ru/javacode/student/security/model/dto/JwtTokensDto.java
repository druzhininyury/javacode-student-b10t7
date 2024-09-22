package ru.javacode.student.security.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtTokensDto {

    private String refreshToken;

    private String accessToken;

}
