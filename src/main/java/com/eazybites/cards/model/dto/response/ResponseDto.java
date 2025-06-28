package com.eazybites.cards.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ResponseDto {
    private String status;
    private Integer code;
    private String message;
}
