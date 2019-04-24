package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author : jiaxing liu
 * @date : 2019/4/24 13:39
 */
class Menu {
    private static Map<String, Double> map = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    static {
        try {
            InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream("menu.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.forEach((key, value) -> map.put(key.toString(),Double.parseDouble(value.toString())));
        } catch (IOException e) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
    }

    static double getValue(String key) {
        return map.get(key);
    }

    static boolean contain(String key) {
        return map.containsKey(key);
    }

    static boolean isTea(String key) {
        return key.toUpperCase().contains("TEA");
    }
}
