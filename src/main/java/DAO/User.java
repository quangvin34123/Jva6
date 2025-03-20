package DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User extends JpaRepository<Entity.User, Integer> {
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh ở đây nếu cần
    Entity.User findByEmail(String email);  // Tìm kiếm người dùng theo email
    Entity.User findByPhone(String phone);  // Tìm kiếm người dùng theo số điện thoại
}