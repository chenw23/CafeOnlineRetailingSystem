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

    public double getTotalPrice(){
        assert orderItems != null: InfoConstant.ORDER_ITEMS_NULL;
        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.cost();
            assert orderItem.getIngredients() != null:InfoConstant.INGREDIENT_NULL;
            for (Ingredient ingredient : orderItem.getIngredients()) {
                totalPrice += ingredient.getPrice() * ingredient.getNumber();
            }
        }
        return totalPrice;
    }
}
