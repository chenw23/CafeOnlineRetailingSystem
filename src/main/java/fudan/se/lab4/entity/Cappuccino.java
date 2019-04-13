package fudan.se.lab4.entity;

public class Cappuccino extends Coffee {

    /**
     * The cost calculating method for Cappuccino
     *
     * @return return the cost of cappuccino depending on its size
     */
    @Override
    public double cost() {
        double finalPrice;
        finalPrice = getPrice() + size2price(getSize());
        return finalPrice;
    }

}
