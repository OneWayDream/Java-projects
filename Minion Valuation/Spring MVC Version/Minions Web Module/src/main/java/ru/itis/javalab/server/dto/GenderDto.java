package ru.itis.javalab.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.server.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenderDto {

    protected User.Gender gender;
    protected String message;

    public static GenderDto from(User.Gender gender){
        return GenderDto.builder()
                .gender(gender)
                .message("gender." + gender.toString().toLowerCase())
                .build();
    }

}
