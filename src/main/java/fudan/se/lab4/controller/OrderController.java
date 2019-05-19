package fudan.se.lab4.controller;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<PaymentInfo> order(@RequestBody Order order) {
        ArrayList<MarketingStrategy> strategies = new ArrayList<>();
        strategies.add(new DoubleElevenStrategy());
        strategies.add(new CombinationDiscountStrategy());
        strategies.add(new FullDiscountStrategy());
        strategies.add(new TeaAndCoffee15OffStrategy());
        orderServiceImpl.setStrategies(strategies);
        return ok().body(orderServiceImpl.pay(order));
    }
}
