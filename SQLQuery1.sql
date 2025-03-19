-- Tạo cơ sở dữ liệu
CREATE DATABASE FashionStore;

-- Sử dụng cơ sở dữ liệu
USE FashionStore;

-- Tạo bảng Users (Khách hàng)
CREATE TABLE Users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    address TEXT,
    role VARCHAR(10) CHECK (role IN ('admin', 'user')) DEFAULT 'user',  -- 'role' được sửa
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng Categories (Danh mục sản phẩm)
CREATE TABLE Categories (
    category_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Tạo bảng Products (Sản phẩm)
CREATE TABLE Products (
    product_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    category_id INT,
    image_url VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);

-- Tạo bảng Orders (Đơn hàng)
CREATE TABLE Orders (
    order_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    user_id INT,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_status VARCHAR(20) CHECK (order_status IN ('Pending', 'Shipped', 'Delivered', 'Cancelled')) DEFAULT 'Pending',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Tạo bảng OrderDetails (Chi tiết đơn hàng)
CREATE TABLE OrderDetails (
    order_detail_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

-- Tạo bảng Payments (Thanh toán)
CREATE TABLE Payments (
    payment_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    order_id INT,
    payment_method VARCHAR(50) CHECK (payment_method IN ('Credit Card', 'PayPal', 'Cash On Delivery')) NOT NULL,
    payment_status VARCHAR(20) CHECK (payment_status IN ('Pending', 'Completed', 'Failed')) DEFAULT 'Pending',
    payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

-- Tạo bảng Reviews (Đánh giá sản phẩm)
CREATE TABLE Reviews (
    review_id INT IDENTITY(1,1) PRIMARY KEY,  -- Thay 'AUTO_INCREMENT' bằng 'IDENTITY'
    product_id INT,
    user_id INT,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Thêm một admin
INSERT INTO Users (first_name, last_name, email, password, role, phone, address)
VALUES ('Admin', 'User', 'admin@example.com', 'hashed_password', 'admin', '1234567890', '123 Admin Street, City');

-- Thêm một user
INSERT INTO Users (first_name, last_name, email, password, role, phone, address)
VALUES ('Jane', 'Doe', 'jane.doe@example.com', 'hashed_password', 'user', '0987654321', '456 User Lane, City');

-- Thêm một user khác
INSERT INTO Users (first_name, last_name, email, password, role, phone, address)
VALUES ('John', 'Smith', 'john.smith@example.com', 'hashed_password', 'user', '1112233445', '789 Smith Ave, City');

--cATEGORIES
-- Thêm các danh mục sản phẩm
INSERT INTO Categories (name, description)
VALUES ('Clothing', 'Quần áo thời trang cho nam và nữ');

INSERT INTO Categories (name, description)
VALUES ('Shoes', 'Giày dép các loại');

INSERT INTO Categories (name, description)
VALUES ('Accessories', 'Phụ kiện thời trang');

--Products
-- Thêm sản phẩm vào danh mục 'Clothing'
INSERT INTO Products (name, description, price, stock, category_id, image_url)
VALUES ('Áo Thun Nam', 'Áo thun nam cổ tròn, màu trắng', 200000, 50, 1, 'image_1.jpg');

-- Thêm sản phẩm vào danh mục 'Shoes'
INSERT INTO Products (name, description, price, stock, category_id, image_url)
VALUES ('Giày Sneaker', 'Giày sneaker nam màu đen', 600000, 30, 2, 'image_2.jpg');

-- Thêm sản phẩm vào danh mục 'Accessories'
INSERT INTO Products (name, description, price, stock, category_id, image_url)
VALUES ('Mũ Lưỡi Trai', 'Mũ lưỡi trai cho nam', 150000, 100, 3, 'image_3.jpg');

--Oders
-- Thêm đơn hàng cho user
INSERT INTO Orders (user_id, total_amount, order_status)
VALUES (2, 800000, 'Pending');

-- Thêm đơn hàng cho user khác
INSERT INTO Orders (user_id, total_amount, order_status)
VALUES (3, 450000, 'Shipped');

--OrderDetails
-- Chi tiết đơn hàng 1
INSERT INTO OrderDetails (order_id, product_id, quantity, price)
VALUES (1, 1, 2, 200000);  -- 2 chiếc áo thun Nam

-- Chi tiết đơn hàng 2
INSERT INTO OrderDetails (order_id, product_id, quantity, price)
VALUES (2, 2, 1, 600000);  -- 1 đôi giày Sneaker

--Payments
-- Thêm thông tin thanh toán cho đơn hàng
INSERT INTO Payments (order_id, payment_method, payment_status)
VALUES (1, 'Credit Card', 'Completed');

-- Thêm thông tin thanh toán cho đơn hàng 2
INSERT INTO Payments (order_id, payment_method, payment_status)
VALUES (2, 'Cash On Delivery', 'Pending');
--Reviews
-- Thêm đánh giá cho sản phẩm 'Áo Thun Nam'
INSERT INTO Reviews (product_id, user_id, rating, comment)
VALUES (1, 2, 4, 'Áo thun rất thoải mái, chất liệu tốt.');

-- Thêm đánh giá cho sản phẩm 'Giày Sneaker'
INSERT INTO Reviews (product_id, user_id, rating, comment)
VALUES (2, 3, 5, 'Giày rất đẹp và thoải mái, sẽ mua thêm.');
