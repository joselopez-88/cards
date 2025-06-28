package com.eazybites.cards.model.dto.response;

import lombok.Data;

@Data
public class CardsResponseDto {
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;
}
