package com.example.cruduserandcardwithsecurity.service;


import com.example.cruduserandcardwithsecurity.dto.CardDto;
import com.example.cruduserandcardwithsecurity.dto.ErrorDto;
import com.example.cruduserandcardwithsecurity.dto.ResponseDto;
import com.example.cruduserandcardwithsecurity.dto.SimpleCrud;
import com.example.cruduserandcardwithsecurity.model.Card;
import com.example.cruduserandcardwithsecurity.repository.CardRepository;
import com.example.cruduserandcardwithsecurity.service.mapper.CardMapper;
import com.example.cruduserandcardwithsecurity.service.validation.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService implements SimpleCrud<Integer, CardDto> {
    private final CardRepository cardRepository;
    private final CardValidation cardValidation;
    private final CardMapper cardMapper;

    @Override
    public ResponseDto<CardDto> create(CardDto dto) {

        List<ErrorDto> errors = this.cardValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }
        try {

            Card card = this.cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            this.cardRepository.save(card);
            return ResponseDto.<CardDto>builder()
                    .message("Ok")
                    .success(true)
                    .data(this.cardMapper.toDto(card))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CardDto> get(Integer id) {

        return this.cardRepository.findByIdAndDeletedAtIsNull(id)
                .map(card -> ResponseDto.<CardDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.cardMapper.toDto(card))
                        .build())
                .orElse(ResponseDto.<CardDto>builder()
                        .code(-1)
                        .message("card is not found")
                        .build());
    }

    @Override
    public ResponseDto<CardDto> update(CardDto dto, Integer id) {

        List<ErrorDto> errors = this.cardValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }
        try {
            return this.cardRepository.findByIdAndDeletedAtIsNull(id)
                    .map(card -> {
                                card.setUpdatedAt(LocalDateTime.now());
                                this.cardMapper.update(dto, card);
                                this.cardRepository.save(card);
                                return ResponseDto.<CardDto>builder()
                                        .success(true)
                                        .message("Ok")
                                        .data(this.cardMapper.toDto(card))
                                        .build();
                            }
                    )
                    .orElse(ResponseDto.<CardDto>builder()
                            .code(-1)
                            .message("card is not found")
                            .build());
        } catch (Exception e) {
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CardDto> delete(Integer id) {
        return this.cardRepository.findByIdAndDeletedAtIsNull(id)
                .map(card -> {
                    card.setDeletedAt(LocalDateTime.now());
                    this.cardRepository.save(card);
                    return ResponseDto.<CardDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.cardMapper.toDto(card))
                            .build();
                })
                .orElse(ResponseDto.<CardDto>builder()
                        .code(-1)
                        .message("card is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<CardDto>> getAll() {
        List<Card> card = this.cardRepository.getAllCard();
        if (card.isEmpty()) {
            return ResponseDto.<List<CardDto>>builder()
                    .code(-1)
                    .message("Cards are not found")
                    .build();
        }
        return ResponseDto.<List<CardDto>>builder()
                .success(true)
                .message("Ok")
                .data(card.stream().map(this.cardMapper::toDto).toList())
                .build();
    }
}
