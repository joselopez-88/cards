package com.eazybites.cards.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybites.cards.constants.CardsConstants;
import com.eazybites.cards.model.CardsContactInfoDto;
import com.eazybites.cards.model.dto.request.CardsUpdateRequestDto;
import com.eazybites.cards.model.dto.response.CardsResponseDto;
import com.eazybites.cards.model.dto.response.ResponseDto;
import com.eazybites.cards.service.ICardsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private final ICardsService iCardsService;

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;

    private final CardsContactInfoDto contactInfo;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(
            @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ResponseDto(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(),
                                CardsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsResponseDto> fetchCardDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        CardsResponseDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsUpdateRequestDto cardsDto) {
        iCardsService.updateCard(cardsDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(),
                                CardsConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        iCardsService.deleteCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(),
                                CardsConstants.MESSAGE_200));
    }

    @Operation(
        summary = "Get Build Version Rest Api",
        description = "Get Build Version details in EazyBank"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.ok().body(buildVersion);
    }
    @GetMapping("/java-home")
    public ResponseEntity<String> getJavaHome(){
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity.ok().body(contactInfo);
    }

}
