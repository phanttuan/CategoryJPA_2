package dao;

import entity.User;

public interface IUserDAO {
    User findById(int id);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    void update(User user);
}