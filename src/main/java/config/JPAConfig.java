package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static EntityManagerFactory emf;
    
    static {
        try {
            System.out.println("Initializing JPA EntityManagerFactory...");
            emf = Persistence.createEntityManagerFactory("dataSource");
            System.out.println("JPA EntityManagerFactory initialized successfully");
        } catch (Exception e) {
            System.err.println("Failed to initialize JPA EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager em() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}