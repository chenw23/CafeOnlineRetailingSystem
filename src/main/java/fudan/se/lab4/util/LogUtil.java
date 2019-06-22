package fudan.se.lab4.util;

import fudan.se.lab4.dto.PaymentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: jiaxing liu
 * @Date: 2019/6/23 0:16
 */
public class LogUtil {
    private static Logger errorLogger = LoggerFactory.getLogger("syserror");
    private static Logger traceLogger = LoggerFactory.getLogger("ordertrace");

    public static void LogSuccess(PaymentInfo paymentInfo, String ID) {
        String logCon = "ID: " + ID + " The original price is ";
        logCon += paymentInfo.getPrice() + ". The discount price is " + paymentInfo.getDiscountPrice();
        logCon += ". The strategy is " + paymentInfo.getMsgs().get(0) + ".";
        traceLogger.info(logCon);
    }

    public static void LogError(String msg, String ID) {
        String logCon = "ID: " + ID + "    " + msg;
        errorLogger.error(logCon);
    }

    public static void LogError(String msg) {
        errorLogger.error(msg);
    }
}
