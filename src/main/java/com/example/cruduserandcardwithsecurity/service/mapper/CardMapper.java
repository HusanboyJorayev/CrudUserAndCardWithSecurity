package com.example.cruduserandcardwithsecurity.service.mapper;


import com.example.cruduserandcardwithsecurity.dto.CardDto;
import com.example.cruduserandcardwithsecurity.model.Card;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CardMapper {

    public abstract CardDto toDto(Card card);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Card toEntity(CardDto cardDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(CardDto dto,@MappingTarget Card card);

}
