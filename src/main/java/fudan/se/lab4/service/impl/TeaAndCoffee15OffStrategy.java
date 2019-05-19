package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;

import java.util.ArrayList;

public class TeaAndCoffee15OffStrategy implements MarketingStrategy {
    /**
     * to check on whether the marketing strategy can be applied to the order or not.
     * @return Can the order has some discounts?
     */
    private boolean isValid(Order order){
        for (OrderItem orderItem: order.getOrderItems()){
            if (orderItem.getName().equals(InfoConstant.NAME_GREENTEA)||
                    orderItem.getName().equals(InfoConstant.NAME_REDTEA)||
                    orderItem.getName().equals(InfoConstant.NAME_CAPPUCCINO)||
                    orderItem.getName().equals(InfoConstant.NAME_ESPRESSO)
            )return true;
        }
        return false;
    }
    @Override
    public PaymentInfo getDiscount(Order order)
    {
        double totalPrice =  order.getTotalPrice();
        ArrayList<String> msgs = new ArrayList<>();
        if (isValid(order)){
            double discount = totalPrice*0.15;
            LanguageServiceImpl obj = LanguageServiceImpl.getInstance();
            msgs.add(String.format( obj.getValue(InfoConstant.CONS_TEA_AND_COFFEE_15_OFF) ,discount));
            return new PaymentInfo(totalPrice,discount,totalPrice-discount,msgs);
        }
        msgs.add("");
        return new PaymentInfo(totalPrice,0,totalPrice,msgs);
    }

}
