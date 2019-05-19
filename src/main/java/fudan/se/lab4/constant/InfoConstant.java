package fudan.se.lab4.constant;

/**
 * Date: 2019/4/17 10:24
 *
 * @author jiaxing liu
 */
public class InfoConstant {
    // framework info
    public static final String ENTITY_EXIST = "{0} already exists, name: {1}";
    public static final String ENTITY_NOT_FOUND = "Object not found, name: {0}";
    public static final String FILE_CANNOT_BE_DIR = "Failed to create file, {0} is not a valid name";
    public static final String FILE_NOT_FOUND = "The property file {0} not found.";
    public static final String CLASS_NOT_FOUND = "The class {0} not found.";
    public static final String LANGUAGE_ERROR = "The information {0} not provided";
    public static final String CREATING_PARENT_DIR = "Creating parent directory ...";
    public static final String FAILED_CREAT_DIR = "Failed to create target directory";
    public static final String FAILED_TO_CREATE_FILE = "Failed to create file: {0}";
    public static final String SUCCESS_TO_CREATE_FILE = "Success to create file: {0}";
    public static final String FAILED_TO_CREATE_FILE_REASON = "Failed to create file: {0}, because {1}";

    // your info constant
    public static final String USER_ALREADY_EXIST = "User already exist,name : {0}";
    public static final String USER_LOGIN_SUCCESSFULLY = "User login successfully,name : {0}";
    public static final String USER_SIGNUP_SUCCESSFULLY = "User signup successfully,name : {0}";
    public static final String USER_LOGIN_FAILED = "User login failed"; //其他情况导致的登陆失败是否需要进行判断？
    public static final String USERNAME_OR_PASS_ERROR = "Username or password error.";
    public static final String CUP_SIZE_ERROR = "Coffee size error.";
    public static final String ORDER_NULL = "Order is null.";
    public static final String INGREDIENT_NUMBER_ERROR = "The number of the ingredient is illegal.";
    public static final String ORDER_ITEMS_NULL = "Order items are null";
    public static final String ORDER_ITEM_NULL = "One of the order items is null";
    public static final String INGREDIENTS_NULL = "The ingredients are null";
    public static final String INGREDIENT_NULL = "One of the ingredients is null";
    public static final String INGREDIENT_NAME_ILLEGAL = "The ingredient name is illegal";

    public static final String USER_HAS_LOGED_IN = "USER_HAS_LOGED_IN";
    public static final String PLEASE_LOGIN= "PLEASE_LOGIN";
    public static final String CONS_DOUBLE_ELEVEN = "DISCOUNT_DOUBLE_ELEVEN";
    public static final String CONS_TEA_AND_COFFEE_15_OFF = "DISCOUNT_TEA_AND_COFFEE_15_OFF";
    public static final String CONS_FULL_REDUCTION = "DISCOUNT_FULL_REDUCTION";
    public static final String CONS_CAPPUCCINO = "DISCOUNT_CAPPUCCINO";
    public static final String CONS_TEA = "DISCOUNT_TEA";
    public static final String CONS_ESPRESSO = "DISCOUNT_ESPRESSO";








    public static final String DISCOUNT_CAPPUCCINO = "Cappuccino 第二杯半价，折扣 %d$";
    public static final String DISCOUNT_TEA = "Tea 买3送1，折扣 %d$";
    public static final String DISCOUNT_ESPRESSO = "大杯Espresso 2杯8折，折扣 %d$";
    public static final String DISCOUNT_FULL_REDUCTION = "所有商品满100$省30$,折扣 %f";
    public static final String DISCOUNT_DOUBLE_ELEVEN = "双十一所有商品打五折,折扣 %f";
    public static final String DISCOUNT_TEA_AND_COFFEE_15_OFF = "订单中包含至少一件茶类商品或至少一件咖啡类商品打85折,折扣 %f";


    public static final String NAME_ESPRESSO = "Espresso";
    public static final String NAME_REDTEA = "RedTea";
    public static final String NAME_GREENTEA = "GreenTea";
    public static final String NAME_CAPPUCCINO = "Cappuccino";
    public static final String NAME_MILK = "Milk";
    public static final String NAME_CHOCOLATE = "Chocolate";
    public static final String NAME_CREAM = "Cream";
    public static final String NAME_SUGAR = "Sugar";
}
