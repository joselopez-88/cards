package com.eazybites.cards.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardsUpdateRequestDto {
    @NotBlank(message = " The mobile number is required.")
    @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.")
    private String mobileNumber;
    @NotBlank(message = " The card number is required.")
    @Pattern(regexp = "(^[0-9]{12}$)", message = " The card number must be 12 digits.")
    private String cardNumber;
    @NotBlank(message = " The card type is required.")
    private String cardType;
    @Positive(message = " The total limit must be greater than 0.")
    private Integer totalLimit;
    @PositiveOrZero(message = " The amount used must be greater than or equal to 0.")
    private Integer amountToUse;
    // @PositiveOrZero(message = " The available amount must be greater than or equal to 0.")
    // private Integer availableAmount;
}
