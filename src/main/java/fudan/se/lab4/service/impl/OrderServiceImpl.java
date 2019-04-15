package fudan.se.lab4.service.impl;


import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public PaymentInfo pay(Order order) {
        double totalPrice = order.getTotalPrice();
        ArrayList<String> msgs = new ArrayList<>();

        double discount;
        double discount1 = combination(order,msgs);
        double discount2 = fullReduction(totalPrice);
        if (discount1 > discount2){
            discount = discount1;
        }
        else {
            discount = discount2;
            msgs.clear();
            msgs.add(String.format("所有商品满100$省30$,折扣 %f",discount2));
        }

        return new PaymentInfo(totalPrice, discount,
                totalPrice - discount, msgs);
    }

    /**
     * @param order
     * @return the total discount of the combination
     */
    private double combination(Order order,ArrayList<String> msgs) {
        return discountOfLargeEspresso(order,msgs) + discountOfTea(order,msgs)
                + discountOfCappuccino(order,msgs);
    }

    /**
     * @param order
     * @return the discount for Espresso
     * Twenty percent off for every two cups of large espresso
     */
    private double discountOfLargeEspresso(Order order,ArrayList<String> msgs) {
        //大杯Espresso, 2杯8折
        int count = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getName().equals("Espresso") && orderItem.getSize() == 3) {
                count++;
            }
        }
        count = count / 2;
        int discount = count * 8;//(countEspresso/2)*0.2*20*2;
        if (count!=0)
            msgs.add(String.format("大杯Espresso 2杯8折，折扣 %d$", discount));
        return discount;



    }

    /**
     * @param order
     * @return the discount for tea
     * buy 3 get 1 for free
     */
    private double discountOfTea(Order order,ArrayList<String> msgs) {
        //Tea 买3送1
        int countGreenTea = 0;
        int countReaTea = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getName().equals("GreenTea")) {
                countGreenTea++;
            }
            if (orderItem.getName().equals("RedTea")) {
                countReaTea++;
            }
        }
        int freeNumber = (countGreenTea + countReaTea) / 4;
        int discount = freeNumber > countReaTea ? (countReaTea * 18 + (freeNumber - countReaTea) * 16) : countReaTea * 18;
        if (freeNumber!=0)
            msgs.add(String.format("Tea 买3送1，折扣 %d$", discount));
        return discount;
    }

    /**
     * @param order
     * @return the discount for Cappuccino
     * The second cup is half price
     */
    private double discountOfCappuccino(Order order,ArrayList<String> msgs) {
        //Cappuccino 第二杯半价
        int countCappuccino = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getName().equals("Cappuccino")) {
                countCappuccino++;
            }
        }
        countCappuccino = countCappuccino / 2;
        int discount =  countCappuccino * 11;
        if (countCappuccino!=0)
            msgs.add(String.format("Cappuccino 第二杯半价，折扣 %d$", discount));
        return discount;
    }


    private double fullReduction(double totalPrice) {
        return (totalPrice / 100) * 30;
    }
}
