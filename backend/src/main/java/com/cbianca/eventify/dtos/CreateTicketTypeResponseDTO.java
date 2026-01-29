package com.cbianca.eventify.dtos;

import com.cbianca.eventify.entities.events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeResponseDTO {
    private UUID id;
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;
    private Event event;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
