package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @author: jiaxing liu
 * @Date: 2019/5/14 21:31
 */

public class MenuService {
    private static Map<String,Map<String, Double>> menu = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(MenuService.class);
    static {
        try {
            InputStream inputStream = MenuService.class.getClassLoader().getResourceAsStream("application-position2currency.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.forEach((key, value) -> {
                Properties properties1 = new Properties();
                try {
                    properties1.load(MenuService.class.getClassLoader().getResourceAsStream(MessageFormat.format(FileConstant.MENU_PROPERTIES,value)));
                    Map<String,Double> menu1 = new HashMap<>();
                    properties1.forEach((key1, value1) -> menu1.put(key1.toString(),Double.parseDouble(value1.toString())));
                    menu.put(value.toString(),menu1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
    }

    /**
     *
     * @param menuItem the item menu.
     * @return the price of the item.
     */
    public static double getPrice(String currency,String menuItem){
        return menu.get(currency).get(menuItem);
    }
}
