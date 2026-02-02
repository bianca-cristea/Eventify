package com.cbianca.eventify.services.impl;

import com.cbianca.eventify.entities.tickets.Ticket;
import com.cbianca.eventify.repositories.TicketRepository;
import com.cbianca.eventify.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    @Override
    public Page<Ticket> listTicketsForUser(UUID userId, Pageable pageable) {
        return ticketRepository.findByPurchaserId(userId,pageable);
    }

    @Override
    public Optional<Ticket> getTicketForUser(UUID userId, UUID ticketId) {
        return ticketRepository.findByIdAndPurchaserId(ticketId,userId);
    }
}
