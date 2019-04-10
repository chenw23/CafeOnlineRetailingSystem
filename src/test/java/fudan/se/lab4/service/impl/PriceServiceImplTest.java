package fudan.se.lab4.service.impl;

import fudan.se.lab2.constant.InfoConstant;
import fudan.se.lab2.entity.Coffee;
import fudan.se.lab2.entity.Espresso;
import fudan.se.lab2.repository.impl.CappuccinoRepositoryImpl;
import fudan.se.lab2.repository.impl.EspressoRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceServiceImplTest {

    private PriceServiceImpl priceService;
    private Coffee coffee;

    @Before
    public void setUp() {
        priceService = new PriceServiceImpl();
        coffee = new CappuccinoRepositoryImpl().getCappuccino("cappuccino");
    }

    @After
    public void tearDown() {
        priceService = null;
        coffee = null;
    }

    @Test
    public void testCostOKWithCappuccino() {
        //for cappuccino
        int size = 1;
        int count = 2;
        Map<Coffee, Integer> order = new HashMap<>();
        coffee.setSize(size);
        order.put(coffee, count);
        assertEquals(44, priceService.cost(order), 0.001);
    }

    @Test
    public void testCostOKWithEspresso() {
        //for espresso
        int size = 1;
        int count = 2;
        Map<Coffee, Integer> order = new HashMap<>();
        Coffee coffee = new EspressoRepositoryImpl().getEspresso("espresso");
        coffee.setSize(size);
        order.put(coffee, count);
        assertEquals(48, priceService.cost(order), 0.001);
    }

    @Test
    public void testCostNumberError() {
        int size = 2;
        int count = -1; //number error
        Map<Coffee, Integer> order = new HashMap<>();
        coffee.setSize(size);
        order.put(coffee, count);
        try {
            priceService.cost(order);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.COFFEE_NUMBER_ERROR);
        }
    }

    @Test
    public void testCostCupError1() {
        try {
            testCostCupErrorHelper(0, 1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.CUP_SIZE_ERROR);
        }
    }

    @Test
    public void testCostCupError2() {
        try {
            testCostCupErrorHelper(4, 2);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.CUP_SIZE_ERROR);
        }
    }

    private void testCostCupErrorHelper(int size, int count) throws RuntimeException {
        Map<Coffee, Integer> order = new HashMap<>();
        coffee.setSize(size);
        order.put(coffee, count);
        priceService.cost(order);
    }

    @Test
    public void testCostNull() {
        try {
            priceService.cost(null);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.ORDER_NULL);
        }
    }

    @Test
    public void testCostCoffeeNull() {
        int count = 2;
        Map<Coffee, Integer> order = new HashMap<>();
        order.put(null, count);
        try {
            priceService.cost(order);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.COFFEE_NULL);
        }
    }
}