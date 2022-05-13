package br.com.cvc.broker.infrastructure.api.v1.controller.model.response;

import br.com.cvc.broker.infrastructure.api.v1.controller.model.RoomHotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuoteHotelResponse {

    private Long id;
    private String cityName;
    @JsonProperty("room")
    private List<RoomHotel> roomHotelList;

}
