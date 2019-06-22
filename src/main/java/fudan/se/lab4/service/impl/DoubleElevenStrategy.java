package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;

import java.util.ArrayList;

public class DoubleElevenStrategy implements MarketingStrategy {

    /**
     * to check on whether the marketing strategy can be applied to the order or not.
     *
     * @return Can the order has some discounts?
     */
    private boolean isValid() {
        String date = SystemInfo.getInstance().getDate();
        String MMDD = date.substring(5,10);
        return "11-11".equals(MMDD);
    }

    /**
     * to get the discount value in doubleElevenStrategy
     * Fifty percent off on the total price
     *
     * @param order the order to get information
     * @return the discount in doubleElevenStrategy
     */

    @Override
    public PaymentInfo getDiscount(Order order) {
        double totalPrice = order.totalPrice();
        ArrayList<String> msgs = new ArrayList<>();
        if (isValid()) {
            double discount = totalPrice / 2;
            LanguageServiceImpl obj = LanguageServiceImpl.getInstance();
            msgs.add(String.format(obj.getValue(InfoConstant.CONS_DOUBLE_ELEVEN), discount));
            return new PaymentInfo(totalPrice, discount, totalPrice - discount, msgs);
        }
        msgs.add("");
        return new PaymentInfo(totalPrice, 0, totalPrice, msgs);
    }
}
