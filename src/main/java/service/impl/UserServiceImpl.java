package service.impl;

import dao.impl.UserDAO;
import entity.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAO();

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
