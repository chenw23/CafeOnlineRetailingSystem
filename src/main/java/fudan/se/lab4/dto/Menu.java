package fudan.se.lab4.dto;

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

    static {
        try {
            InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream("menu.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
            Map<String, Double> tmap = new HashMap<String, Double>();
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> objectObjectEntry = iterator.next();
                tmap.put(objectObjectEntry.getKey().toString(), Double.parseDouble(objectObjectEntry.getValue().toString()));
            }
            map.clear();
            map.putAll(tmap);
        } catch (IOException e) {
            e.printStackTrace();
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
