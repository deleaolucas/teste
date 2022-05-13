package br.com.cvc.broker.infrastructure.api.v1.controller.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomHotel {

    private Long roomID;
    private String categoryName;
    private Double totalPrice;
    private PriceHotel priceDetail;

}