package com.example.cruduserandcardwithsecurity.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authId;
    private String name;
    private String surname;

    @Column(nullable = false,unique = true)
    private String password;
    @Column(unique = true,nullable = false)
    private String username;
    private Boolean enabled;

    private String code;

    @OneToMany(mappedBy = "authId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Authorities>authorities;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
