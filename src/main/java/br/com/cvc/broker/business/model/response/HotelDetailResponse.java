package br.com.cvc.broker.business.model.response;

import br.com.cvc.broker.business.model.DetailRoomPriceDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDetailResponse {
    private Long id;
    private String name;
    private Long cityCode;
    private String cityName;
    @JsonProperty("rooms")
    private List<DetailRoomPriceDto> detailRoomPriceDtoList;

}