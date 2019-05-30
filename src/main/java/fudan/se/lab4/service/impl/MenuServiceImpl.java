package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.service.MenuService;
import fudan.se.lab4.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MenuServiceImpl implements MenuService {

    private static Map<String, Map<String, Double>> menu = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static MenuServiceImpl obj = new MenuServiceImpl();

    static {
        String currPath = "application-position2currency.properties";
        try {
            InputStream instream = MenuServiceImpl.class.getClassLoader().getResourceAsStream(currPath);
            Properties prop = new Properties();
            prop.load(instream);
            prop.forEach((key, value) -> {
                InputStream inputStream = MenuServiceImpl.class.getClassLoader()
                        .getResourceAsStream(MessageFormat.format(FileConstant.MENU_PROPERTIES, value));
                Properties properties = new Properties();
                try {
                    properties.load(inputStream);
                } catch (IOException e) {
                    logger.info(MessageFormat.format(InfoConstant.FILE_NOT_FOUND, value));
                }
                Map<String, Double> menuItem = new HashMap<>();
                properties.forEach((ky, val) -> menuItem.put(ky.toString(), Double.parseDouble(val.toString())));
                menu.put(value.toString(), menuItem);
            });
        } catch (IOException e) {
            logger.info(MessageFormat.format(InfoConstant.FILE_NOT_FOUND, currPath));
        }
    }

    private MenuServiceImpl() {
    }

    /**
     * the single instance model.
     *
     * @return the special instance.
     */
    public static MenuServiceImpl getInstance() {
        return obj;
    }

    public double getPrice(String currency, String menuItem) {
        assert menu.get(currency) != null : InfoConstant.CURRENCY_ERROR;
        assert menu.get(currency).get(menuItem) != null : InfoConstant.MENUITEM_ERROR;
        return menu.get(currency).get(menuItem);
    }
}
