package com.cbianca.eventify.mapper;

import com.cbianca.eventify.dtos.GetTicketResponseDTO;
import com.cbianca.eventify.dtos.ListEventTicketTypeResponseDTO;
import com.cbianca.eventify.dtos.ListTicketResponseDTO;
import com.cbianca.eventify.entities.ticket_types.TicketType;
import com.cbianca.eventify.entities.tickets.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface TicketMapper {

    ListEventTicketTypeResponseDTO toListEventTicketTypeResponseDTO(TicketType ticketType);
    ListTicketResponseDTO toListTicketResponseDTO(Ticket ticket);
    @Mapping(target = "price", source = "ticket.ticketType.price")
    @Mapping(target = "description", source = "ticket.ticketType.description")
    @Mapping(target = "eventVenue", source = "ticket.ticketType.event.venue")
    @Mapping(target = "eventStart", source = "ticket.ticketType.event.start")
    @Mapping(target = "eventEnd", source = "ticket.ticketType.event.end")
    GetTicketResponseDTO toGetTicketResponseDTO(Ticket ticket);
}
