package com.example.cruduserandcardwithsecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "name cannot be null or empty")
    private String name;
    @NotBlank(message = "surname cannot be null or empty")
    private String surname;
    @NotBlank(message = "password cannot be null or empty")
    private String password;
    private Integer age;
    @Column(unique = true)
    @NotBlank(message = "email cannot be null or empty")
    private String email;

    @OneToMany(mappedBy = "userId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Card>card;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
