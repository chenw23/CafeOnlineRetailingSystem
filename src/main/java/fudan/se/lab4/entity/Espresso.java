package fudan.se.lab4.entity;

public class Espresso extends Coffee {
    /**
     * The cost calculating method for Espresso
     *
     * @return return the cost of espresso depending on its size
     */
    @Override
    public double cost() {
        double finalPrice;
        finalPrice = getPrice() + size2price(getSize());
        return finalPrice;
    }
}
