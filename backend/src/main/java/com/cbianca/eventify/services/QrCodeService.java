package com.cbianca.eventify.services;

import com.cbianca.eventify.entities.qr.QrCode;
import com.cbianca.eventify.entities.tickets.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

}
