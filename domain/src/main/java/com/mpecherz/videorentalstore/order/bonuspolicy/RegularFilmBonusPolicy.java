package com.mpecherz.videorentalstore.order.bonuspolicy;


public class RegularFilmBonusPolicy implements BonusPolicy {
    @Override
    public Integer getFFPoints() {
        return 1;
    }
}
