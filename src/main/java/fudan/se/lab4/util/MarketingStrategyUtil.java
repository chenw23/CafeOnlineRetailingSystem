package fudan.se.lab4.util;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.impl.MenuServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author: jiaxing liu
 * @Date: 2019/6/22 13:51
 */
public class MarketingStrategyUtil {
    private static ArrayList<MarketingStrategy> strategies = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    static {
        InputStream inputStream = MenuServiceImpl.class.getClassLoader().getResourceAsStream("application-strategies.properties");
        Properties properties = new Properties();
        try {
            if (inputStream != null) {
                properties.load(inputStream);

                properties.forEach((key, value) -> {
                    try {
                        Class c = Class.forName(value.toString());
                        strategies.add((MarketingStrategy)c.newInstance());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        logger.info(MessageFormat.format(InfoConstant.CLASS_NOT_FOUND, value.toString()));
                    }
                });
            }
        } catch (IOException e ) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
    }

    public static ArrayList<MarketingStrategy> getStrategies() {
        return new ArrayList<>(strategies);
    }
}
