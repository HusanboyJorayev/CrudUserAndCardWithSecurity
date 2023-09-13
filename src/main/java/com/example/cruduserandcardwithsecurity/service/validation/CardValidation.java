package com.example.cruduserandcardwithsecurity.service.validation;

import com.example.cruduserandcardwithsecurity.dto.CardDto;
import com.example.cruduserandcardwithsecurity.dto.ErrorDto;
import com.example.cruduserandcardwithsecurity.model.User;
import com.example.cruduserandcardwithsecurity.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CardValidation {
    private final UserRepository userRepository;

    public List<ErrorDto> validate(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(dto.getUserId());
        if (optional.isEmpty()) {
            errors.add(new ErrorDto("you cannot card because user is null ","card"));
        }
        if (StringUtils.isBlank(dto.getCardName())) {
            errors.add(new ErrorDto("cardName cannot be null or empty", "cardName"));
        }
        if (StringUtils.isBlank(dto.getCardNumber())) {
            errors.add(new ErrorDto("cardNumber cannot be null or empty", "cardNumber"));
        }
        if ((dto.getCardCode()==null)) {
            errors.add(new ErrorDto("cardCode cannot be null or empty", "cardCode"));
        }
        if (StringUtils.isBlank(dto.getBalance().toString())) {
            errors.add(new ErrorDto("balance cannot be null or empty", "balance"));
        }
        return errors;
    }
}
