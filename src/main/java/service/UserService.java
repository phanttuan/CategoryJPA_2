package service;

import entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
}
