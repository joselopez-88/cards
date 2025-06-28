package com.eazybites.cards.service;

import com.eazybites.cards.model.dto.request.CardsUpdateRequestDto;
import com.eazybites.cards.model.dto.response.CardsResponseDto;

public interface ICardsService {
  void createCard(String mobileNumber);
  void updateCard(CardsUpdateRequestDto cardsDto);
  void deleteCard(String mobileNumber);
  CardsResponseDto fetchCard(String mobileNumber);
}
