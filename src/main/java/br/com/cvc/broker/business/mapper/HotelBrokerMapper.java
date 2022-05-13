package br.com.cvc.broker.business.mapper;

import br.com.cvc.broker.business.model.QuoteHotelDto;
import br.com.cvc.broker.business.model.QuotePerDayDto;
import br.com.cvc.broker.infrastructure.api.v1.controller.model.PriceHotel;
import br.com.cvc.broker.infrastructure.api.v1.controller.model.RoomHotel;
import br.com.cvc.broker.infrastructure.api.v1.controller.model.response.QuoteHotelResponse;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class HotelBrokerMapper {

    public static List<QuoteHotelResponse> mapToQuoteResponse(List<QuoteHotelDto> quoteHotelDtoList) {
        List<QuoteHotelResponse> quoteHotelResponseList = new ArrayList<>();

        for (QuoteHotelDto quoteHotelDto : quoteHotelDtoList) {
            QuoteHotelResponse quoteHotelResponse = QuoteHotelResponse.builder().build();
            List<RoomHotel> roomHotelList = new ArrayList<>();
            quoteHotelResponse.setId(quoteHotelDto.getId());
            quoteHotelResponse.setCityName(quoteHotelDto.getCityName());

            quoteHotelDto.getQuoteRoomPriceDtoList().forEach(room -> {
                RoomHotel roomHotel = RoomHotel.builder().build();

                roomHotel.setRoomID(room.getRoomID());
                roomHotel.setCategoryName(room.getCategoryName());
                roomHotel.setPriceDetail(mapToPriceHotel(room.getQuotePerDayDto()));
                roomHotel.setTotalPrice(room.getTotalPrice());
                roomHotelList.add(roomHotel);
                quoteHotelResponse.setRoomHotelList(roomHotelList);
            });
            quoteHotelResponseList.add(quoteHotelResponse);
        }
        return quoteHotelResponseList;
    }

    private PriceHotel mapToPriceHotel(QuotePerDayDto quotePerDayDto) {
        return PriceHotel.builder()
                .pricePerDayAdult(quotePerDayDto.getPricePerDayAdult())
                .pricePerDayChild(quotePerDayDto.getPricePerDayChild())
                .build();
    }

}