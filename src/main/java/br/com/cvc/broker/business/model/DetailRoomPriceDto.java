package br.com.cvc.broker.business.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetailRoomPriceDto {
    private Long roomID;
    private String categoryName;
    @JsonProperty("price")
    private DetailHostingPriceDto detailHostingPriceDto;
}