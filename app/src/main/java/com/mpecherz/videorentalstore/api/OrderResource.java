package com.mpecherz.videorentalstore.api;

import com.mpecherz.videorentalstore.model.OrderService;
import com.mpecherz.videorentalstore.model.VideoOrderRequest;
import com.mpecherz.videorentalstore.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getRental(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addOrder(@Valid @RequestBody VideoOrderRequest request) {
        Order order = orderService.addOrder(request);
        return ResponseEntity.of(Optional.of(order));
    }
}
