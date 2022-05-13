package br.com.cvc.broker.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HotelBrokerEnum {

    COMMISSION(0.7);
    private final Double value;

    public Double getValue() {
        return value;
    }

}
