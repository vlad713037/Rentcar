package by.viraz84.myrentcar.service;

import by.viraz84.myrentcar.dao.UsersDao;
import by.viraz84.myrentcar.exception.UserNotFoundForLoginException;
import by.viraz84.myrentcar.model.Users;
import by.viraz84.myrentcar.model.enam.UserRole;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static UserService instance;

    private UserService() {

        instance = this;
    }

    public static UserService getInstance() {
        if (instance == null) {
            UserService.instance = new UserService();
        }
        return instance;
    }

    public Users registerUser(String login, String password, String firstName, String lastName) {
        String hashPassw = hashPassword(password);

        Users user = new Users();

        user.setLogin(login);
        user.setPass(hashPassw);
        user.setUserRole(UserRole.USER);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        Users save = UsersDao.getInstance().save(user);

       return save;
    }


    public Users login(String login, String password) {
        Users findByLogin = UsersDao.getInstance().findByLogin(login);
        if (findByLogin != null) {
            String enterPass = hashPassword(password);
            String hashInDB = findByLogin.getPass();

            if (hashInDB.equals(enterPass)) {
                return findByLogin;
            }
        }
        throw new UserNotFoundForLoginException(login);

    }
    public  boolean userIsExist(String login, String password) {
        boolean result = false;
        Users findByLogin = UsersDao.getInstance().findByLogin(login);
        if (findByLogin != null) {
            String enterPass = hashPassword(password);
            String hashInDB = findByLogin.getPass();
            if (hashInDB.equals(enterPass)) {
                result = true;
            }
        }
        return result;

    }

    private String hashPassword(String password) {
        return DigestUtils.md5Hex(password).toUpperCase();
    }


}
