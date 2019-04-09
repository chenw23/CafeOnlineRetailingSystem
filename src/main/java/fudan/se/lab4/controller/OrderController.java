package fudan.se.lab4.controller;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<PaymentInfo> order(@RequestBody Order order) {
        return ok().body(orderService.pay(order));
    }
}
