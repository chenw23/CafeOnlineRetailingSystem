package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 6442456165785725948L;
    private String id;
    private List<OrderItem> orderItems;
    private String currency;

    public Order(String id,String currency, List<OrderItem> orderItems) {
        this.currency = currency;
        this.id = id;
        this.orderItems = orderItems;
    }

    public Order() {}

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {

        assert orderItems != null : InfoConstant.ORDER_ITEMS_NULL;
        return orderItems.stream().map(orderItem -> {
                    assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
                    return orderItem.cost(currency);
                })
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
