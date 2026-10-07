CREATE DATABASE ai_ecommerce;

USE ai_ecommerce;
CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);
SELECT * FROM customers;
SELECT COUNT(*) AS total_products
FROM products;
SELECT id, name, category, price, image
FROM products
LIMIT 10;
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price DECIMAL(10,2) NOT NULL,
    image VARCHAR(255)
);

CREATE TABLE cart (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,

    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE product_views (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    viewed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);



INSERT INTO products
(name, category, description, price, image)
VALUES

('Samsung Galaxy S25', 'Mobile', 'Latest Samsung smartphone with AMOLED display', 79999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('iPhone 16', 'Mobile', 'Apple smartphone with powerful performance', 89999, 'https://images.unsplash.com/photo-1592286927505-2fd2f4f9c7b7'),
('OnePlus 13', 'Mobile', 'Flagship Android smartphone', 69999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Google Pixel 9', 'Mobile', 'Google smartphone with advanced camera', 74999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Redmi Note 14', 'Mobile', 'Affordable smartphone with large display', 18999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Nothing Phone 3', 'Mobile', 'Modern smartphone with unique design', 44999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Vivo V40', 'Mobile', 'Stylish smartphone with excellent camera', 32999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Oppo Reno 13', 'Mobile', 'Premium smartphone with fast charging', 37999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Realme GT 7', 'Mobile', 'Performance-focused smartphone', 39999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
('Motorola Edge 60', 'Mobile', 'Slim smartphone with curved display', 29999, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),

('Dell Inspiron 15', 'Laptop', 'Everyday laptop for students and professionals', 54999, 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853'),
('HP Pavilion 15', 'Laptop', 'Powerful laptop for office and study', 59999, 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853'),
('Lenovo IdeaPad Slim 5', 'Laptop', 'Slim and lightweight laptop', 57999, 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853'),
('ASUS VivoBook 15', 'Laptop', 'Affordable performance laptop', 52999, 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853'),
('Acer Aspire 5', 'Laptop', 'Reliable laptop for daily use', 49999, 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853'),
('MacBook Air M4', 'Laptop', 'Apple laptop with M4 processor', 99999, 'https://images.unsplash.com/photo-1517336714739-489689fd1ca8'),
('MacBook Pro M4', 'Laptop', 'Professional Apple laptop', 149999, 'https://images.unsplash.com/photo-1517336714739-489689fd1ca8'),
('HP Victus Gaming', 'Laptop', 'Gaming laptop with dedicated graphics', 79999, 'https://images.unsplash.com/photo-1593642702821-c8da6771f0c6'),
('ASUS ROG Strix', 'Laptop', 'High performance gaming laptop', 129999, 'https://images.unsplash.com/photo-1593642702821-c8da6771f0c6'),
('Lenovo Legion 5', 'Laptop', 'Gaming laptop with powerful processor', 94999, 'https://images.unsplash.com/photo-1593642702821-c8da6771f0c6'),

('Logitech Wireless Mouse', 'Computer Accessories', 'Ergonomic wireless mouse', 999, 'https://images.unsplash.com/photo-1527814050087-3793815479db'),
('HP Wireless Mouse', 'Computer Accessories', 'Comfortable wireless mouse', 799, 'https://images.unsplash.com/photo-1527814050087-3793815479db'),
('Dell Bluetooth Mouse', 'Computer Accessories', 'Bluetooth mouse for laptops', 1199, 'https://images.unsplash.com/photo-1527814050087-3793815479db'),
('Logitech Gaming Mouse', 'Computer Accessories', 'High precision gaming mouse', 2499, 'https://images.unsplash.com/photo-1527814050087-3793815479db'),
('Mechanical RGB Keyboard', 'Computer Accessories', 'RGB mechanical gaming keyboard', 2499, 'https://images.unsplash.com/photo-1587829741301-dc798b83add3'),
('Logitech Keyboard', 'Computer Accessories', 'Wireless keyboard for office use', 1799, 'https://images.unsplash.com/photo-1587829741301-dc798b83add3'),
('Gaming Keyboard Pro', 'Computer Accessories', 'Mechanical gaming keyboard', 3499, 'https://images.unsplash.com/photo-1587829741301-dc798b83add3'),
('USB Hub 4 Port', 'Computer Accessories', 'Multi-port USB hub', 799, 'https://images.unsplash.com/photo-1625842268584-8f3296236761'),
('USB-C Hub', 'Computer Accessories', 'Premium USB-C multi-port hub', 1499, 'https://images.unsplash.com/photo-1625842268584-8f3296236761'),
('Laptop Cooling Pad', 'Computer Accessories', 'Cooling pad for gaming laptops', 1299, 'https://images.unsplash.com/photo-1593642702821-c8da6771f0c6'),

('Sony WH-1000XM6', 'Audio', 'Premium noise cancelling headphones', 29999, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'),
('Boat Rockerz 450', 'Audio', 'Affordable wireless headphones', 1499, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'),
('JBL Tune 770NC', 'Audio', 'Wireless noise cancellation headphones', 5999, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'),
('Apple AirPods Pro', 'Audio', 'Premium wireless earbuds', 24999, 'https://images.unsplash.com/photo-1600294037681-c80b4cb5b434'),
('Samsung Galaxy Buds', 'Audio', 'Wireless earbuds for Android users', 8999, 'https://images.unsplash.com/photo-1600294037681-c80b4cb5b434'),
('Boat Airdopes 181', 'Audio', 'Affordable Bluetooth earbuds', 1299, 'https://images.unsplash.com/photo-1600294037681-c80b4cb5b434'),
('OnePlus Buds 4', 'Audio', 'Wireless earbuds with clear sound', 4999, 'https://images.unsplash.com/photo-1600294037681-c80b4cb5b434'),
('JBL Bluetooth Speaker', 'Audio', 'Portable Bluetooth speaker', 3999, 'https://images.unsplash.com/photo-1608043152269-423dbba4e7e1'),
('Sony Bluetooth Speaker', 'Audio', 'Premium portable speaker', 7999, 'https://images.unsplash.com/photo-1608043152269-423dbba4e7e1'),
('Boat Party Speaker', 'Audio', 'Powerful party Bluetooth speaker', 5999, 'https://images.unsplash.com/photo-1608043152269-423dbba4e7e1'),

('Apple Watch Series 11', 'Wearable', 'Smart watch with health features', 46999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Samsung Galaxy Watch 7', 'Wearable', 'Premium Android smartwatch', 29999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Noise ColorFit Pro', 'Wearable', 'Affordable fitness smartwatch', 2999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Boat Storm Call', 'Wearable', 'Fitness smartwatch with calling', 1999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Amazfit Active', 'Wearable', 'Fitness tracking smartwatch', 9999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Fire-Boltt Phoenix', 'Wearable', 'Budget smartwatch with calling', 1499, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Garmin Forerunner', 'Wearable', 'Advanced running smartwatch', 29999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Fitbit Charge', 'Wearable', 'Fitness activity tracker', 9999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Samsung Fit 3', 'Wearable', 'Lightweight fitness band', 2999, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),
('Xiaomi Smart Band', 'Wearable', 'Affordable fitness band', 2499, 'https://images.unsplash.com/photo-1523275335684-37898b6baf30'),

('HP Laptop Bag', 'Bags', 'Water resistant laptop backpack', 1999, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('American Tourister Backpack', 'Bags', 'Travel and college backpack', 2499, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('Wildcraft Laptop Bag', 'Bags', 'Durable laptop backpack', 2199, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('Skybags Backpack', 'Bags', 'Stylish everyday backpack', 1799, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('Safari Travel Backpack', 'Bags', 'Large travel backpack', 1999, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('VIP Laptop Bag', 'Bags', 'Professional laptop bag', 2299, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('College Backpack', 'Bags', 'Lightweight college backpack', 999, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('Office Backpack', 'Bags', 'Professional office backpack', 1599, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('Travel Laptop Bag', 'Bags', 'Travel friendly laptop backpack', 2999, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),
('Premium Leather Bag', 'Bags', 'Premium leather laptop bag', 4999, 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'),

('Nike Air Max', 'Footwear', 'Comfortable running shoes', 8999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Adidas Ultraboost', 'Footwear', 'Premium running shoes', 11999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Puma Running Shoes', 'Footwear', 'Lightweight sports shoes', 4999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Nike Revolution', 'Footwear', 'Affordable running shoes', 3999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Adidas Casual Shoes', 'Footwear', 'Stylish casual sneakers', 4999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Puma Sneakers', 'Footwear', 'Modern lifestyle sneakers', 4499, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Reebok Sports Shoes', 'Footwear', 'Comfortable sports footwear', 3999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Skechers Walking Shoes', 'Footwear', 'Comfortable walking shoes', 5999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Campus Running Shoes', 'Footwear', 'Budget running shoes', 1999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
('Woodland Shoes', 'Footwear', 'Durable outdoor shoes', 6999, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),

('Levis Denim Jacket', 'Fashion', 'Classic denim jacket', 3999, 'https://images.unsplash.com/photo-1551028719-00167b16eac5'),
('Nike Sports T-Shirt', 'Fashion', 'Comfortable sports t-shirt', 1999, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),
('Adidas T-Shirt', 'Fashion', 'Casual cotton t-shirt', 1799, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),
('Puma Hoodie', 'Fashion', 'Warm casual hoodie', 2999, 'https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3'),
('Levis Jeans', 'Fashion', 'Classic slim fit jeans', 3499, 'https://images.unsplash.com/photo-1542272604-787c3835535d'),
('Allen Solly Shirt', 'Fashion', 'Formal cotton shirt', 2499, 'https://images.unsplash.com/photo-1603252110481-7ba873bf42ab'),
('Peter England Shirt', 'Fashion', 'Formal office shirt', 1999, 'https://images.unsplash.com/photo-1603252110481-7ba873bf42ab'),
('Roadster Jacket', 'Fashion', 'Casual winter jacket', 2999, 'https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3'),
('Hoodie Sweatshirt', 'Fashion', 'Comfortable cotton sweatshirt', 1999, 'https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3'),
('Casual Polo Shirt', 'Fashion', 'Classic polo shirt', 1499, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab'),

('Mi Power Bank 10000mAh', 'Mobile Accessories', 'Fast charging power bank', 1299, 'https://images.unsplash.com/photo-1609592424935-5f4e4b9a4f4c'),
('Samsung Power Bank', 'Mobile Accessories', 'Fast charging Samsung power bank', 1999, 'https://images.unsplash.com/photo-1609592424935-5f4e4b9a4f4c'),
('OnePlus Charger', 'Mobile Accessories', 'Fast charging mobile adapter', 1499, 'https://images.unsplash.com/photo-1583863788434-e58a36330cf0'),
('USB-C Fast Charger', 'Mobile Accessories', 'Universal fast charging adapter', 999, 'https://images.unsplash.com/photo-1583863788434-e58a36330cf0'),
('Lightning Cable', 'Mobile Accessories', 'Durable charging cable', 699, 'https://images.unsplash.com/photo-1583863788434-e58a36330cf0'),
('USB-C Cable', 'Mobile Accessories', 'Fast charging USB-C cable', 499, 'https://images.unsplash.com/photo-1583863788434-e58a36330cf0'),
('Mobile Tripod', 'Mobile Accessories', 'Adjustable smartphone tripod', 899, 'https://images.unsplash.com/photo-1606983340126-99ab4feaa64a'),
('Phone Stand', 'Mobile Accessories', 'Desktop mobile phone stand', 399, 'https://images.unsplash.com/photo-1606983340126-99ab4feaa64a'),
('MagSafe Charger', 'Mobile Accessories', 'Wireless magnetic charger', 2999, 'https://images.unsplash.com/photo-1583863788434-e58a36330cf0'),
('Wireless Charging Pad', 'Mobile Accessories', 'Fast wireless charging pad', 1599, 'https://images.unsplash.com/photo-1583863788434-e58a36330cf0');