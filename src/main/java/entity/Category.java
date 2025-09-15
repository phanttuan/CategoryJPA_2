package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	 
	@Column(name = "categoryname",columnDefinition = "nvarchar(50)")
	private String categoryname;
	
	@Column(name = "images")
	private String images;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

	public Category(int id, String categoryname, String images) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.images = images;
	}
	public Category(int id, String categoryname, String images, User user) {
		this.id = id;
		this.categoryname = categoryname;
		this.images = images;
		this.user = user;
	}
	public Category() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}