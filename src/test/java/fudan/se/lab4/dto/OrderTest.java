package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Date: 2019/4/17 10:06
 *
 * @author jiaxing liu
 */
public class OrderTest {

    /**
     * check the method getTotalPrice when order items are null.
     */
    @Test
    public void testGetTotalPriceWithOrderItemsNull() {
        Order order = getOrder(null);
        try {
            order.totalPrice();
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.ORDER_ITEMS_NULL);
        }
    }

    /**
     * check the method getTotalPrice when one order item's ingredient is null.
     */
    @Test
    public void testGetTotalPriceWithIngredientNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, null, 2));
        Order order = getOrder(orderItems);
        try {
            order.totalPrice();
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.INGREDIENTS_NULL);
        }
    }

    /**
     * check the method getTotalPrice when one order item's ingredient is null.
     */
    @Test
    public void testGetTotalPriceOK() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_CHOCOLATE, 2));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 2));
        Order order = getOrder(orderItems);
        assertEquals(order.totalPrice(), 28.4, 0.01);
    }

    /**
     * check the method getTotalPrice when the size is illegal.
     * Here illegal size is 4
     */
    @Test
    public void testGetTotalPriceWithSizeError() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_MILK, 2));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 4));
        Order order = getOrder(orderItems);
        try {
            order.totalPrice();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.CUP_SIZE_ERROR);
        }
    }

    /**
     * offer a new order with a random ID
     * and a default currency of rmb.
     *
     * @return a new order with an id generted with the date
     */
    private Order getOrder(ArrayList<OrderItem> orderItems) {
        String currency = "cny";
        return new Order(currency, orderItems);
    }
}