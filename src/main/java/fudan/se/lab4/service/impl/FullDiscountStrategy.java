package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.LanguageService;
import fudan.se.lab4.service.MarketingStrategy;

import java.util.ArrayList;

public class FullDiscountStrategy implements MarketingStrategy {
    /**
     * @param order the totalPrice of an order
     * @return the discount under the full reduction promotion
     */
    @Override
    public PaymentInfo getDiscount(Order order){
        double totalPrice = order.getTotalPrice();
        double discount = (int) (totalPrice / 100) * 30.0;
        ArrayList<String> msgs = new ArrayList<>();
        LanguageServiceImpl obj = LanguageServiceImpl.getInstance();
        msgs.add(String.format(obj.getValue(InfoConstant.CONS_FULL_REDUCTION),discount));
        return  new PaymentInfo(totalPrice,discount,totalPrice-discount,msgs);
    }
}
