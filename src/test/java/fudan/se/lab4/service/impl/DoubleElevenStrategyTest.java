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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Date: 2019/5/17 12:30
 *
 * @author jiaxing liu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubleElevenStrategyTest {
    private DoubleElevenStrategy obj;

    @Before
    public void setUp() {
        obj = new DoubleElevenStrategy();
    }

    @After
    public void tearDown() {
        obj = null;
    }

    @Test
    public void testGetDiscountInRMB() {
        //The test is ugly. When the date is 11-11, the strategy is ok.
        if (isDoubleEleven()) {
            assertEquals(14.2, obj.getDiscount(getOrder(InfoConstant.NAME_CNY)).getDiscount(), 0.001);
        } else {
            assertEquals(0, obj.getDiscount(getOrder(InfoConstant.NAME_CNY)).getDiscount(), 0.001);
        }
    }

    @Test
    public void testGetDiscountInHKD() {
        //The test is ugly. When the date is 11-11, the strategy is ok.
        if (isDoubleEleven()) {
            assertEquals(15.9, obj.getDiscount(getOrder(InfoConstant.NAME_HKD)).getDiscount(), 0.001);
        } else {
            assertEquals(0, obj.getDiscount(getOrder(InfoConstant.NAME_HKD)).getDiscount(), 0.001);
        }
    }

    public Order getOrder(String currency) {
        //before discount:{rmb:28.4 hkd:31.8}
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_MILK, 2));
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 2));
        return TestHelper.getOrder(currency, orderItems);
    }

    public boolean isDoubleEleven() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return "11-11".equals(sdf.format(dt));
    }
}