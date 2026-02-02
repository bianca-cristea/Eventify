package com.cbianca.eventify.services;

import com.cbianca.eventify.entities.tickets.Ticket;

import java.util.UUID;

public interface TicketTypeService {

    Ticket purchaseTicket(UUID userId, UUID ticketType);


}
