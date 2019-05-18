package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.service.LanguageService;
import fudan.se.lab4.util.FileUtil;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LanguageServiceImpl implements LanguageService {


    private Class languageProvider;
    private static Map<String,String> languageReposity = new HashMap();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    static {
        InputStream inputStream = MenuServiceImpl.class.getClassLoader().getResourceAsStream("application-language.properties");
        Properties properties = new Properties();
        try {
            if(inputStream != null) {
                properties.load(inputStream);
                properties.forEach((key, value) -> languageReposity.put(key.toString(), value.toString()));
            }
        } catch (IOException e) {
            logger.info(InfoConstant.FILE_NOT_FOUND);
        }
    }

    private static LanguageServiceImpl obj;


    /**
     * the default language is English.
     */
    private LanguageServiceImpl(){
        String defaultLang = "English";
        updateLanguage(defaultLang);
    }


    /**
     * the single instance model.
     * @return the special instance.
     */
    public static LanguageServiceImpl getInstance(){
        if(obj == null){
            obj = new LanguageServiceImpl();
        }
        return obj;
    }

    public void updateLanguage(String language){
        try{
            languageProvider = Class.forName(FileConstant.UI_CONSTANT_PATH + languageReposity.get(language));
        } catch (ClassNotFoundException e) {
            logger.info(MessageFormat.
                    format(InfoConstant.CLASS_NOT_FOUND,FileConstant.UI_CONSTANT_PATH +languageReposity.get(language)));
        }
    }

    public String getValue(String name){

        String result = MessageFormat.format(InfoConstant.LANGUAGE_ERROR,name);
        try{
            Field f = languageProvider.getField(name);
            result = f.get(languageProvider).toString();
        }catch (NoSuchFieldException | IllegalAccessException e){
            logger.info(result);
        }
        return result;
    }


}
