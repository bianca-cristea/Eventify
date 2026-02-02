package com.cbianca.eventify.mapper;


import com.cbianca.eventify.dtos.*;
import com.cbianca.eventify.entities.events.CreateEventRequest;
import com.cbianca.eventify.entities.events.Event;
import com.cbianca.eventify.entities.events.UpdateEventRequest;
import com.cbianca.eventify.entities.ticket_types.CreateTicketTypeRequest;
import com.cbianca.eventify.entities.ticket_types.TicketType;
import com.cbianca.eventify.entities.ticket_types.UpdateTicketTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
        CreateTicketTypeRequest fromDTO(CreateTicketTypeRequestDTO dto);
        CreateEventRequest fromDTO(CreateEventRequestDTO dto);
        CreateEventResponseDTO toDTO(Event event);

        ListEventTicketTypeResponseDTO toDTO(TicketType ticketType);

        ListEventResponseDTO toListEventResponseDTO(Event event);

        GetEventDetailsTicketTypesResponseDTO toGetEventDetailsTicketTypesResponseDTO(TicketType ticketType);

        GetEventDetailsResponseDTO toGetEventDetailsResponseDTO(Event event);

        UpdateTicketTypeRequest fromDTO(UpdateTicketTypeRequestDTO dto);

        UpdateEventRequest fromDTO(UpdateEventRequestDTO dto);

        UpdateTicketTypeResponseDTO toUpdateTicketTypeResponseDTO(TicketType ticketType);

        UpdateEventResponseDTO toUpdateEventResponseDTO(Event event);

        ListPublishedEventResponseDTO toListPublishedEventResponseDTO(Event event);

        GetPublishedEventDetailsTicketTypesResponseDTO toGetPublishedEventDetailsTicketTypesResponseDTO(TicketType ticketType);

        GetPublishedEventDetailsResponseDTO toGetPublishedEventDetailsResponseDTO(Event event);

}

