package com.mpecherz.videorentalstore.configuration;

import com.mpecherz.videorentalstore.model.Film;
import com.mpecherz.videorentalstore.order.bonuspolicy.BonusPolicy;
import com.mpecherz.videorentalstore.order.bonuspolicy.NewFilmBonusPolicy;
import com.mpecherz.videorentalstore.order.bonuspolicy.OldFIlmBonusPolicy;
import com.mpecherz.videorentalstore.order.bonuspolicy.RegularFilmBonusPolicy;
import com.mpecherz.videorentalstore.order.pricingpolicy.NewFilmPricePolicy;
import com.mpecherz.videorentalstore.order.pricingpolicy.OldFilmPricePolicy;
import com.mpecherz.videorentalstore.order.pricingpolicy.PricePolicy;
import com.mpecherz.videorentalstore.order.pricingpolicy.RegularPricePolicy;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


@org.springframework.context.annotation.Configuration
public class MainConfiguration {

    @Bean(name = "pricingPolicy")
    Map<String, PricePolicy> pricingPolicy() {

        return new HashMap<String, PricePolicy>() {{

            put(Film.Type.NEW.toString(), new NewFilmPricePolicy());
            put(Film.Type.OLD.toString(), new OldFilmPricePolicy());
            put(Film.Type.REGULAR.toString(), new RegularPricePolicy());

        }};
    }

    @Bean(name = "bonusPolicy")
    Map<String, BonusPolicy> bonusPolicy() {

        return new HashMap<String, BonusPolicy>() {{

            put(Film.Type.NEW.toString(), new NewFilmBonusPolicy());
            put(Film.Type.OLD.toString(), new OldFIlmBonusPolicy());
            put(Film.Type.REGULAR.toString(), new RegularFilmBonusPolicy());

        }};
    }

}
