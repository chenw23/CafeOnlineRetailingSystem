package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public PaymentInfo pay(Order order) {
        double totalPrice = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            totalPrice += 0;
            for (Ingredient ingredient : orderItem.getIngredients()) {
                totalPrice += 0;
            }
        }
        //todo: Tang, Xinyue
        double discount = promotion();
        return new PaymentInfo(totalPrice, discount,
                totalPrice * (1 - discount), null);
    }

    private double promotion() {
        //todo: Tang, Xinyue
        return 0;
    }
}
