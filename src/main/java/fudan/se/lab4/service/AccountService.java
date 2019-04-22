package fudan.se.lab4.service;

import fudan.se.lab4.entity.User;

/**
 * This class is in charge of the user account service logic. Any user log-in and register method
 * must be done via this class. Furthermore, this class also provides utility functions for
 * checking the log in status of the user. There are also restrictions on the user name and
 * the password of the user, hence this class provides methods of validating the user name and
 * the password.
 *
 * @author Wang, Chen
 */
public interface AccountService {
    /**
     * Provides the log in function of the user
     *
     * @param user The created user object with the input data
     * @return Whether logs in successfully
     */
    boolean login(User user);

    /**
     * Provides the registration function of the user
     *
     * @param user The created user object with the input data
     * @return Whether registers in successfully
     */
    boolean signup(User user);

    /**
     * Check the login status, you can maintain this status in environment variable.
     *
     * @return if user has already login, return true, else return false.
     */
    boolean checkStatus();

    /**
     * Check whether the given name is valid
     *
     * @param name the given name to check
     * @return whether the name is valid
     */
    boolean checkName(String name);

    /**
     * Check whether the given password is valid
     *
     * @param password the given password to check
     * @return whether the password is valid
     */
    boolean checkPassword(String password);
}