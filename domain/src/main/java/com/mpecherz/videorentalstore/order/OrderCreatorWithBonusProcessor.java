package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.CustomerService;
import com.mpecherz.videorentalstore.model.VideoService;
import com.mpecherz.videorentalstore.model.VideoOrderRequest;
import com.mpecherz.videorentalstore.model.Customer;
import com.mpecherz.videorentalstore.model.Order;
import com.mpecherz.videorentalstore.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class OrderCreatorWithBonusProcessor extends OrderCreatorProcessor {
    private final BonusPolicyResolver bonusPolicyResolver;

    @Autowired
    public OrderCreatorWithBonusProcessor(OrderRepository orderRepository, PricePolicyResolver pricePolicyResolver, CustomerService customerService, VideoService videoService, BonusPolicyResolver bonusPolicyResolver) {
        super(orderRepository, pricePolicyResolver, customerService, videoService);
        this.bonusPolicyResolver = bonusPolicyResolver;
    }

    @Override
    protected Order process(VideoOrderRequest request) {
        Order order = super.process(request);
        Customer customer = getCustomer(order.getCustomerId());
        customerService.updateCustomer(createCustomer(customer, getFFPoints(order)));
        return order;
    }

    private int getFFPoints(Order order) {
        return order.getRentals().stream()
                .map(item -> getFilmResource(getRental(item).getVideoId()))
                .map(item -> bonusPolicyResolver.getBonusPolicy(item.getType()).getFFPoints()).reduce(Integer::sum)
                .orElse(0);
    }

    private Rental getRental(String item) {
        return orderRepository.getRental(item);
    }

    private Customer createCustomer(Customer customer, int ffPoints) {
        return new Customer(customer.getId(), customer.getFfPoints() + ffPoints);
    }
}
