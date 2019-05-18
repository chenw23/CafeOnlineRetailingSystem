package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.MarketingStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleElevenStrategy implements MarketingStrategy {

    /**
     * to check on whether the marketing strategy can be applied to the order or not.
     * @return Can the order has some discounts?
     */
    private boolean isValid(){
        String date = SystemInfo.getInstance().getDate();
        String pattern = "\\d{4}-11-11.";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(date);
        return m.matches();
    }

    /**
     * to get the discount value in doubleElevenStrategy
     * Fifty percent off on the total price
     * @param order the order to get information
     * @return the discount in doubleElevenStrategy
     */

    @Override
    public double getDiscount(Order order){
        if(isValid()){
            return (order.getTotalPrice())/2;
        }
        return 0;
    }
}
