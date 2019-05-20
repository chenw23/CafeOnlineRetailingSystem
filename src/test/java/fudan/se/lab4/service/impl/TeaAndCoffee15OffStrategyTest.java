package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author: jiaxing liu
 * @Date: 2019/5/18 12:37
 */
public class TeaAndCoffee15OffStrategyTest {
    private TeaAndCoffee15OffStrategy obj;
    private ArrayList<Ingredient> ingredients;

    @Before
    public void setUp() {
        ingredients = TestHelper.getIngredients();
        obj = new TeaAndCoffee15OffStrategy();
    }

    @After
    public void tearDown() {
        ingredients = null;
        obj = null;
    }


    @Test
    public void testGetDiscountInHKD(){
        assertEquals(obj.getDiscount(getOrder("hkd")).getDiscount(), 9.27, 0.001);
    }

    @Test
    public void testGetDiscountInRMB(){
        assertEquals(obj.getDiscount(getOrder("cny")).getDiscount(), 8.28, 0.001);
    }

    public Order getOrder(String currency){
        //before discount{rmb:32.6 + 22.6 hkd:36.4 + 25.4}
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        return TestHelper.getOrder(currency,orderItems);
    }
}