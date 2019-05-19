package fudan.se.lab4.service.impl;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import java.util.ArrayList;

public class CombinationDiscountStrategy implements MarketingStrategy {
    private ArrayList<String> msgs;
    LanguageServiceImpl obj;
    /**
     * @param order the order contains the items purchased
     * @return the total discount of the combination
     */
    @Override
    public PaymentInfo getDiscount(Order order){
        msgs = new ArrayList<>();
        obj = LanguageServiceImpl.getInstance();

        double totalPrice = order.getTotalPrice();
        double discount =  discountOfLargeEspresso(order, msgs) + discountOfTea(order, msgs)
                + discountOfCappuccino(order, msgs);
        return new PaymentInfo(totalPrice,discount,totalPrice-discount,msgs);
    }
    /**
     * Twenty percent off for every two cups of large espresso
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
        int discount = count * 8;//(countEspresso/2)*0.2*20*2;
        if (count != 0)
            msgs.add(String.format(obj.getValue(InfoConstant.CONS_ESPRESSO), discount));
        return discount;
    }

    /**
     * buy 3 get 1 for free
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

        int countReaTea = (int) order.getOrderItems().stream()
                .filter(orderItem -> {
                    assert orderItem != null : InfoConstant.ORDER_ITEM_NULL;
                    return orderItem.getName().equals(InfoConstant.NAME_REDTEA);
                })
                .count();

        int freeNumber = (countGreenTea + countReaTea) / 4;
        int discount = freeNumber > countReaTea ? (countReaTea * 18 + (freeNumber - countReaTea) * 16) : freeNumber * 18;
        if (freeNumber != 0)
            msgs.add(String.format(obj.getValue(InfoConstant.CONS_TEA), discount));
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
        int discount = countCappuccino * 11;
        if (countCappuccino != 0)
            msgs.add(String.format(obj.getValue(InfoConstant.CONS_CAPPUCCINO), discount));
        return discount;
    }

}
