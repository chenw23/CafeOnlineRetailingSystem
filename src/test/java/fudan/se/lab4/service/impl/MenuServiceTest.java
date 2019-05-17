package fudan.se.lab4.service.impl;

import fudan.se.lab4.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {

    private MenuService obj;

    @Before
    public void setUp() {
        obj = MenuService.getInstance();
    }

    @After
    public void tearDown() {
        obj = null;
    }

    @Test
    public void testGetPriceInRMB(){
        double rmbPrice = obj.getPrice("rmb","Cappuccino");
        assertEquals(rmbPrice,22,0.001);
    }

    @Test
    public void testGetPriceInHKD(){
        double hkdPrice = obj.getPrice("hkd","Cappuccino");
        assertEquals(hkdPrice,25,0.001);
    }
}