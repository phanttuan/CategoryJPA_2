package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    // roleid: 1-user, 2-manager, 3-admin
    @Column(name = "roleid")
    private int roleid;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "images", length = 255)
    private String images;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    public User() {
    }

    public User(int id, String username, String password, int roleid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleid = roleid;
    }

    public User(int id, String username, String password, int roleid, String fullname, String phone, String images) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleid = roleid;
        this.fullname = fullname;
        this.phone = phone;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category c) {
        categories.add(c);
        c.setUser(this);
    }

    public void removeCategory(Category c) {
        categories.remove(c);
        c.setUser(null);
    }

    public String getRole() {
        switch (roleid) {
            case 3: return "ADMIN";
            case 2: return "MANAGER";
            default: return "USER";
        }
    }

    public void setRole(String role) {
        if (role == null) {
            this.roleid = 1;
        } else if (role.equalsIgnoreCase("ADMIN")) {
            this.roleid = 3;
        } else if (role.equalsIgnoreCase("MANAGER")) {
            this.roleid = 2;
        } else {
            this.roleid = 1;
        }
    }
}