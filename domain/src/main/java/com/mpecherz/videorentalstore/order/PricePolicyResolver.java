package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.Film;
import com.mpecherz.videorentalstore.order.pricingpolicy.PricePolicy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@AllArgsConstructor
class PricePolicyResolver {

    @Resource(name = "pricingPolicy")
    private final Map<String, PricePolicy> pricePolicyMap;

    PricePolicy getPricePolicy(Film.Type type) {
        return pricePolicyMap.get(type.toString());
    }
}
