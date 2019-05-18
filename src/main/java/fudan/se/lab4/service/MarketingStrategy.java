package fudan.se.lab4.service;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;

public interface MarketingStrategy {

    /**
     * using the strategy, how much the discount would be?
     * It should firstly call the isValid.
     * @return the paymentInfo
     */
    PaymentInfo getDiscount(Order order);
}
