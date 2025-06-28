package com.eazybites.cards.exception.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailsBody extends ErrorBody {
    private Map<String, String> details;
}
