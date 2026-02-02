package com.cbianca.eventify.dtos;

import com.cbianca.eventify.entities.ticket_validation.TicketValidationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketValidationResponseDTO {
    private UUID ticketId;
    private TicketValidationStatusEnum status;
}
