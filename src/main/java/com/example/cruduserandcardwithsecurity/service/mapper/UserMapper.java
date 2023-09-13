package com.example.cruduserandcardwithsecurity.service.mapper;


import com.example.cruduserandcardwithsecurity.dto.UserDto;
import com.example.cruduserandcardwithsecurity.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract   class UserMapper {

    @Autowired
    protected CardMapper cardMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(ignore = true,target = "card")
    @Mapping(target = "password",expression = "java(passwordEncoder.encode(user.getPassword()))")
    public abstract UserDto toDto(User user);

    @Mapping(target = "password",expression = "java(passwordEncoder.encode(user.getPassword()))")
    @Mapping(target = "card",expression = "java(user.getCard().stream().map(this.cardMapper::toDto).collect(Collectors.toSet()))")
    public abstract UserDto toDtoWithCard(User user);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    @Mapping(ignore = true,target = "card")
    public abstract User toEntity(UserDto userDto);

    @Mapping(ignore = true,target = "card")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(UserDto dto,@MappingTarget User user);

    public void view(User user){
        passwordEncoder.encode(user.getPassword());
        UserDto dto=new UserDto();
        dto.setCard(user.getCard().stream().map(this.cardMapper::toDto).collect(Collectors.toSet()));
    }

}
