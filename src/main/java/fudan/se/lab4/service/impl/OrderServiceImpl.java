package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.OrderService;
import fudan.se.lab4.util.LogUtil;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {
    private ArrayList<MarketingStrategy> strategies = new ArrayList<>();
    /**
     * @param order the order contains the items purchased
     * @return the payment information
     */
    @Override
    public PaymentInfo pay(Order order) {
        checkNull(order);
        if (strategies.size() == 0) {
            ArrayList<String> msg = new ArrayList<>();
            msg.add("");
            return new PaymentInfo(order.totalPrice(), 0, order.totalPrice(), msg);
        }

        PaymentInfo best = strategies.get(0).getDiscount(order);

        PaymentInfo paymentInfo;
        for (MarketingStrategy marketingStrategy : strategies) {
            paymentInfo = marketingStrategy.getDiscount(order);
            if (paymentInfo.getDiscount() > best.getDiscount()) {
                best = paymentInfo;
            }
        }
        LogUtil.LogSuccess(best, order.getId());
        return best;
    }

    public void setStrategies(ArrayList<MarketingStrategy> strategies) {
        this.strategies = strategies;
    }

    private void checkNull(Order order) {
        if (order==null) {
            LogUtil.LogError(InfoConstant.ORDER_NULL);
        }
        assert order != null : InfoConstant.ORDER_NULL;
        if (order.getOrderItems() == null) {
            LogUtil.LogError(InfoConstant.ORDER_ITEMS_NULL, order.getId());
        }
        assert order.getOrderItems() != null : InfoConstant.ORDER_ITEMS_NULL;
    }

}
