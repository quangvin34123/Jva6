package com.example.duanjava6.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.duanjava6.Dao.PaymentDAO;
import com.example.duanjava6.Entity.Payment;
import com.example.duanjava6.Entity.Payment.PaymentMethod;
import com.example.duanjava6.Entity.Payment.PaymentStatus;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentDAO paymentDAO;

    // Hiển thị trang danh sách tất cả các thanh toán
    @GetMapping
    public String getAllPayments(Model model) {
        List<Payment> payments = paymentDAO.findAll();
        model.addAttribute("payments", payments);
        return "S"; // Trả về template danh sách thanh toán
    }

    // Hiển thị form tạo thanh toán mới
    @GetMapping("/new")
    public String showPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "payment/form"; // Trả về template form tạo thanh toán
    }

    // Xử lý tạo thanh toán mới
    @PostMapping("/create")
    public String createPayment(@ModelAttribute Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.Pending); // Mặc định là Pending khi tạo mới
        paymentDAO.save(payment);
        return "redirect:/payments"; // Chuyển hướng về danh sách thanh toán
    }

    // Xem chi tiết thanh toán theo ID
    @GetMapping("/{id}")
    public String getPaymentById(@PathVariable("id") Integer id, Model model) {
        Optional<Payment> payment = paymentDAO.findById(id);
        if (payment.isPresent()) {
            model.addAttribute("payment", payment.get());
            return "payment/detail"; // Trả về template chi tiết thanh toán
        }
        return "redirect:/payments"; // Nếu không tìm thấy, quay lại danh sách
    }

    // API để lấy danh sách thanh toán theo orderId
    @GetMapping("/order/{orderId}")
    @ResponseBody
    public ResponseEntity<List<Payment>> getPaymentsByOrderId(@PathVariable("orderId") Integer orderId) {
        List<Payment> payments = paymentDAO.findByOrderOrderId(orderId);
        return ResponseEntity.ok(payments);
    }

    // API để lấy danh sách thanh toán theo trạng thái
    @GetMapping("/status/{status}")
    @ResponseBody
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable("status") PaymentStatus status) {
        List<Payment> payments = paymentDAO.findByPaymentStatus(status);
        return ResponseEntity.ok(payments);
    }

    // Cập nhật trạng thái thanh toán
    @PostMapping("/update-status/{id}")
    public String updatePaymentStatus(@PathVariable("id") Integer id, 
                                    @RequestParam("status") PaymentStatus status) {
        Optional<Payment> paymentOpt = paymentDAO.findById(id);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            payment.setPaymentStatus(status);
            paymentDAO.save(payment);
        }
        return "redirect:/payments";
    }

    // Xóa thanh toán
    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable("id") Integer id) {
        paymentDAO.deleteById(id);
        return "redirect:/payments";
    }
}