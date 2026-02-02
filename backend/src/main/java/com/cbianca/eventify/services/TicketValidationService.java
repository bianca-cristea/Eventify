package com.cbianca.eventify.services;

import com.cbianca.eventify.entities.ticket_validation.TicketValidation;

import java.util.UUID;

public interface TicketValidationService {
    TicketValidation validateTicketByQrCode(UUID qrCodeId);
    TicketValidation validateTicketManually(UUID ticketId);
}
