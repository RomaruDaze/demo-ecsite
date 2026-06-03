-- 1. Create the 'users' table
CREATE TABLE users (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      zipcode VARCHAR(20),
                      prefecture VARCHAR(50),
                      municipalities VARCHAR(100),
                      address VARCHAR(255),
                      telephone VARCHAR(20)
);

-- 2. Insert mock data
INSERT INTO users (name, email, password, zipcode, prefecture, municipalities, address, telephone)
VALUES
    ('Tarou Tanaka', 'tanaka.tarou@example.com', '$2y$10$eImiTxAk4vmM854.CeIcaenN.4.A./m.1/Z.t.31a.u2z26d4837.', '100-0005', 'Tokyo', 'Chiyoda-ku', 'Marunouchi 1-chome', '03-1234-5678'),
    ('Hanako Sato', 'sato.hanako@example.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '530-0001', 'Osaka', 'Osaka-shi Kita-ku', 'Umeda 1-chome', '06-9876-5432'),
    ('John Doe', 'john.doe@example.com', '$2y$10$z7x8y9wbv...mockhashedpassword...', '902-0067', 'Okinawa', 'Naha-shi', 'Asato 1-chome', '098-111-2222'),
    ('Yuki Suzuki', 'suzuki.yuki@example.com', '$2y$10$v2b3c4dx...mockhashedpassword...', '060-0005', 'Hokkaido', 'Sapporo-shi Chuo-ku', 'Kita 5-jonishi', '011-333-4444');
('Ichiro Takahashi', 'takahashi.ichiro@example.com', '$2y$10$7x8y9z...mockhashedpassword1...', '450-0002', 'Aichi', 'Nagoya-shi Nakamura-ku', 'Meieki 1-chome', '052-555-6666'),
    ('Sakura Watanabe', 'watanabe.sakura@example.com', '$2y$10$a1b2c3...mockhashedpassword2...', '812-0012', 'Fukuoka', 'Fukuoka-shi Hakata-ku', 'Hakataekichuogai', '092-777-8888'),
    ('Kenji Ito', 'ito.kenji@example.com', '$2y$10$d4e5f6...mockhashedpassword3...', '980-8484', 'Miyagi', 'Sendai-shi Aoba-ku', 'Chuo 1-chome', '022-222-3333'),
    ('Jane Smith', 'jane.smith@example.com', '$2y$10$g7h8i9...mockhashedpassword4...', '231-0017', 'Kanagawa', 'Yokohama-shi Naka-ku', 'Minatominami 1-chome', '045-444-5555'),
    ('Hiroshi Nakamura', 'nakamura.hiroshi@example.com', '$2y$10$j0k1l2...mockhashedpassword5...', '600-8216', 'Kyoto', 'Kyoto-shi Shimogyo-ku', 'Higashishiokojicho', '075-888-9999'),
    ('Aoi Kobayashi', 'kobayashi.aoi@example.com', '$2y$10$m3n4o5...mockhashedpassword6...', '730-0011', 'Hiroshima', 'Hiroshima-shi Naka-ku', 'Motomachi', '082-999-0000');