package dao.impl;

import dao.IVideoDAO;
import entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class VideoDAO implements IVideoDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dataSource");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void insert(Video video) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(video);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Video video = em.find(Video.class, id);
            if (video != null) {
                em.remove(video);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Video findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> searchByTitle(String keyword) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Video> query = em.createQuery(
                "SELECT v FROM Video v WHERE LOWER(v.title) LIKE :keyword", Video.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}