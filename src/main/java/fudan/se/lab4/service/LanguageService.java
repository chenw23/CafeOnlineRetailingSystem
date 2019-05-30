package fudan.se.lab4.service;

public interface LanguageService {

    /**
     * the language switch
     *
     * @param language the update language
     */
    void updateLanguage(String language);


    /**
     * pass the information name
     *
     * @param name the name of the information
     * @return the value in special language
     */
    String getValue(String name);
}
