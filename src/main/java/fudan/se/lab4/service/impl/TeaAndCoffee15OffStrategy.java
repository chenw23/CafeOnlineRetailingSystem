package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;

import java.util.ArrayList;

public class TeaAndCoffee15OffStrategy implements MarketingStrategy {
    /**
     * to check on whether the marketing strategy can be applied to the order or not.
     * @return Can the order has some discounts?
     */
    private boolean isValid(){
        //TODO
        return true;
    }
    @Override
    public PaymentInfo getDiscount(Order order)
    {
        double totalPrice =  order.getTotalPrice();
        if (isValid()){
            double discount = totalPrice*0.15;
            ArrayList<String> msgs = new ArrayList<>();
            msgs.add(String.format(InfoConstant.DISCOUNT_TeaAndCoffee15OFF,discount));
            return new PaymentInfo(totalPrice,discount,totalPrice-discount,msgs);
        }
        return new PaymentInfo(totalPrice,0,totalPrice,null);
    }

}
