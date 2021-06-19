package ru.itis.javalab.data.redis.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("user")
public class RedisUser {

    @Id
    private String id;

    private List<String> tokens;

    private Long userId;

}
