package com.mpecherz.videorentalstore.order.pricingpolicy;

import java.math.BigDecimal;

public class OldFilmPricePolicy extends PricePolicy {
    private static final int BEST_PRICE_DAYS_LIMIT = 5;

    @Override
    public BigDecimal getPrice(int days) {
        return days <= BEST_PRICE_DAYS_LIMIT ? BASIC_PRICE :
                BASIC_PRICE.add(BASIC_PRICE.multiply(new BigDecimal(days - BEST_PRICE_DAYS_LIMIT)));
    }
}
