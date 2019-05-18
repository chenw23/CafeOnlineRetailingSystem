package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.service.CurrencyService;
import fudan.se.lab4.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CurrencyServiceImpl implements CurrencyService {

    private static Map<String, Double> menu = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static CurrencyServiceImpl obj;

    private CurrencyServiceImpl(){}

    /**
     * the single instance model.
     * @return the special instance.
     */

    public static CurrencyServiceImpl getInstance(){
        if(obj == null){
            obj = new CurrencyServiceImpl();
        }
        return obj;
    }

    public double getPrice(String currency,String menuItem){
        String path = "application-menu-"+currency+".properties";
        try {
            InputStream inputStream = CurrencyServiceImpl.class.getClassLoader().getResourceAsStream(path);
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.forEach((key, value) -> menu.put(key.toString(),Double.parseDouble(value.toString())));
        } catch (IOException e) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
        return menu.get(menuItem);
    }
}
