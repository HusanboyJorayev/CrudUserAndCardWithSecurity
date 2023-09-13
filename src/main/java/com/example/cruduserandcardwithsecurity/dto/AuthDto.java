package com.example.cruduserandcardwithsecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "password",allowSetters = true)
public class AuthDto implements UserDetails {

    private Integer authId;
    @NotBlank(message = "name cannot be null or empty")
    private String name;
    private String surname;
    @NotBlank(message = "name cannot be null or empty")
    private String username;
    @NotBlank(message = "name cannot be null or empty")
    private String password;
    private Boolean enabled;

    private Set<AuthoritiesDto> authorities;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Optional.ofNullable(authorities)
                 .map(authorities->authorities.stream()
                         .map(authority ->new SimpleGrantedAuthority(authority.getAuthority())).toList())
                 .orElse(new ArrayList<>());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.enabled;
    }


}
