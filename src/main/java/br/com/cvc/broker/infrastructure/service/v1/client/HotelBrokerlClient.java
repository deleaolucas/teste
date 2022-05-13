package br.com.cvc.broker.infrastructure.service.v1.client;

import br.com.cvc.broker.business.model.response.HotelDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelBrokerlClient {

    @Value("${cvc.endpoint.apihotel}")
    private String BASE_URL;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List<HotelDetailResponse>> getHotelDetailsByIdCity(Integer id) {

        HttpEntity<Void> requestEntity = new HttpEntity<>(new HttpHeaders());

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return restTemplate.exchange(BASE_URL.concat("avail/{id}"),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<HotelDetailResponse>>() {
                },
                params);
    }

    public ResponseEntity<List<HotelDetailResponse>> getHotelDetailsByIdHotel(Integer id) {

        HttpEntity<Void> requestEntity = new HttpEntity<>(new HttpHeaders());

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return restTemplate.exchange(BASE_URL.concat("/{id}"),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<HotelDetailResponse>>() {
                },
                params);
    }

}

