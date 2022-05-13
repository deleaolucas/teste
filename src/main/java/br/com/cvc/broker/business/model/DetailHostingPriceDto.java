package br.com.cvc.broker.business.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetailHostingPriceDto {
    private Double adult;
    private Double child;
}


