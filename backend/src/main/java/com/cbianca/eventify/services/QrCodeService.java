package com.cbianca.eventify.services;

import com.cbianca.eventify.entities.qr.QrCode;
import com.cbianca.eventify.entities.tickets.Ticket;

import java.util.UUID;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);
    byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);

}
