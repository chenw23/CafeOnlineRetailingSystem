package fudan.se.lab4.service;

public interface CurrencyService {
    /**
     *
     * @param menuItem the item menu.
     * @return the price of the item.
     */
    public double getPrice(String currency,String menuItem);

}
