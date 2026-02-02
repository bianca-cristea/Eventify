package com.cbianca.eventify.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketResponseTicketTypeDTO {
    private UUID id;
    private String name;
    private Double price;
}
