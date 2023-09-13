package com.example.cruduserandcardwithsecurity.service;


import com.example.cruduserandcardwithsecurity.dto.ErrorDto;
import com.example.cruduserandcardwithsecurity.dto.ResponseDto;
import com.example.cruduserandcardwithsecurity.dto.SimpleCrud;
import com.example.cruduserandcardwithsecurity.dto.UserDto;
import com.example.cruduserandcardwithsecurity.model.User;
import com.example.cruduserandcardwithsecurity.repository.UserRepository;
import com.example.cruduserandcardwithsecurity.service.mapper.UserMapper;
import com.example.cruduserandcardwithsecurity.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<Integer, UserDto> {
    private final UserRepository userRepository;
    private final UserValidation userValidation;
    private final UserMapper userMapper;

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
        List<ErrorDto> errors = this.userValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }
        try {
            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.userMapper.toDto(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("user while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> get(Integer id) {

        return this.userRepository.findByIdAndDeletedAtIsNull(id)
                .map(user -> ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.userMapper.toDtoWithCard(user))
                        .build())
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message("user is not found")
                        .build());

    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer id) {
        List<ErrorDto> errors = this.userValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }
        try {
            return this.userRepository.findByIdAndDeletedAtIsNull(id)
                    .map(user -> {
                        user.setUpdatedAt(LocalDateTime.now());
                        this.userMapper.update(dto, user);
                        this.userRepository.save(user);
                        return ResponseDto.<UserDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.userMapper.toDtoWithCard(user))
                                .build();
                    })
                    .orElse(ResponseDto.<UserDto>builder()
                            .code(-1)
                            .message("user is not found")
                            .build());
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("user while updating error")
                    .build();
        }

    }

    @Override
    public ResponseDto<UserDto> delete(Integer id) {
        return this.userRepository.findByIdAndDeletedAtIsNull(id)
                .map(user -> {
                    user.setDeletedAt(LocalDateTime.now());
                    this.userRepository.save(user);
                    return ResponseDto.<UserDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.userMapper.toDto(user))
                            .build();
                })
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message("user is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<UserDto>> getAll() {
        List<User> user = this.userRepository.getAllUser();
        if (user.isEmpty()) {
            return ResponseDto.<List<UserDto>>builder()
                    .code(-1)
                    .message("Users are not found")
                    .build();
        }
        return ResponseDto.<List<UserDto>>builder()
                .success(true)
                .message("Ok")
                .data(user.stream().map(this.userMapper::toDtoWithCard).toList())
                .build();

    }
}
