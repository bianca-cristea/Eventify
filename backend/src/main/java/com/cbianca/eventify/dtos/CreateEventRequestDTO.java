package com.cbianca.eventify.dtos;
import com.cbianca.eventify.entities.events.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDTO {
    @NotBlank(message = "Please provide event name")
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    @NotBlank(message = "Please provide venue details")
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;

    @NotNull(message = "Please provide event status")
    private EventStatusEnum status;
    @NotEmpty(message = "Please provide at least one ticket type")
    @Valid()
    private List<CreateTicketTypeRequestDTO> ticketTypes;
}
