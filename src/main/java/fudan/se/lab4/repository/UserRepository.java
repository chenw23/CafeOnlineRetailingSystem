package fudan.se.lab4.repository;

import fudan.se.lab4.entity.User;

public interface UserRepository {
    /**
     * persist user in data/user.csv
     *
     * @param user the user in data/user.csv
     */
    void createUser(User user);

    /**
     * get User by name in data/user.csv
     *
     * @param name the name in data/user.csv
     * @return user
     */
    User getUser(String name);
}
