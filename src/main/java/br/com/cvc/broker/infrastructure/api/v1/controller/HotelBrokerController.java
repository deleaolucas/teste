package br.com.cvc.broker.infrastructure.api.v1.controller;

import br.com.cvc.broker.business.HotelBrokerBusiness;
import br.com.cvc.broker.infrastructure.api.v1.controller.model.response.QuoteHotelResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/broker/hotel")
@Log4j2
public class HotelBrokerController {

    @Autowired
    private HotelBrokerBusiness hotelBrokerBusiness;

    @GetMapping(value = "quote")
    public ResponseEntity<List<QuoteHotelResponse>> getQuoteHotelByIdCity(@RequestParam(name = "idCity") Integer idCity,
                                                                          @RequestParam(name = "checkin") String checkin,
                                                                          @RequestParam(name = "checkout") String checkout,
                                                                          @RequestParam(name = "numberAdults") Long numberAdults,
                                                                          @RequestParam(name = "numberChildren") Long numberChildren) {
        log.info("{} {} {} {} {}", idCity, checkin, checkout, numberAdults, numberChildren);

        return ResponseEntity.ok(hotelBrokerBusiness.getListQuoteHotelByIdCity(idCity, checkin, checkout, numberAdults, numberChildren));
    }

    @GetMapping(value = "find")
    public ResponseEntity<QuoteHotelResponse> getQuoteHotelByIdHotel(@RequestParam(name = "idHotel") Integer idHotel,
                                                                     @RequestParam(name = "checkin") String checkin,
                                                                     @RequestParam(name = "checkout") String checkout,
                                                                     @RequestParam(name = "numberAdults") Long numberAdults,
                                                                     @RequestParam(name = "numberChildren") Long numberChildren) {
        log.info("{} {} {} {} {}", idHotel, checkin, checkout, numberAdults, numberChildren);

        return ResponseEntity.ok(hotelBrokerBusiness.getQuoteHotelByIdHotel(idHotel, checkin, checkout, numberAdults, numberChildren).get(0));
    }
}
