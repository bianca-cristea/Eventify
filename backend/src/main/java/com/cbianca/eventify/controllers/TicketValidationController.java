package com.cbianca.eventify.controllers;

import com.cbianca.eventify.dtos.TicketValidationRequestDTO;
import com.cbianca.eventify.dtos.TicketValidationResponseDTO;
import com.cbianca.eventify.entities.ticket_validation.TicketValidation;
import com.cbianca.eventify.entities.ticket_validation.TicketValidationMethod;
import com.cbianca.eventify.mapper.TicketValidationMapper;
import com.cbianca.eventify.services.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/ticket-validations")
@RequiredArgsConstructor

public class TicketValidationController {

    private final TicketValidationService ticketValidationService;
    private final TicketValidationMapper ticketValidationMapper;

    @PostMapping
    public ResponseEntity<TicketValidationResponseDTO> validateTicket(
            @RequestBody TicketValidationRequestDTO ticketValidationRequestDTO
            )
    {
        TicketValidationMethod method = ticketValidationRequestDTO.getMethod();
        TicketValidation ticketValidation;

        if(TicketValidationMethod.MANUAL.equals(method)){
             ticketValidation = ticketValidationService.validateTicketManually(ticketValidationRequestDTO.getId());
        } else {
             ticketValidation = ticketValidationService.validateTicketByQrCode(ticketValidationRequestDTO.getId());
        }
        return  ResponseEntity.ok(ticketValidationMapper.toTicketValidationResponseDTO(ticketValidation));
    }

}
