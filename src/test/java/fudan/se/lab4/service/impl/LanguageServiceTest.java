package fudan.se.lab4.service.impl;

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
public class LanguageServiceTest {
    private LanguageService obj;
    @Before
    public void setUp() {
        obj = LanguageService.getInstance();
    }

    @After
    public void tearDown() {
        obj = null;
    }

    @Test
    public void testGetValueInChinese(){
        String language = "Chinese";
        obj.updateLanguage(language);
        //TODO:liu.Here the information should be a reference, not a built-in constant.
        assertEquals(obj.getValue("USERNAME_OR_PASS_ERROR"),"用户名或密码错误");
    }

    @Test
    public void testGetValueInEnglish(){
        String language = "English";
        obj.updateLanguage(language);
        //TODO:liu.Here the information should be a reference, not a built-in constant.
        assertEquals(obj.getValue("USERNAME_OR_PASS_ERROR"),"Username or password error.");
    }
}