package com.example.cruduserandcardwithsecurity.service.mapper;


import com.example.cruduserandcardwithsecurity.dto.AuthoritiesDto;
import com.example.cruduserandcardwithsecurity.model.Authorities;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class AuthoritiesMapper {

    public abstract AuthoritiesDto toDto(Authorities authorities);

    @Mapping(ignore = true, target = "authoritiesId")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Authorities toEntity(AuthoritiesDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(AuthoritiesDto dto, @MappingTarget Authorities authorities);
}
