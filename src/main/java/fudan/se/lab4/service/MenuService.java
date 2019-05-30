package fudan.se.lab4.service;

public interface MenuService {
    /**
     * @param menuItem the item menu.
     * @return the price of the item.
     */
    double getPrice(String currency, String menuItem);
}
