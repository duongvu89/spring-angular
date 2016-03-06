package demo.service;

import demo.dao.UserDao;
import demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nguyen Duong Vu on 21-Feb-16.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void delete(String id) {
        userDao.delete(id);
    }
}
