package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.*;
import fudan.se.lab4.entity.Beverage;
import fudan.se.lab4.entity.OrderItemPrice;
import fudan.se.lab4.entity.decorator.Decorator;
import fudan.se.lab4.service.DiscountChain;
import fudan.se.lab4.service.OrderService;
import fudan.se.lab4.util.BeverageFactory;
import fudan.se.lab4.util.DecoratorFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public PaymentInfo pay(Order order) {

        double totalPrice = 0;
        List<OrderItemPrice> orderItemPrices = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem x : orderItems) {

            Beverage beverage = BeverageFactory.getBeverageInstanceByName(x);

            List<Ingredient> ingredients = x.getIngredients();
            // without ingredient
            if (null == ingredients || ingredients.size() == 0) {
                orderItemPrices.add(new OrderItemPrice(x, beverage.cost()));
                totalPrice += beverage.cost();
            } else { // with ingredients
                Decorator decorator = null;
                for (int i = 0; i < ingredients.size(); i++) {
                    Ingredient tempIng = ingredients.get(i);
                    if (i == 0) {
                        decorator = DecoratorFactory.getDecoratorByName(
                                tempIng.getName(), beverage, tempIng.getNumber());
                    } else {
                        decorator = DecoratorFactory.getDecoratorByName(
                                tempIng.getName(), decorator, tempIng.getNumber());
                    }
                }
                orderItemPrices.add(new OrderItemPrice(x, decorator.cost()));
                totalPrice += decorator.cost();
            }

        }

        // discount
        DiscountChain espressoDiscount = new EspressoDiscountChain();
        DiscountChain totalPriceDiscount = new TotalPriceDiscountChain();
        espressoDiscount.setNextDiscountChain(totalPriceDiscount);
        DiscountInfo discountInfo = espressoDiscount.discount(orderItemPrices);

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPrice(totalPrice);
        paymentInfo.setDiscount(discountInfo.getDiscount());
        paymentInfo.setDiscountPrice(totalPrice - discountInfo.getDiscount());
        paymentInfo.setMsgs(Arrays.asList(discountInfo.getInfo()));
        return paymentInfo;
    }
}
