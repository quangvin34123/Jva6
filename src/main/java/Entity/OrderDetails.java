package Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng giá trị
    @Column(name = "order_details_id")
    private Integer orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // Liên kết với bảng Orders
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Liên kết với bảng Products
    private Products product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    // Constructor mặc định
    public OrderDetails() {
    }

    // Constructor với tham số
    public OrderDetails(Orders order, Products product, Integer quantity, BigDecimal price, BigDecimal total) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    // Getter và Setter
    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
