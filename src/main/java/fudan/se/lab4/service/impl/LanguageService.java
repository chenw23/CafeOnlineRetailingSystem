package fudan.se.lab4.service.impl;

public class LanguageService {

    private String language;

    private static LanguageService obj;

    /**
     * the default language is English.
     */
    private LanguageService(){
        this.language = "English";
    }

    /**
     * the single instance model.
     * @return the special instance.
     */
    public static LanguageService getInstance(){
        if(obj == null){
            obj = new LanguageService();
        }
        return obj;
    }


    /**
     * the language switch
     * @param language the update language
     */
    public void updateLanguage(String language){
        //TODO
    }

    /**
     * pass the information name
     * @param name the name of the information
     * @return the value in special language
     */
    public String getValue(String name){
        //TODO
        return null;
    }

}
