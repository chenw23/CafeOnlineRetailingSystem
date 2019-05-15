package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: jiaxing liu
 * @Date: 2019/5/15 10:30
 */
public class LanguageService {
    private static Class LanguageProvider;
    private static Map<String,String> languageReposity = new HashMap();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    static {
        InputStream inputStream = MenuService.class.getClassLoader().getResourceAsStream("application-language.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            properties.forEach((key,value)->languageReposity.put(key.toString(),value.toString()));
        } catch (IOException e) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
    }

    /**
     *
     * @param language the update language
     */
    public static void updateLanguage(String language){
        String path = "fudan.se.lab4.constant.InfoConstant_";
        try{
            LanguageProvider = Class.forName(path + languageReposity.get(language).toUpperCase());
        } catch (ClassNotFoundException e) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
    }

    public static String getValue(String type){
        String result = "Language error.";
        try{
            result = LanguageProvider.getField(type).toString();
        }catch (NoSuchFieldException e){
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
        return result;
    }

}
