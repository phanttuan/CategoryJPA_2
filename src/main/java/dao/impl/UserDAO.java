package dao.impl;

import config.JPAConfig;
import dao.IUserDAO;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDAO implements IUserDAO {

    @Override
    public User findById(int id) {
        EntityManager em = JPAConfig.em();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAConfig.em();
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            q.setParameter("username", username);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        EntityManager em = JPAConfig.em();
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
            q.setParameter("username", username);
            q.setParameter("password", password);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAConfig.em();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}