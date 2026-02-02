package com.cbianca.eventify.dtos;

import com.cbianca.eventify.entities.tickets.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketResponseDTO {
    private UUID id;
    private TicketStatusEnum status;
    private ListTicketResponseTicketTypeDTO ticketType;
}
