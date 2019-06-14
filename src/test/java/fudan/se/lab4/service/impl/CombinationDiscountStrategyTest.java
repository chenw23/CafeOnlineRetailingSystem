package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Date: 2019/5/18 12:37
 *
 * @author jiaxing liu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CombinationDiscountStrategyTest {
    private CombinationDiscountStrategy obj;
    private ArrayList<Ingredient> ingredients;

    @Before
    public void setUp() {
        obj = new CombinationDiscountStrategy();
        ingredients = TestHelper.getIngredients();
    }

    @After
    public void tearDown() {
        obj = null;
        ingredients = null;
    }

    @Test
    public void testGetDiscountInRMB() {
        assertEquals(obj.getDiscount(getOrder("cny")).getDiscount(), 52.94, 0.001);
    }

    @Test
    public void testGetDiscountInHKD() {
        assertEquals(obj.getDiscount(getOrder("hkd")).getDiscount(), 59.25999999999999, 0.001);
    }

    public Order getOrder(String currency) {
        //discount{rmb:11.0 + 18.0 hkd:12.5 + 20.0}
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 1));
        return TestHelper.getOrder(currency, orderItems);
    }
}