package DAO;

import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categories extends JpaRepository<Category, Integer> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
    Category findByName(String name);  // Tìm danh mục theo tên
}
