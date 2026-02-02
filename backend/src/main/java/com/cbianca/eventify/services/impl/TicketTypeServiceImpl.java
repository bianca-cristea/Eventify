package com.cbianca.eventify.services.impl;

import com.cbianca.eventify.entities.ticket_types.TicketType;
import com.cbianca.eventify.entities.tickets.Ticket;
import com.cbianca.eventify.entities.tickets.TicketStatusEnum;
import com.cbianca.eventify.entities.user.User;
import com.cbianca.eventify.exceptions.TicketSoldOutException;
import com.cbianca.eventify.exceptions.TicketTypeNotFoundException;
import com.cbianca.eventify.exceptions.UserNotFoundException;
import com.cbianca.eventify.repositories.TicketRepository;
import com.cbianca.eventify.repositories.TicketTypeRepository;
import com.cbianca.eventify.repositories.UserRepository;
import com.cbianca.eventify.services.QrCodeService;
import com.cbianca.eventify.services.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id %s was not found",userId)
        ));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId).orElseThrow(() -> new TicketTypeNotFoundException(
                String.format("Ticket type with id %s was not found.", ticketTypeId)
        ));

        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketType.getId());
        Integer totalAvailable = ticketType.getTotalAvailable();

        if(purchasedTickets + 1 > totalAvailable){
            throw new TicketSoldOutException();
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);
    }

}
