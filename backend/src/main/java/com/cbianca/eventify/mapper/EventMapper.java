package com.cbianca.eventify.mapper;


import com.cbianca.eventify.dtos.CreateEventRequestDTO;
import com.cbianca.eventify.dtos.CreateEventResponseDTO;
import com.cbianca.eventify.dtos.CreateTicketTypeRequestDTO;
import com.cbianca.eventify.entities.events.CreateEventRequest;
import com.cbianca.eventify.entities.events.Event;
import com.cbianca.eventify.entities.ticket_types.CreateTicketTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
        CreateTicketTypeRequest fromDTO(CreateTicketTypeRequestDTO dto);
        CreateEventRequest fromDTO(CreateEventRequestDTO dto);
        CreateEventResponseDTO toDTO(Event event);
}
