package fudan.se.lab4.repository.impl;

import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.util.FileUtil;

import java.text.MessageFormat;

public class UserRepositoryImpl {

    public void createUser(User user) {
        FileUtil.write(objectToStringArray(user), FileConstant.USER_CSV);
    }


    public User getUser(String name) {
        return stringArrayToObject(FileUtil.readByName(name, FileConstant.USER_CSV));
    }

    private String[] objectToStringArray(User user) {
        // if user already exists, throw exception
        if (FileUtil.exist(user.getName(), FileConstant.USER_CSV)) {
            throw new RuntimeException(MessageFormat.format(InfoConstant.ENTITY_EXIST, "User",
                    user.getName()));
        }
        String[] array = new String[2];
        array[0] = user.getName();
        array[1] = user.getPassword();
        return array;
    }

    private User stringArrayToObject(String[] array) {
        User user = new User();
        user.setName(array[0]);
        user.setPassword(array[1]);
        return user;
    }
}
