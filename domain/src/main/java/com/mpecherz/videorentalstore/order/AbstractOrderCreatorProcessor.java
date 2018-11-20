package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.VideoOrderRequest;
import com.mpecherz.videorentalstore.model.Film;
import com.mpecherz.videorentalstore.model.Order;

import java.math.BigDecimal;

abstract class AbstractOrderCreatorProcessor {

    abstract Order process(VideoOrderRequest request);

    abstract BigDecimal getPrice(int daysToRent, Film.Type Type);
}
