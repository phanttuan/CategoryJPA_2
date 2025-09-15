package dao;

import java.util.List;

import entity.Category;

public interface ICategoryDAO {
	Category findById(int id);
	List<Category> findAll();
	List<Category> findByUser(int userId);
	void update(Category category);
	void delete(int id);
	void create(Category category);
}