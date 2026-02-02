package com.cbianca.eventify.mapper;

import com.cbianca.eventify.dtos.TicketValidationResponseDTO;
import com.cbianca.eventify.entities.ticket_validation.TicketValidation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface TicketValidationMapper {


    @Mapping(target = "ticketId",source = "ticket.id")
    TicketValidationResponseDTO toTicketValidationResponseDTO(TicketValidation ticketValidation);


}
