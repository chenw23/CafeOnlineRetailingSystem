package fudan.se.lab4.service.impl;

import fudan.se.lab2.constant.InfoConstant;
import fudan.se.lab2.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceImplTest {
    private AccountServiceImpl accountService;
    private User user;

    @Before
    public void setUp() {
        accountService = new AccountServiceImpl();
        user = new User();
    }

    @After
    public void tearDown() {
        accountService = null;
        user = null;
    }

    @Test
    public void testLoginPasswNotCorrect() {
        //Password not correct
        String name = "starbb_123";
        user.setName(name);
        user.setPassword("123abcde");
        try {
            accountService.login(user);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), InfoConstant.USERNAME_OR_PASS_ERROR);
        }
    }

    @Test
    public void testLoginUserNotExist() {
        //user not exist
        String name = "userNull";
        user.setName(name);
        user.setPassword("123abcde");
        try {
            accountService.login(user);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), MessageFormat.format(InfoConstant.Entity_NOT_FOUND, name));
        }
    }

    @Test
    public void testLoginOK() {
        //Qwertkylin,123456
        user.setName("starbb_123");
        user.setPassword("123456_abc");
        assertTrue(accountService.login(user));
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testNameNull() {
        assertFalse(accountService.checkName(null));
    }

    @Test
    public void testNameBeginWithoutStarbb() {
        String testName = "abcbb_123";
        assertFalse(accountService.checkName(testName));
    }

    @Test
    public void testNameWithOtherChar() {
        //other character here:%
        String testName = "starbb_12%";
        assertFalse(accountService.checkName(testName));
    }

    @Test
    public void testNameShorterThan8() {
        //length here:7
        String testName = "starbb_";
        assertFalse(accountService.checkName(testName));
    }

    @Test
    public void testNameLongerThan49() {
        //length here:50
        String testName = "starbb_1234afas121e12313afa23eqfawfgt5wfaqwreqwr16";
        assertFalse(accountService.checkName(testName));
    }

    @Test
    public void testNameOK() {
        String testName = "starbb_12AC";
        assertTrue(accountService.checkName(testName));
    }

    @Test
    public void testPasswNull() {
        assertFalse(accountService.checkPassword(null));
    }

    @Test
    public void testPasswWithoutUnderline() {
        String testPassw = "1234abcdef";
        assertFalse(accountService.checkPassword(testPassw));
    }

    @Test
    public void testPasswWithoutDigit() {
        String testPassw = "_abcdef_ads";
        assertFalse(accountService.checkPassword(testPassw));
    }

    @Test
    public void testPasswWithoutLetter() {
        String testPassw = "_123456789";
        assertFalse(accountService.checkPassword(testPassw));
    }

    @Test
    public void testPasswWithOtherChar() {
        //other character here:*
        String testPassw = "123_abcde3da&";
        assertFalse(accountService.checkPassword(testPassw));
    }

    @Test
    public void testPasswShorterThan8() {
        //length here:7
        String testPassw = "123_abc";
        assertFalse(accountService.checkPassword(testPassw));
    }

    @Test
    public void testPasswLongerThan99() {
        //length here:100
        String testPassw = "a123_abcafnasfasaf12222222224wfeafnalkfjl122222222222efsnvkljsf3443fasvasfgwq4r3w4fvrfeatg24rwsvcw35";
        assertFalse(accountService.checkPassword(testPassw));
    }

    @Test
    public void testPasswOK() {
        String testPassw = "123_abc12";
        assertTrue(accountService.checkPassword(testPassw));
    }

    @Test
    public void testSignUpOK() {
        Date date = new Date();
        String name = "starbb_" + (new SimpleDateFormat("MM_dd_hh_mm_ss")).format(date);
        user.setName(name);
        user.setPassword("123456_abc");
        assertTrue(accountService.signup(user));
    }

    @Test
    public void testSignUpError() {
        //Qwertkylin,123456
        user.setName("Qwertkylin");
        user.setPassword("123456");
        assertFalse(accountService.signup(user));
    }

    @Test
    public void testCheckStatusOK() {
        user.setName("starbb_123");
        user.setPassword("123456_abc");
        accountService.login(user);
        assertTrue(accountService.checkStatus());
    }

    @Test
    public void testCheckStatusFalse() {
        assertFalse(accountService.checkStatus());
    }
}