package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.repository.impl.UserRepositoryImpl;
import fudan.se.lab4.service.AccountService;
import fudan.se.lab4.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * This class is in charge of the user account service logic. Any user log-in and register method
 * must be done via this class. Furthermore, this class also provides utility functions for
 * checking the log in status of the user. There are also restrictions on the user name and
 * the password of the user, hence this class provides methods of validating the user name and
 * the password.
 *
 * @author Wang, Chen
 */
public class AccountServiceImpl implements AccountService {
    private static Logger logger = LoggerFactory.getLogger("file");
    private static boolean loginStatus = false;
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private LanguageServiceImpl languageService = LanguageServiceImpl.getInstance();

    /**
     * Provides the log in function of the user
     *
     * @param user saved the name and password
     * @return if login successfully, return true;else throw an RuntimeException
     * @throws RuntimeException if log in failed
     */
    @Override
    public boolean login(User user) {
        //user exist and the password correct
        if (userRepository.getUser(user.getName()) != null &&
                userRepository.getUser(user.getName()).getPassword().equals(user.getPassword())) {
            loginStatus = true;
            String userLoginOKStr = MessageFormat.format(InfoConstant.USER_LOGIN_SUCCESSFULLY, user.getName());
            logger.info(userLoginOKStr);
            return true;
        } else {
            logger.info(InfoConstant.USER_LOGIN_FAILED);
            LogUtil.LogError(InfoConstant.USERNAME_OR_PASS_ERROR);
            return false;
        }
    }

    /**
     * Provides the registration function of the user
     *
     * @param user saved the name and password
     * @return if user not exist, then catch exception and sign up successfully;
     * otherwise  throw an RuntimeException
     * @throws RuntimeException if signs up unsuccessfully
     */
    @Override
    public boolean signup(User user) {
        if (!(checkName(user.getName()) && checkPassword(user.getPassword()))) {
            return false;
        }
        try {
            userRepository.getUser(user.getName());//if it is null, username not exist, so goto the catch branch

        } catch (RuntimeException e) {
            userRepository.createUser(user);
            // loginStatus = true;
            String userSignupOkStr = MessageFormat.format(InfoConstant.USER_SIGNUP_SUCCESSFULLY, user.getName());
            logger.info(userSignupOkStr);
            return true;
        }
        //username already exist
        String userAlreadyExistStr = MessageFormat.format(InfoConstant.USER_ALREADY_EXIST, user.getName());
        logger.info(userAlreadyExistStr);
        LogUtil.LogError(userAlreadyExistStr);
        return false;
    }

    /**
     * Check whether the given name is valid
     * Any user name should start with starbb_, contain only characters, numbers and underscore
     * The user name should also be of a length between 8 and 49
     *
     * @param name the given name to check
     * @return whether the name is valid
     */
    @Override
    public boolean checkName(String name) {
        return name != null &&
                name.matches("^starbb_[a-zA-Z0-9_]{1,42}$");
    }

    /**
     * Check whether the given password is valid
     * Any password should only contain and must contain all the three types of characters below:
     * 1) English characters(upper case or lower case);
     * 2) Numbers;
     * 3) Underscore.
     * In addition, the passwords should be of a length between 8 and 99
     *
     * @param password the given password to check
     * @return whether the password is valid
     */
    @Override
    public boolean checkPassword(String password) {
        return password != null &&
                password.matches("^(?=.*[0-9].*)(?=.*[A-Za-z].*)(?=.*[_].*)[a-zA-Z0-9_]{8,99}$");
    }

    /**
     * * Check the login status, you can maintain this status in environment variable.
     *
     * @return if user has already login, return true, else return false.
     */
    @Override
    public boolean checkStatus() {
        if (loginStatus) {
            System.out.println(languageService.getValue(InfoConstant.USER_HAS_LOGED_IN));
            return true;
        }
        System.out.println(languageService.getValue(InfoConstant.PLEASE_LOGIN));
        return false;
    }
}
