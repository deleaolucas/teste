package br.com.cvc.broker.infrastructure.service;

import br.com.cvc.broker.business.model.response.HotelDetailResponse;
import br.com.cvc.broker.infrastructure.service.v1.client.HotelBrokerlClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelBrokerService {

    @Autowired
    private HotelBrokerlClient client;

    @SneakyThrows
    public ResponseEntity<List<HotelDetailResponse>> getHotelDetailsByCityCode(Integer id) {
        return client.getHotelDetailsByIdCity(id);
    }

    @SneakyThrows
    public ResponseEntity<List<HotelDetailResponse>> getHotelDetailsByIdHotel(Integer id) {
        return client.getHotelDetailsByIdHotel(id);
    }

}
