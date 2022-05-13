package br.com.cvc.broker.business.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class QuoteHotelDto {
    private Long id;
    private String name;
    private Long cityCode;
    private String cityName;
    @JsonProperty("rooms")
    private List<QuoteRoomPriceDto> quoteRoomPriceDtoList;
}