package fudan.se.lab4.service;

import fudan.se.lab4.entity.Coffee;

import java.util.Map;

public interface PriceService {
    /**
     * @param order Coffee and its number.
     *              eg. if Espresso is 4$, 2 cups of Espresso is 8$.
     * @return total price
     */
    double cost(Map<Coffee, Integer> order);
}