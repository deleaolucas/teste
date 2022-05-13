package br.com.cvc.broker.business.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QuoteRoomPriceDto {
    private Long roomID;
    private String categoryName;
    private Double totalPrice;
    @JsonProperty("priceDetail")
    private QuotePerDayDto quotePerDayDto;
}