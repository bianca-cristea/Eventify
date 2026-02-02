package com.cbianca.eventify.dtos;

import com.cbianca.eventify.entities.ticket_validation.TicketValidationMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationRequestDTO {
    private UUID id;
    private TicketValidationMethod method;

}
