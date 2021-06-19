package ru.itis.javalab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.data.models.RefreshToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenDto {

    protected String token;

    public static RefreshTokenDto from(RefreshToken refreshToken){
        return RefreshTokenDto.builder()
                .token(refreshToken.getToken())
                .build();
    }

}
