package com.example.duanjava6.Controller;

import com.example.duanjava6.Entity.CartItem;
import com.example.duanjava6.Dao.CartItemDAO; // Giả định bạn có DAO để tương tác với database
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartItemDAO cartItemDAO;

    // Hiển thị trang giỏ hàng
    @GetMapping
    public String showCart(Model model) {
        List<CartItem> cartItems = cartItemDAO.findAll();
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart"; // Trả về cart.html
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public String addToCart(@RequestParam("name") String name,
                            @RequestParam("imageUrl") String imageUrl,
                            @RequestParam("price") double price,
                            @RequestParam("quantity") int quantity) {
        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
        CartItem existingItem = cartItemDAO.findByName(name);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemDAO.save(existingItem);
        } else {
            CartItem newItem = new CartItem(name, imageUrl, price, quantity);
            cartItemDAO.save(newItem);
        }
        return "redirect:/cart";
    }

    // Cập nhật số lượng sản phẩm
    @PostMapping("/update")
    @ResponseBody
    public String updateQuantity(@RequestBody UpdateRequest request) {
        CartItem item = cartItemDAO.findByName(request.getName());
        if (item != null) {
            if (request.getQuantity() <= 0) {
                cartItemDAO.delete(item);
            } else {
                item.setQuantity(request.getQuantity());
                cartItemDAO.save(item);
            }
            return "success";
        }
        return "error: Product not found";
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @PostMapping("/remove")
    @ResponseBody
    public String removeItem(@RequestBody RemoveRequest request) {
        CartItem item = cartItemDAO.findByName(request.getName());
        if (item != null) {
            cartItemDAO.delete(item);
            return "success";
        }
        return "error: Product not found";
    }

    // Xử lý thanh toán (chỉ các sản phẩm được chọn)
    @PostMapping("/checkout")
    @ResponseBody
    public String checkout(@RequestBody CheckoutRequest request) {
        List<String> itemsToCheckout = request.getItems();
        if (itemsToCheckout == null || itemsToCheckout.isEmpty()) {
            return "error: No items selected";
        }

        // Xóa các sản phẩm được chọn sau khi thanh toán
        for (String name : itemsToCheckout) {
            CartItem item = cartItemDAO.findByName(name);
            if (item != null) {
                cartItemDAO.delete(item);
            }
        }

        // Ở đây bạn có thể thêm logic để tạo Order và Payment
        return "success";
    }

    // Các lớp DTO để nhận dữ liệu từ JSON
    public static class UpdateRequest {
        private String name;
        private int quantity;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    public static class RemoveRequest {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class CheckoutRequest {
        private List<String> items;

        public List<String> getItems() { return items; }
        public void setItems(List<String> items) { this.items = items; }
    }
}