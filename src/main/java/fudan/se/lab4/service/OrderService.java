package fudan.se.lab4.service;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;

public interface OrderService {
    /**
     * @param order the order
     * @return the information of payment.
     */
    PaymentInfo pay(Order order);
}
