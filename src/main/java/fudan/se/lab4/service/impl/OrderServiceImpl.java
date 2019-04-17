package fudan.se.lab4.service.impl;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.OrderService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    /**
     *
     * @param order the order contains the items purchased
     * @return the payment information
     */
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
            msgs.add(String.format(InfoConstant.DISCOUNT_FULL_REDUCTION,discount2));
        }

        return new PaymentInfo(totalPrice, discount,
                totalPrice - discount, msgs);
    }

    /**
     * @param order the order contains the items purchased
     * @return the total discount of the combination
     */
    double combination(Order order,ArrayList<String> msgs) {
        return discountOfLargeEspresso(order,msgs) + discountOfTea(order,msgs)
                + discountOfCappuccino(order,msgs);
    }

    /**
     * Twenty percent off for every two cups of large espresso
     * @param order
     * @return the discount for Espresso
     */
    double discountOfLargeEspresso(Order order,ArrayList<String> msgs) {
        //大杯Espresso, 2杯8折
        int count = (int)order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getName().equals(InfoConstant.NAME_ESPRESSO) && orderItem.getSize() == 3)
                .count();

        count = count / 2;
        int discount = count * 8;//(countEspresso/2)*0.2*20*2;
        if (count!=0)
            msgs.add(String.format(InfoConstant.DISCOUNT_ESPRESSO, discount));
        return discount;



    }

    /**
     * buy 3 get 1 for free
     * @param order the order contains the items purchased
     * @return the discount for tea
     */
    double discountOfTea(Order order,ArrayList<String> msgs) {
        //Tea 买3送1
        int countGreenTea = (int)order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getName().equals(InfoConstant.NAME_GREENTEA))
                .count();

        int countReaTea = (int)order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getName().equals(InfoConstant.NAME_REDTEA))
                .count();

        int freeNumber = (countGreenTea + countReaTea) / 4;
        int discount = freeNumber > countReaTea ? (countReaTea * 18 + (freeNumber - countReaTea) * 16) : freeNumber * 18;
        if (freeNumber!=0)
            msgs.add(String.format(InfoConstant.DISCOUNT_TEA, discount));
        return discount;
    }

    /**
     * The second cup is half price
     * @param order the order contains the items purchased
     * @return the discount for Cappuccino
     */
    double discountOfCappuccino(Order order,ArrayList<String> msgs) {
        //Cappuccino 第二杯半价
        int countCappuccino = (int)order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getName().equals(InfoConstant.NAME_CAPPUCCINO))
                .count();

        countCappuccino = countCappuccino / 2;
        int discount =  countCappuccino * 11;
        if (countCappuccino!=0)
            msgs.add(String.format(InfoConstant.DISCOUNT_CAPPUCCINO, discount));
        return discount;
    }

    /**
     *
     * @param totalPrice the totalPrice of an order
     * @return the discount under the full reduction promotion
     */
    double fullReduction(double totalPrice) {
        return (int)((totalPrice) / 100) * 30.0;
    }
}
