package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.UIConstant_cn;
import fudan.se.lab4.constant.UIConstant_en;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: jiaxing liu
 * @Date: 2019/5/17 12:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageServiceImplTest {
    private LanguageServiceImpl obj;
    @Before
    public void setUp() {
        obj = LanguageServiceImpl.getInstance();
    }

    @After
    public void tearDown() {
        obj = null;
    }

    @Test
    public void testGetValueInChinese(){
        String language = "Chinese";
        obj.updateLanguage(language);
        assertEquals(obj.getValue("USERNAME_OR_PASS_ERROR"), UIConstant_cn.USERNAME_OR_PASS_ERROR);
    }

    @Test
    public void testGetValueInEnglish(){
        String language = "English";
        obj.updateLanguage(language);
        assertEquals(obj.getValue("USERNAME_OR_PASS_ERROR"), UIConstant_en.USERNAME_OR_PASS_ERROR);
    }
}