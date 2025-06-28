package com.eazybites.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybites.cards.model.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Integer> {
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
