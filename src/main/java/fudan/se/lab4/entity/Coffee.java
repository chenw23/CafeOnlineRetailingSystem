package fudan.se.lab4.entity;

import fudan.se.lab4.constant.InfoConstant;

/**
 * The Coffee abstract class offers the unified function for coffee order
 * operations. Each kind of coffee has its own attributes and can have
 * their unique qualities.
 *
 * @author Wang, Chen
 */
public abstract class Coffee {
    private String name;
    private String description;
    private double price;
    private int size;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Any subclass should implement this method to calculate the price of that
     * kind of coffee according to the type.
     *
     * @return the total price of all the cups of coffee of that kind
     */
    public abstract double cost();

    /**
     * The internal helper method converting the size to the price addition on
     * the total price.
     *
     * @param size The specified size number of the cup
     * @return The price addition to the total price
     * @throws RuntimeException if the size of the cup is not in the set list
     */
    double size2price(int size) {
        int cupPrice;
        switch (size) {
            case Size.SMALL:
                cupPrice = 2;
                break;
            case Size.MIDDLE:
                cupPrice = 4;
                break;
            case Size.LARGE:
                cupPrice = 6;
                break;
            default:
                throw new RuntimeException(InfoConstant.CUP_SIZE_ERROR);
        }
        return cupPrice;
    }
}
