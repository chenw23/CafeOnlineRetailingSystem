package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.Coffee;
import fudan.se.lab4.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Map;

public class PriceServiceImpl implements PriceService {

    /**
     * @param order Coffee and its number.
     *              eg. if Espresso is 4$, 2 cups of Espresso is 8$.
     * @return the total price
     */
    public double cost(Map<Coffee, Integer> order) {
        if (order == null) throw new RuntimeException(InfoConstant.ORDER_NULL);
        double orderPrice = 0;
        Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
        Coffee coffee;
        int number;
        for (Map.Entry<Coffee, Integer> entry : order.entrySet()) {
            coffee = entry.getKey();
            number = entry.getValue();
            if (number < 0) {
                throw new RuntimeException(InfoConstant.COFFEE_NUMBER_ERROR);
            }
            if (coffee == null)
                throw new RuntimeException(InfoConstant.COFFEE_NULL);
            orderPrice += coffee.cost() * number;
            logger.info(MessageFormat.format(InfoConstant.ORDER_PRICE_INFORMATION,
                    coffee.getName(), coffee.getSize(), number, coffee.cost()));
        }
        return orderPrice;
    }
}