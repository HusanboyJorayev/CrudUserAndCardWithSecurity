package com.example.cruduserandcardwithsecurity.controller;


import com.example.cruduserandcardwithsecurity.dto.ResponseDto;
import com.example.cruduserandcardwithsecurity.dto.SimpleCrud;
import com.example.cruduserandcardwithsecurity.dto.UserDto;
import com.example.cruduserandcardwithsecurity.service.UserService;
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
@RequestMapping(value = "user")
public class UserController implements SimpleCrud<Integer, UserDto> {

    private final UserService userService;


    @Override
    @PostMapping("/create")
    @Operation(
            method = "create method",
            description = "this method creates users",
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
    public ResponseDto<UserDto> create(@RequestBody @Valid UserDto dto) {
        return this.userService.create(dto);
    }

    @Override
    @GetMapping("/get")
    @Operation(
            method = "get method",
            description = "this method gets users",
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
    public ResponseDto<UserDto> get(@RequestParam Integer id) {
        return this.userService.get(id);
    }

    @Override
    @PutMapping("/update/{id}")
    @Operation(
            method = "update method",
            description = "this method updates users",
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
    public ResponseDto<UserDto> update(@RequestBody @Valid UserDto dto, @PathVariable Integer id) {
        return this.userService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    @Operation(
            method = "delete method",
            description = "this method deletes users",
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
    public ResponseDto<UserDto> delete(@RequestParam Integer id) {
        return this.userService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    @Operation(
            method = "getAll method",
            description = "this method gets all users",
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
    public ResponseDto<List<UserDto>> getAll() {
        return this.userService.getAll();
    }
}
