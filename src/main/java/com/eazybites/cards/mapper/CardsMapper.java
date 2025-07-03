package com.eazybites.cards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.eazybites.cards.model.dto.request.CardsUpdateRequestDto;
import com.eazybites.cards.model.dto.response.CardsResponseDto;
import com.eazybites.cards.model.entity.Cards;

@Mapper(componentModel = "spring")
public interface CardsMapper {

    CardsResponseDto mapToCardsResponseDto(Cards cards);
    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "amountUsed", ignore = true)
    @Mapping(target = "availableAmount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Cards mapToCards(CardsUpdateRequestDto cardsDto);
    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "amountUsed", ignore = true)
    @Mapping(target = "availableAmount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateCardsFromDto(CardsUpdateRequestDto dto, @MappingTarget Cards entity);

}
