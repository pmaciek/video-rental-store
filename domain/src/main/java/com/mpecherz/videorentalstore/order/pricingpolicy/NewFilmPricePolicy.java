package com.mpecherz.videorentalstore.order.pricingpolicy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NewFilmPricePolicy extends PricePolicy {

    @Override
    public BigDecimal getPrice(int days) {
        return PREMIUM_PRICE.multiply(new BigDecimal(days));
    }
}
