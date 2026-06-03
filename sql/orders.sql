-- Create orders table
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        total_price INT NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create order_items table to track what was bought in each order
CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             order_id INT NOT NULL,
                             item_id INT NOT NULL,
                             quantity INT NOT NULL,
                             price_at_purchase INT NOT NULL
);