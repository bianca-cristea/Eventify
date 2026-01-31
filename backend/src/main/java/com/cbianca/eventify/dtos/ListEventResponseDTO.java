package com.cbianca.eventify.dtos;

import com.cbianca.eventify.entities.events.EventStatusEnum;
import com.cbianca.eventify.entities.ticket_types.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListEventResponseDTO {


    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private  LocalDateTime salesStartDate;
    private  LocalDateTime salesEndDate;
    private EventStatusEnum status;
    private List<TicketType> ticketTypes = new ArrayList<>();


}
