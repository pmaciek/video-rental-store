package com.mpecherz.videorentalstore.order.bonuspolicy;


public class NewFilmBonusPolicy implements BonusPolicy {
    @Override
    public Integer getFFPoints() {
        return 2;
    }
}
