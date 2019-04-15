package fudan.se.lab4.service.impl;


import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public PaymentInfo pay(Order order) {
        double totalPrice = order.getTotalPrice();



        double discount1 = combination(order);
        double discount2 = fullReduction(totalPrice);
        double discount =  discount1>discount2?discount1: discount2;

        return new PaymentInfo(totalPrice, discount,
                totalPrice- discount, null);
    }
    /**
     *
     * @param order
     * @return the total discount of the combination
     */
    private double combination(Order order){
        return discountOfLargeEspresso(order)+discountOfTea(order)
                +discountOfCappuccino(order);
    }

    /**
     *
     * @param order
     * @return the discount for Espresso
     * Twenty percent off for every two cups of large espresso
     */
    private double discountOfLargeEspresso(Order order) {
        //大杯Espresso, 2杯8折
        int count = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getName().equals("Espresso") && orderItem.getSize() == 3) {
                count++;
            }
        }
        return (count / 2) * 8; //(countEspresso/2)*0.2*20*2;

    }

    /**
     *
     * @param order
     * @return the discount for tea
     * buy 3 get 1 for free
     */
    private double discountOfTea(Order order){
        //Tea 买3送1
        int countGreenTea = 0;
        int countReaTea = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getName().equals("GreenTea")){
                countGreenTea++;
            }
            if (orderItem.getName().equals("RedTea")){
                countReaTea++;
            }
        }
        int freeNumber = (countGreenTea+countReaTea)/4;
        return freeNumber>countReaTea? (countReaTea*18+(freeNumber-countReaTea)*16) :countReaTea*18;
    }

    /**
     *
     * @param order
     * @return the discount for Cappuccino
     * The second cup is half price
     */
    private double discountOfCappuccino(Order order){
        //Cappuccino 第二杯半价
        int countCappuccino = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getName().equals("Cappuccino")){
                countCappuccino++;
            }
        }
        return (countCappuccino/2)*11;

    }


    private double fullReduction(double totalPrice){
        return (totalPrice/100)*30;
    }
}
