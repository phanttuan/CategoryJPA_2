package dao.impl;

import java.util.List;

import config.JPAConfig;
import dao.ICategoryDAO;
import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoryDAO implements ICategoryDAO {

	@Override
	public Category findById(int id) {
		EntityManager em = JPAConfig.em();
		Category category = null;
		try {
			category = em.find(Category.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager em = JPAConfig.em();
		List<Category> categories = null;
		try {
			categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return categories;
	}

	public List<Category> findByUser(int userId) {
		EntityManager em = JPAConfig.em();
		List<Category> categories = null;
		try {
			TypedQuery<Category> q = em.createQuery("SELECT c FROM Category c WHERE c.user.id = :uid", Category.class);
			q.setParameter("uid", userId);
			categories = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return categories;
	}

	@Override
	public void update(Category category) {
		EntityManager em = JPAConfig.em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(category);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = JPAConfig.em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
	}

	@Override
	public void create(Category category) {
		EntityManager em = JPAConfig.em();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(category);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

}