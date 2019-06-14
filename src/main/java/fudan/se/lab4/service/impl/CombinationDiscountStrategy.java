package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;

import java.util.ArrayList;

public class CombinationDiscountStrategy implements MarketingStrategy {
    private ArrayList<String> msgs;
    private LanguageServiceImpl languageService;
    private MenuServiceImpl menuService;

    /**
     * @param order the order contains the items purchased
     * @return the total discount of the combination
     */
    @Override
    public PaymentInfo getDiscount(Order order) {
        msgs = new ArrayList<>();
        languageService = LanguageServiceImpl.getInstance();
        menuService = MenuServiceImpl.getInstance();

        double totalPrice = order.totalPrice();
        double discount = discountOfLargeEspresso(order, msgs) + discountOfTea(order, msgs)
                + discountOfCappuccino(order, msgs) + discountOfTeaAndCoffee(order,msgs);
        return new PaymentInfo(totalPrice, discount, totalPrice - discount, msgs);
    }

    /**
     * Twenty percent off for every two cups of large espresso
     *
     * @param order the receive order
     * @return the discount for Espresso
     */
    private double discountOfLargeEspresso(Order order, ArrayList<String> msgs) {
        //大杯Espresso, 2杯8折
        int count = (int) order.getOrderItems().stream().
                filter(orderItem -> {
                    assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
                    return orderItem.getName().equals(InfoConstant.NAME_ESPRESSO) && orderItem.getSize() == 3;
                }).
                count();

        count = count / 2;
        double discount = count * 0.2 * menuService.getPrice(order.getCurrency(), InfoConstant.NAME_ESPRESSO) * 2;//(count/2)*0.2*price*2;
        if (count != 0)
            msgs.add(String.format(languageService.getValue(InfoConstant.CONS_ESPRESSO), discount));
        return discount;
    }

    /**
     * buy 3 get 1 for free
     *
     * @param order the order contains the items purchased
     * @return the discount for tea
     */
    private double discountOfTea(Order order, ArrayList<String> msgs) {
        //Tea 买3送1
        int countGreenTea = (int) order.getOrderItems().stream()
                .filter(orderItem -> {
                    assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
                    return orderItem.getName().equals(InfoConstant.NAME_GREENTEA);
                })
                .count();

        int countRedTea = (int) order.getOrderItems().stream()
                .filter(orderItem -> {
                    assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
                    return orderItem.getName().equals(InfoConstant.NAME_REDTEA);
                })
                .count();

        int freeNumber = (countGreenTea + countRedTea) / 4;
        double priceOfRedTea = menuService.getPrice(order.getCurrency(), InfoConstant.NAME_REDTEA);
        double priceOfGreenTea = menuService.getPrice(order.getCurrency(), InfoConstant.NAME_GREENTEA);
        double discount = freeNumber > countRedTea ? (countRedTea * priceOfRedTea + (freeNumber - countRedTea) * priceOfGreenTea) : freeNumber * priceOfRedTea;
        if (freeNumber != 0)
            msgs.add(String.format(languageService.getValue(InfoConstant.CONS_TEA), discount));
        return discount;
    }

    /**
     * The second cup is half price
     *
     * @param order the order contains the items purchased
     * @return the discount for Cappuccino
     */
    private double discountOfCappuccino(Order order, ArrayList<String> msgs) {
        //Cappuccino 第二杯半价
        int countCappuccino = (int) order.getOrderItems().stream()
                .filter(orderItem -> {
                    assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
                    return orderItem.getName().equals(InfoConstant.NAME_CAPPUCCINO);
                })
                .count();

        countCappuccino = countCappuccino / 2;
        double discount = countCappuccino * menuService.getPrice(order.getCurrency(), InfoConstant.NAME_CAPPUCCINO) / 2;
        if (countCappuccino != 0)
            msgs.add(String.format(languageService.getValue(InfoConstant.CONS_CAPPUCCINO), discount));
        return discount;
    }

    /**
     * 15% off for both coffee and tea
     *
     * @param order the order contains the items purchased
     * @return the discount for Cappuccino
     */
    private double discountOfTeaAndCoffee(Order order,ArrayList<String> msgs){
        boolean hasTea = false;
        boolean hasCoffee = false;
        double discount = 0;
        for (OrderItem orderItem:order.getOrderItems()) {
            if(orderItem.getName().equals(InfoConstant.NAME_GREENTEA)
            || orderItem.getName().equals(InfoConstant.NAME_REDTEA)){
                hasTea = true;
            }
            if(orderItem.getName().equals(InfoConstant.NAME_ESPRESSO)
                    || orderItem.getName().equals(InfoConstant.NAME_CAPPUCCINO)){
                hasCoffee = true;
            }
        }
        if(hasCoffee && hasTea){
            discount = order.totalPrice() * 0.15;
            msgs.add(String.format(languageService.getValue(InfoConstant.CONS_TEA_AND_COFFEE_15_OFF), discount));
        }
        return discount;

    }







}
