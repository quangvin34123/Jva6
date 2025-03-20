package Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng giá trị (IDENTITY trong SQL Server)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Quan hệ nhiều-nhiều với Product (1 Category có thể có nhiều Product, 1 Product có thể thuộc nhiều Category)
    @ManyToMany
    @JoinTable(
            name = "product_categories",  // Bảng kết nối giữa Product và Category
            joinColumns = @JoinColumn(name = "category_id"),  // Khóa ngoại đến category_id
            inverseJoinColumns = @JoinColumn(name = "product_id")  // Khóa ngoại đến product_id
    )
    private Set<Products> products = new HashSet<>();  // Dùng Set để lưu các Product liên kết

    // Constructor mặc định
    public Categories() {
    }

    // Constructor với tham số
    public Categories(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter và Setter
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }
}
