package com.example.cruduserandcardwithsecurity.service.validation;


import com.example.cruduserandcardwithsecurity.dto.ErrorDto;
import com.example.cruduserandcardwithsecurity.dto.UserDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {
    public List<ErrorDto> validate(UserDto dto) {
        List<ErrorDto> errors = new ArrayList<>();


        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name cannot be null or empty", "name"));
        }
        if (StringUtils.isBlank(dto.getSurname())) {
            errors.add(new ErrorDto("surname cannot be null or empty", "surname"));
        }
        if (StringUtils.isBlank(dto.getEmail())) {
            errors.add(new ErrorDto("email cannot be null or empty", "email"));
        }
        if (!checkEmail(dto.getEmail())) {
            errors.add(new ErrorDto("email", "email is not exit"));
        }
        if (StringUtils.isBlank(dto.getPassword())) {
            errors.add(new ErrorDto("password cannot be null or empty", "password"));
        }
        if (StringUtils.isBlank(dto.getAge().toString())) {
            errors.add(new ErrorDto("age cannot be null or empty", "age"));
        }
        return errors;
    }

    private boolean checkEmail(String email) {
        String[] array = email.split("@");
        return array[1].equals("gmail.com") && array.length == 2;
    }
}
