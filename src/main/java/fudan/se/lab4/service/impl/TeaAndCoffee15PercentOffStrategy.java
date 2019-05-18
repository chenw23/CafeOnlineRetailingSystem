package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.service.MarketingStrategy;

public class TeaAndCoffee15PercentOffStrategy implements MarketingStrategy {

    /**
     * to check on whether the marketing strategy can be applied to the order or not.
     * @return Can the order has some discounts?
     */
    private boolean isValid(){
        return true;
    }

    /**
     * to get the discount value in TeaAndCoffee15PercentOffStrategy
     * @param order the order to get information
     * @return the discount in TeaAndCoffee15PercentOffStrategy
     */

    @Override
    public double getDiscount(Order order){
        if(isValid()){
            return 0;
        }
        return 0;
    }
}
