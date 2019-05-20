package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.OrderService;
import org.springframework.stereotype.Service;

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

          if(strategies.size()==0){
              return new PaymentInfo(order.getTotalPrice(),0,order.getTotalPrice(),new ArrayList<>());
          }

          MarketingStrategy bestStrategy = strategies.get(0);
          int discount = 0;
          for (MarketingStrategy marketingStrategy: strategies){
              if(marketingStrategy.getDiscount(order).getDiscount()>discount){
                  bestStrategy = marketingStrategy;
              }
          }
          return bestStrategy.getDiscount(order) ;
    }

    public void setStrategies(ArrayList<MarketingStrategy> strategies){
        this.strategies = strategies;
    }



    private void checkNull(Order order) {
        assert order != null : InfoConstant.ORDER_NULL;
        assert order.getOrderItems() != null : InfoConstant.ORDER_ITEMS_NULL;
    }
}
