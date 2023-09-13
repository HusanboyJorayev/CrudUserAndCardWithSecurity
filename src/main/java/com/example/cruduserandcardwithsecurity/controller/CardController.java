package com.example.cruduserandcardwithsecurity.controller;


import com.example.cruduserandcardwithsecurity.dto.CardDto;
import com.example.cruduserandcardwithsecurity.dto.ResponseDto;
import com.example.cruduserandcardwithsecurity.dto.SimpleCrud;
import com.example.cruduserandcardwithsecurity.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "card")
public class CardController implements SimpleCrud<Integer, CardDto> {

    private final CardService cardService;

    @Override
    @PostMapping("/create")
    @Operation(
            method = "create method",
            description = "this method creates cards",
            summary = "this is a create method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )
    public ResponseDto<CardDto> create(@RequestBody @Valid CardDto dto) {
        return this.cardService.create(dto);
    }

    @Override
    @GetMapping("/get")
    @Operation(
            method = "get method",
            description = "this method gets cards",
            summary = "this is a get method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )
    public ResponseDto<CardDto> get(@RequestParam Integer id) {
        return this.cardService.get(id);
    }

    @Override
    @PutMapping("/update/{id}")
    @Operation(
            method = "update method",
            description = "this method updates cards",
            summary = "this is a update method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )
    public ResponseDto<CardDto> update(@RequestBody @Valid CardDto dto, @PathVariable Integer id) {
        return this.cardService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    @Operation(
            method = "delete method",
            description = "this method deletes cards",
            summary = "this is a delete method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )
    public ResponseDto<CardDto> delete(@RequestParam Integer id) {
        return this.cardService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    @Operation(
            method = "getAll method",
            description = "this method gets all cards",
            summary = "this is a getAll method",
            responses = @ApiResponse(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )
    public ResponseDto<List<CardDto>> getAll() {
        return this.cardService.getAll();
    }
}
