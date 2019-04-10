package fudan.se.lab4.entity;

import java.text.MessageFormat;

/**
 * The user object class holding the name and password of the user.
 * This object is created when a user logs in ,tries to log in or registers.
 */
public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Name: {0}, Password: {1}", this.getName(), this.getPassword());
    }
}
