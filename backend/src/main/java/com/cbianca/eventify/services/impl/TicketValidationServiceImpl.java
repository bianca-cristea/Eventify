package com.cbianca.eventify.services.impl;

import com.cbianca.eventify.entities.qr.QrCode;
import com.cbianca.eventify.entities.qr.QrCodeStatusEnum;
import com.cbianca.eventify.entities.ticket_validation.TicketValidation;
import com.cbianca.eventify.entities.ticket_validation.TicketValidationMethod;
import com.cbianca.eventify.entities.ticket_validation.TicketValidationStatusEnum;
import com.cbianca.eventify.entities.tickets.Ticket;
import com.cbianca.eventify.exceptions.QrCodeNotFoundException;
import com.cbianca.eventify.exceptions.TicketTypeNotFoundException;
import com.cbianca.eventify.repositories.QRCodeRepository;
import com.cbianca.eventify.repositories.TicketRepository;
import com.cbianca.eventify.repositories.TicketValidationRepository;
import com.cbianca.eventify.services.TicketValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {

    private final QRCodeRepository qrCodeRepository;
    private final TicketValidationRepository ticketValidationRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
       QrCode qrCode =  qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE).orElseThrow(() -> new QrCodeNotFoundException(String.format("Qr code with ID %s was not found",qrCodeId)));

    Ticket ticket = qrCode.getTicket();

        return validateTicket(ticket);

    }

    private TicketValidation validateTicket(Ticket ticket) {
        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setValidationMethod(TicketValidationMethod.QR_SCAN);

        TicketValidationStatusEnum ticketValidationStatus  = ticket.getValidations().stream()
                .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
                .findFirst()
                .map(v -> TicketValidationStatusEnum.INVALID)
                .orElse(TicketValidationStatusEnum.VALID);

        ticketValidation.setStatus(ticketValidationStatus);
        return ticketValidationRepository.save(ticketValidation);
    }

    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(TicketTypeNotFoundException::new);
    return validateTicket(ticket);
    }
}
