package fudan.se.lab4.controller;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.impl.*;
import fudan.se.lab4.util.MarketingStrategyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
@Api(value = "order", description = "the service to handle orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "submit a order.", response = ResponseEntity.class)
    public ResponseEntity<PaymentInfo> order(@RequestBody @Valid Order order) {
        orderServiceImpl.setStrategies(MarketingStrategyUtil.getStrategies());
        return ok().body(orderServiceImpl.pay(order));
    }
}
