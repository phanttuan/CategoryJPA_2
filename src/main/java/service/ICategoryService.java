package service;

import java.util.List;

import entity.Category;

public interface ICategoryService {
	void insert(Category category);
	List<Category> findAll();
	List<Category> findByUser(int userId);
	void update(Category category);
	void delete(int id);
	Category findById(int id);
}