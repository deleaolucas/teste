package br.com.cvc.broker.business.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuotePerDayDto {
    private Double pricePerDayAdult;
    private Double pricePerDayChild;
}


