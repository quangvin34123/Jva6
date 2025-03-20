package Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng giá trị
    @Column(name = "review_id")
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Liên kết với bảng Products
    private Products product;

    @Column(name = "rating", nullable = false)
    private Integer rating;  // Đánh giá từ 1 đến 5

    @Column(name = "comment", length = 500)
    private String comment;  // Bình luận của người dùng

    @Column(name = "review_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;  // Ngày đánh giá

    // Constructor mặc định
    public Reviews() {
    }

    // Constructor với tham số
    public Reviews(Products product, Integer rating, String comment, Date reviewDate) {
        this.product = product;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    // Getter và Setter
    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
