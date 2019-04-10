package fudan.se.lab4.constant;

public class InfoConstant {
    // framework info
    public static final String Entity_EXIST = "{0} already exists, name: {1}";
    public static final String Entity_NOT_FOUND = "Object not found, name: {0}";
    public static final String FILE_CANNOT_BE_DIR = "Failed to create file, {0} is not a valid name";
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
    public static final String USER_SIGNUP_FAILED = "User signup failed";
    public static final String USERNAME_OR_PASS_ERROR = "Username or password error.";
    public static final String ENTER_THE_SYSTEM_HINT = "Now you can enter the system:";
    public static final String ENTER_INVALID_WARNING = "Please enter valid input.";
    public static final String USER_REGEX = "[0-9A-Za-z]+";


    public static final String ORDER_PRICE_INFORMATION = "name: {0}, size: {1}, number: {2}, price: {3}$";
    public static final String CUP_SIZE_ERROR = "Coffee size error.";
    public static final String COFFEE_NUMBER_ERROR = "Coffee number error.";
    public static final String ORDER_NULL = "Order is null.";
    public static final String COFFEE_NULL = "Coffee is null.";

}
