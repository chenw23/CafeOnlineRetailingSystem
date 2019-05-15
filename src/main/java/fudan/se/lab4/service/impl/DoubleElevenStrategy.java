package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.service.MarketingStrategy;

/**
 * @author: jiaxing liu
 * @Date: 2019/5/15 10:03
 */
public class DoubleElevenStrategy implements MarketingStrategy {

    /**
     * to check on whether the marketing strategy can be applied to the order or not.
     * @param order the order to check
     * @return Can the order has some discounts?
     */
    private boolean isValid(Order order){
        //TODO
        return true;
    }

    @Override
    public double getDiscount(Order order){
        //TODO
        if(isValid(order)){
            return 0;
        }
        return 0;
    }
}
