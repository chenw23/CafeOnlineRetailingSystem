package fudan.se.lab4.service.impl;

public class MenuService {

    private static MenuService obj;

    private MenuService(){}

    /**
     * the single instance model.
     * @return the special instance.
     */
    public static MenuService getInstance(){
        if(obj == null){
            obj = new MenuService();
        }
        return obj;
    }

    /**
     *
     * @param menuItem the item menu.
     * @return the price of the item.
     */
    public double getPrice(String currency,String menuItem){
        //TODO
        return 0;
    }
}
