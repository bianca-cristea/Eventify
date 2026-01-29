package com.cbianca.eventify.dtos;

import com.cbianca.eventify.entities.events.EventStatusEnum;
import com.cbianca.eventify.entities.ticket_types.TicketType;
import com.cbianca.eventify.entities.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CreateEventResponseDTO {
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private  LocalDateTime salesStartDate;
    private LocalDateTime salesEndDate;
    private EventStatusEnum status;
    private List< CreateTicketTypeResponseDTO> ticketTypes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
