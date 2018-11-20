package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.Film;
import com.mpecherz.videorentalstore.order.bonuspolicy.BonusPolicy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@AllArgsConstructor
class BonusPolicyResolver {

    @Resource(name = "bonusPolicy")
    private final Map<String, BonusPolicy> bonusPolicyMap;

    BonusPolicy getBonusPolicy(Film.Type type) {
        return bonusPolicyMap.get(type.toString());
    }
}
