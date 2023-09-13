package com.example.cruduserandcardwithsecurity.model;


import com.example.cruduserandcardwithsecurity.dto.AuthDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 1000*3600*24*7)
public class AuthAccessSession {
    @Id
    private String sessionId;
    private AuthDto authDto;
}
