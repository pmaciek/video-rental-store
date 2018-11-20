package com.mpecherz.videorentalstore.api;

import com.mpecherz.videorentalstore.model.OrderService;
import com.mpecherz.videorentalstore.model.VideoReturnRentalRequest;
import com.mpecherz.videorentalstore.model.Order;
import com.mpecherz.videorentalstore.model.Rental;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/orders/{orderId}/rentals")
@Slf4j
@RequiredArgsConstructor
public class RentalResource {

    private final OrderService orderService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Rental getRental(@PathVariable String orderId, @PathVariable String id) {
        return orderService.getRental(orderId, id);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rental> getRentals(@PathVariable String orderId) {
        return orderService.getRentalsByOrderId(orderId);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order returnRentals(@PathVariable String orderId, @Valid @RequestBody VideoReturnRentalRequest returnRentalRequest) {
        return orderService.returnRentals(orderId, returnRentalRequest.getListOfVideos());
    }
}
