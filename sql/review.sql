CREATE TABLE reviews (
                         id SERIAL PRIMARY KEY,
                         item_id INT NOT NULL,
                         user_id INT NOT NULL,
                         rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
                         comment TEXT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE review_comments (
                                 id SERIAL PRIMARY KEY,
                                 review_id INT NOT NULL,
                                 user_id INT NOT NULL,
                                 comment TEXT NOT NULL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- A single table to handle likes for both reviews and comments
CREATE TABLE review_likes (
                              id SERIAL PRIMARY KEY,
                              user_id INT NOT NULL,
                              review_id INT,
                              comment_id INT
);