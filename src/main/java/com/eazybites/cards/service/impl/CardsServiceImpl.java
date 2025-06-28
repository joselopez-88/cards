package com.eazybites.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybites.cards.constants.CardsConstants;
import com.eazybites.cards.exception.error.CardAlreadyExistsException;
import com.eazybites.cards.exception.error.ResourceNotFoundException;
import com.eazybites.cards.mapper.CardsMapper;
import com.eazybites.cards.model.dto.request.CardsUpdateRequestDto;
import com.eazybites.cards.model.dto.response.CardsResponseDto;
import com.eazybites.cards.model.entity.Cards;
import com.eazybites.cards.repository.CardsRepository;
import com.eazybites.cards.service.ICardsService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private final CardsMapper cardsMapper;
    private final CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
       Optional<Cards> existingCard = cardsRepository.findByMobileNumber(mobileNumber);

        if (existingCard.isPresent()) {
            throw new CardAlreadyExistsException(mobileNumber);
        }
        Cards cards = createNewCard(mobileNumber);
        cardsRepository.save(cards);

    }

    @Override
    public void updateCard(CardsUpdateRequestDto cardsDto) {
        Cards existingCard = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber()).orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", cardsDto.getMobileNumber()));
        cardsMapper.updateCardsFromDto(cardsDto, existingCard);
        existingCard.setAmountUsed(existingCard.getAmountUsed() + cardsDto.getAmountToUse());
        existingCard.setAvailableAmount(existingCard.getTotalLimit() - existingCard.getAmountUsed());
        cardsRepository.save(existingCard);
    }

    @Override
    public void deleteCard(String mobileNumber) {
        Cards existingCard = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(existingCard.getCardId());
    }

    @Override
    public CardsResponseDto fetchCard(String mobileNumber) {
        Cards existingCard = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        return cardsMapper.mapToCardsResponseDto(existingCard);
    }

       private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
    
}
