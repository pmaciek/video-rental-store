package com.mpecherz.videorentalstore.order.pricingpolicy;

import java.math.BigDecimal;


public abstract class PricePolicy {

    final static BigDecimal BASIC_PRICE = BigDecimal.valueOf(30);
    final static BigDecimal PREMIUM_PRICE = BigDecimal.valueOf(40);

    public abstract BigDecimal getPrice(int days);
}
