package br.com.cvc.broker.infrastructure.api.v1.controller.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceHotel {
    private Double pricePerDayAdult;
    private Double pricePerDayChild;
}


