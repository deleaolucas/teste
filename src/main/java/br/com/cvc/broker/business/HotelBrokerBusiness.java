package br.com.cvc.broker.business;

import br.com.cvc.broker.business.exception.QuoteHotelNotFoundException;
import br.com.cvc.broker.business.mapper.HotelBrokerMapper;
import br.com.cvc.broker.business.model.QuoteHotelDto;
import br.com.cvc.broker.business.model.QuotePerDayDto;
import br.com.cvc.broker.business.model.QuoteRoomPriceDto;
import br.com.cvc.broker.business.model.response.HotelDetailResponse;
import br.com.cvc.broker.enums.HotelBrokerEnum;
import br.com.cvc.broker.infrastructure.api.v1.controller.model.response.QuoteHotelResponse;
import br.com.cvc.broker.infrastructure.service.HotelBrokerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class HotelBrokerBusiness {

    @Autowired
    private HotelBrokerService hotelBrokerService;

    public List<QuoteHotelResponse> getListQuoteHotelByIdCity(Integer id, String checking, String checkout,
                                                              Long numberAdults, Long numberChildren) {
        var quoteHotelDtoList = build(Objects.requireNonNull(Optional.of(hotelBrokerService.getHotelDetailsByCityCode(id))
                        .orElseThrow(() -> new QuoteHotelNotFoundException("Quote Hotel Not Found")).getBody()),
                checking, checkout, numberAdults, numberChildren);

        return HotelBrokerMapper.mapToQuoteResponse(quoteHotelDtoList);
    }

    public List<QuoteHotelResponse> getQuoteHotelByIdHotel(Integer id, String checking, String checkout,
                                                           Long numberAdults, Long numberChildren) {
        var quoteHotelDtoList = build(Objects.requireNonNull(Optional.of(hotelBrokerService.getHotelDetailsByIdHotel(id))
                .orElseThrow(() -> new QuoteHotelNotFoundException("Quote Hotel Not Found")).getBody()), checking, checkout, numberAdults, numberChildren);

        return HotelBrokerMapper.mapToQuoteResponse(quoteHotelDtoList);
    }

    private List<QuoteHotelDto> build(List<HotelDetailResponse> hotelDetailsResponseList, String checkin, String checkout,
                                      Long numberAdults, Long numberChildren) {
        List<QuoteHotelDto> quoteHotelResponseList = new ArrayList<>();

        hotelDetailsResponseList.forEach(hotelDetailResponse -> {
            QuoteHotelDto quoteHotelDto = QuoteHotelDto.builder().build();
            List<QuoteRoomPriceDto> quoteRoomPriceDtoList = new ArrayList<>();

            quoteHotelDto.setId(hotelDetailResponse.getId());
            quoteHotelDto.setCityName(hotelDetailResponse.getCityName());
            quoteHotelDto.setName(hotelDetailResponse.getName());
            quoteHotelDto.setCityCode(hotelDetailResponse.getCityCode());

            hotelDetailResponse.getDetailRoomPriceDtoList().forEach(roomHotel -> {
                QuoteRoomPriceDto quoteRoomPriceDto = QuoteRoomPriceDto.builder().build();
                var days = days(checkin, checkout);
                var perDayAdult = (roomHotel.getDetailHostingPriceDto().getAdult() * numberAdults) * days;
                var perDayChild = (roomHotel.getDetailHostingPriceDto().getChild() * numberChildren) * days;

                quoteRoomPriceDto.setTotalPrice((perDayAdult / HotelBrokerEnum.COMMISSION.getValue()) + (perDayChild / HotelBrokerEnum.COMMISSION.getValue()));
                quoteRoomPriceDto.setRoomID(roomHotel.getRoomID());
                quoteRoomPriceDto.setCategoryName(roomHotel.getCategoryName());
                quoteRoomPriceDto.setQuotePerDayDto(QuotePerDayDto.builder()
                        .pricePerDayAdult(perDayAdult)
                        .pricePerDayChild(perDayChild).build());
                quoteRoomPriceDtoList.add(quoteRoomPriceDto);
            });
            quoteHotelDto.setQuoteRoomPriceDtoList(quoteRoomPriceDtoList);
            quoteHotelResponseList.add(quoteHotelDto);
        });
        return quoteHotelResponseList;
    }

    @SneakyThrows
    public Long days(String checking, String checkout) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(checking);
        Date secondDate = sdf.parse(checkout);

        long diff = secondDate.getTime() - firstDate.getTime();

        TimeUnit time = TimeUnit.DAYS;
        return time.convert(diff, TimeUnit.MILLISECONDS);
    }

}
