package fudan.se.lab4.service;

import fudan.se.lab4.dto.Order;

/**
 * @author: jiaxing liu
 * @Date: 2019/5/15 9:53
 */
public interface MarketingStrategy {

    /**
     * using the strategy, how much the discount would be?
     * It should firstly call the isValid.
     * @return the discount
     */
    double getDiscount(Order order);
}
