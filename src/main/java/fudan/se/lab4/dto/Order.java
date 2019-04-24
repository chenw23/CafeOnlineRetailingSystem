package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 6442456165785725948L;
    private String id;
    private List<OrderItem> orderItems;

    public Order(String id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public Order() {
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
            assert orderItem.getIngredients() != null : InfoConstant.INGREDIENTS_NULL;
            return orderItem.cost() + orderItem.getIngredients().stream()
                    .map(ingredient -> {
                        assert ingredient != null : InfoConstant.INGREDIENT_NULL;
                        return Menu.getValue(ingredient.getName()) * ingredient.getNumber();
                    })
                    .mapToDouble(Double::doubleValue)
                    .sum();
        }).mapToDouble(Double::doubleValue).sum();
    }
}
