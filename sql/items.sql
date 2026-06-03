-- 1. Create the 'items' table (Kept for reference/structure)
CREATE TABLE IF NOT EXISTS items (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    description TEXT,
    price INT NOT NULL,
    image_url VARCHAR(500),
    deleted BOOLEAN DEFAULT FALSE
    );

-- 2. Insert 100 items with Yahoo Shopping live image URLs
INSERT INTO items (name, description, price, image_url, deleted) VALUES
-- Electronics (1-10)
('Smartphone X1', 'Latest 5G smartphone with 128GB storage and OLED display.', 85000, 'https://item-shopping.c.yimg.jp/i/j/wisers1_mst-fcnrakuf53e?resolution=2x', false),
('Wireless Earbuds Pro', 'Noise-cancelling true wireless earbuds with 24h battery life.', 15000, 'https://item-shopping.c.yimg.jp/i/j/phatee_a006-260?resolution=2x', false),
('4K Ultra HD TV', '55-inch Smart TV with HDR and built-in streaming apps.', 65000, 'https://item-shopping.c.yimg.jp/i/j/hipregio-yh_4533115066891?resolution=2x', false),
('Gaming Laptop 15"', 'High-performance laptop with RTX 3060 and 16GB RAM.', 145000, 'https://item-shopping.c.yimg.jp/i/j/ecoren-ys_81-49-246?resolution=2x', false),
('Smartwatch Series 5', 'Fitness tracker, heart rate monitor, and waterproof design.', 22000, 'https://item-shopping.c.yimg.jp/i/j/annasui_iw-bs-cp-bb8-bk2?resolution=2x', false),
('Bluetooth Speaker', 'Portable waterproof speaker with deep bass.', 4500, 'https://item-shopping.c.yimg.jp/i/j/mii-st_yx-tg?resolution=2x', false),
('Mechanical Keyboard', 'RGB backlit mechanical keyboard with tactile switches.', 8000, 'https://item-shopping.c.yimg.jp/i/j/janpara_108177134-222?resolution=2x', false),
('Wireless Mouse', 'Ergonomic wireless mouse with precision tracking.', 3500, 'https://item-shopping.c.yimg.jp/i/j/logicool_m185cga?resolution=2x', false),
('Tablet Pro 11', '11-inch tablet perfect for drawing and productivity.', 75000, 'https://item-shopping.c.yimg.jp/i/j/ecoren-ys_81-12-089?resolution=2x', false),
('Power Bank 20000mAh', 'Fast-charging portable battery pack.', 3000, 'https://item-shopping.c.yimg.jp/i/j/alicemall_5dank5019-w0?resolution=2x', false),

-- Apparel & Clothing (11-20)
('Classic White T-Shirt', '100% cotton basic white tee for everyday wear.', 1500, 'https://item-shopping.c.yimg.jp/i/j/europort_cameo5a-wh-oto?resolution=2x', false),
('Slim Fit Jeans', 'Comfortable stretch denim in dark blue.', 4500, 'https://item-shopping.c.yimg.jp/i/j/size4458_428708?resolution=2x', false),
('Winter Parka Jacket', 'Heavy-duty insulated jacket for extreme cold.', 12000, 'https://item-shopping.c.yimg.jp/i/j/redwood_shu-rvpk-blu?resolution=2x', false),
('Running Sneakers', 'Lightweight breathable running shoes.', 6500, 'https://item-shopping.c.yimg.jp/i/j/teeolive_acrrifmcsn?resolution=2x', false),
('Leather Belt', 'Genuine leather belt with classic buckle.', 2500, 'https://item-shopping.c.yimg.jp/i/j/nakota_sr-ra2303-008cx?resolution=2x', false),
('Cotton Hoodie', 'Warm and cozy pullover hoodie with front pocket.', 3500, 'https://item-shopping.c.yimg.jp/i/j/blueism-y_ofs-os23-1ds-005?resolution=2x', false),
('Formal Dress Shirt', 'Wrinkle-free light blue dress shirt.', 3000, 'https://item-shopping.c.yimg.jp/i/j/rococo_14173?resolution=2x', false),
('Wool Scarf', 'Soft and warm scarf for the winter season.', 1800, 'https://item-shopping.c.yimg.jp/i/j/homeroortega_brsn005-black?resolution=2x', false),
('Sports Shorts', 'Quick-dry athletic shorts with zipper pockets.', 2200, 'https://item-shopping.c.yimg.jp/i/j/futsalshoproda_w1261151?resolution=2x', false),
('Ankle Socks (5-Pack)', 'Breathable cotton ankle socks.', 1000, 'https://item-shopping.c.yimg.jp/i/j/54tide_gb-ingles5psock?resolution=2x', false),

-- Home & Kitchen (21-30)
('Espresso Machine', 'Compact espresso maker with milk frother.', 18000, 'https://item-shopping.c.yimg.jp/i/j/gioncard_131-gc-cp020?resolution=2x', false),
('Non-Stick Frying Pan', '10-inch skillet with scratch-resistant coating.', 3000, 'https://item-shopping.c.yimg.jp/i/j/bestshop-d_hfayb0cm9n9rgpk?resolution=2x', false),
('Robot Vacuum', 'Smart vacuum cleaner with app control and mapping.', 25000, 'https://item-shopping.c.yimg.jp/i/j/ankerdirect_t2292?resolution=2x', false),
('Air Purifier', 'HEPA filter air purifier for rooms up to 500 sq ft.', 14000, 'https://item-shopping.c.yimg.jp/i/j/sneak_cad-mp-c30?resolution=2x', false),
('Electric Kettle', '1.7L stainless steel fast-boil kettle.', 2800, 'https://item-shopping.c.yimg.jp/i/j/binotoki-y_100002432-kura?resolution=2x', false),
('Chef Knife Set', 'Professional 5-piece stainless steel knife set.', 8500, 'https://item-shopping.c.yimg.jp/i/j/gaju_ed820391?resolution=2x', false),
('Microwave Oven', '700W compact microwave with 10 power levels.', 7500, 'https://item-shopping.c.yimg.jp/i/j/zakkashopcom_186186-14781?resolution=2x', false),
('Digital Kitchen Scale', 'High-precision food scale with LCD display.', 1500, 'https://item-shopping.c.yimg.jp/i/j/arqs_ym-scale0101?resolution=2x', false),
('Cotton Bath Towels', 'Set of 4 luxury plush bath towels.', 4000, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0dcwcgypz-n?resolution=2x', false),
('Ceramic Dinner Plates', 'Set of 6 modern minimalist dinner plates.', 3500, 'https://item-shopping.c.yimg.jp/i/j/allinone-d_hfayb0cxtpf7ngk?resolution=2x', false),

-- Sports & Outdoors (31-40)
('Yoga Mat', 'Eco-friendly non-slip exercise mat with carrying strap.', 2500, 'https://item-shopping.c.yimg.jp/i/j/manduka_401105120?resolution=2x', false),
('Adjustable Dumbbells', 'Pair of dumbbells adjustable up to 20kg each.', 15000, 'https://item-shopping.c.yimg.jp/i/j/bestshop-d_hfayb0bjbdg6wrk?resolution=2x', false),
('Camping Tent', 'Waterproof 4-person family camping tent.', 12000, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0dbn922gw-n?resolution=2x', false),
('Mountain Bike', '21-speed adult mountain bike with dual suspension.', 35000, 'https://item-shopping.c.yimg.jp/i/j/ennkei_jinghma-r7?resolution=2x', false),
('Sleeping Bag', 'Ultralight cold-weather sleeping bag.', 4500, 'https://item-shopping.c.yimg.jp/i/j/dabada_sleeping-bag-5?resolution=2x', false),
('Resistance Bands', 'Set of 5 resistance bands for home workouts.', 1800, 'https://item-shopping.c.yimg.jp/i/j/import-tabaido_hfayb08hmhkmw9k?resolution=2x', false),
('Tennis Racket', 'Professional grade carbon fiber tennis racket.', 11000, 'https://item-shopping.c.yimg.jp/i/j/kpi_wr8051701?resolution=2x', false),
('Soccer Ball', 'Standard size 5 match soccer ball.', 2000, 'https://item-shopping.c.yimg.jp/i/j/dts_kickerball-nm-a?resolution=2x', false),
('Hiking Backpack', '45L waterproof travel backpack with rain cover.', 5500, 'https://item-shopping.c.yimg.jp/i/j/zozo_103729568?resolution=2x', false),
('Jump Rope', 'Tangle-free speed jump rope for cardio.', 800, 'https://item-shopping.c.yimg.jp/i/j/linofle_ly-0417?resolution=2x', false),

-- Health & Beauty (41-50)
('Vitamin C Serum', 'Anti-aging facial serum for glowing skin.', 3200, 'https://item-shopping.c.yimg.jp/i/j/kurasio-en_zsh-cserum50-1?resolution=2x', false),
('Electric Toothbrush', 'Sonic toothbrush with 3 brushing modes and timer.', 4500, 'https://item-shopping.c.yimg.jp/i/j/joshin_4573502851914-23-5907?resolution=2x', false),
('Hair Dryer', 'Ionic hair dryer with 2 speeds and cool shot.', 3800, 'https://item-shopping.c.yimg.jp/i/j/kinujojapan-shop_hw001?resolution=2x', false),
('Moisturizing Lotion', 'Daily body lotion for dry and sensitive skin.', 1200, 'https://item-shopping.c.yimg.jp/i/j/bettysbeauty_11215042?resolution=2x', false),
('Men''s Cologne', 'Woody and spicy signature fragrance (100ml).', 6500, 'https://item-shopping.c.yimg.jp/i/j/parfumearth_zzcrd11-050?resolution=2x', false),
('Makeup Brush Set', '12-piece professional cosmetic brush kit.', 2500, 'https://item-shopping.c.yimg.jp/i/j/bettysbeauty_c0710044?resolution=2x', false),
('Shampoo & Conditioner', 'Sulfate-free argan oil hair care set.', 2800, 'https://item-shopping.c.yimg.jp/i/j/e-alamode_3605-g50st10?resolution=2x', false),
('Sunscreen SPF 50', 'Water-resistant broad spectrum sunblock.', 1500, 'https://item-shopping.c.yimg.jp/i/j/az-market_null-sunscreen?resolution=2x', false),
('Massage Gun', 'Deep tissue muscle massager with 6 heads.', 8500, 'https://item-shopping.c.yimg.jp/i/j/sywh_ddgqwg418?resolution=2x', false),
('Lip Balm (3-Pack)', 'Natural beeswax lip care.', 900, 'https://item-shopping.c.yimg.jp/i/j/womensfitness_wm-04598?resolution=2x', false),

-- Toys & Games (51-60)
('Building Blocks Set', '1000-piece classic colorful brick set.', 4500, 'https://item-shopping.c.yimg.jp/i/j/baby-hoppe_sna058?resolution=2x', false),
('Board Game: Strategy', 'Award-winning family strategy board game.', 5000, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0cl59fj8z-n?resolution=2x', false),
('RC Car', 'High-speed remote control buggy.', 3500, 'https://item-shopping.c.yimg.jp/i/j/vt-web_vt-kidsrc-assort?resolution=2x', false),
('Plush Bear', 'Giant 24-inch soft teddy bear.', 2800, 'https://item-shopping.c.yimg.jp/i/j/airim-baby_peacebear?resolution=2x', false),
('Puzzle 1000 Pieces', 'Beautiful landscape jigsaw puzzle.', 1500, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-1797227424-n?resolution=2x', false),
('Action Figure Set', 'Pack of 4 superhero action figures.', 3200, 'https://item-shopping.c.yimg.jp/i/j/digitamin_zf150011?resolution=2x', false),
('Dollhouse', 'Wooden dollhouse with mini furniture included.', 8500, 'https://item-shopping.c.yimg.jp/i/j/supersportsxebio_10902400201?resolution=2x', false),
('Water Gun', 'High-capacity summer water blaster.', 1200, 'https://item-shopping.c.yimg.jp/i/j/poposhop_hhleter22722513267ef?resolution=2x', false),
('Educational Tablet', 'Interactive learning toy for toddlers.', 4000, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0ctd82qgb-n?resolution=2x', false),
('Card Game', 'Fast-paced party card game for 2-8 players.', 1800, 'https://item-shopping.c.yimg.jp/i/j/hobbyone_tradingcardcase-02?resolution=2x', false),

-- Books & Media (61-70)
('Novel: The Great Adventure', 'Bestselling fiction paperback.', 1500, 'https://item-shopping.c.yimg.jp/i/j/magicdoor_9780385386203?resolution=2x', false),
('Cookbook: Easy Meals', '100 quick and healthy recipes.', 2200, 'https://item-shopping.c.yimg.jp/i/j/magicdoor_9780794604974?resolution=2x', false),
('Learn Java Programming', 'Comprehensive guide to modern Java.', 4500, 'https://item-shopping.c.yimg.jp/i/j/magicdoor_9780321245748?resolution=2x', false),
('History of the World', 'Hardcover encyclopedia of global history.', 3800, 'https://item-shopping.c.yimg.jp/i/j/neowing_neobk-2978725?resolution=2x', false),
('Sci-Fi Audiobook', '10-hour thrilling audio drama.', 2000, 'https://s.yimg.jp/c/logo/f/2.0/shopping_r_34_2x.png', false),
('Business Strategy', 'Insights from top CEOs and leaders.', 1800, 'https://item-shopping.c.yimg.jp/i/j/booksdream-store2_gs72-006?resolution=2x', false),
('Children''s Bedtime Stories', 'Illustrated tales for kids aged 3-7.', 1200, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0f1t78g7p-n?resolution=2x', false),
('Blank Journal', 'Premium leather-bound writing notebook.', 2500, 'https://item-shopping.c.yimg.jp/i/j/rudie_10004102?resolution=2x', false),
('Manga Vol. 1', 'First volume of the popular action series.', 600, 'https://item-shopping.c.yimg.jp/i/j/kinokuniya_9784834232509?resolution=2x', false),
('Travel Guide: Japan', 'Top spots and hidden gems in Tokyo, Kyoto, and more.', 1600, 'https://item-shopping.c.yimg.jp/i/j/netoff_0011439805?resolution=2x', false),

-- Groceries & Food (71-80)
('Premium Coffee Beans', '1kg dark roast Arabica coffee.', 2800, 'https://item-shopping.c.yimg.jp/i/j/cafegokochi_fbt-2k?resolution=2x', false),
('Green Tea Bags', 'Pack of 100 organic matcha green tea bags.', 1200, 'https://item-shopping.c.yimg.jp/i/j/antina_75093145?resolution=2x', false),
('Extra Virgin Olive Oil', '750ml cold-pressed olive oil.', 1500, 'https://item-shopping.c.yimg.jp/i/j/global-hospitality_gh-008-2?resolution=2x', false),
('Jasmine Rice 5kg', 'High-quality fragrant white rice.', 2500, 'https://item-shopping.c.yimg.jp/i/j/hl-labo_five010?resolution=2x', false),
('Dark Chocolate Bar', '70% cocoa organic chocolate block.', 400, 'https://item-shopping.c.yimg.jp/i/j/speedbody_wm-60721?resolution=2x', false),
('Mixed Nuts', '1kg roasted and salted almond, cashew, and walnut mix.', 3200, 'https://item-shopping.c.yimg.jp/i/j/good-mam88_987651?resolution=2x', false),
('Oatmeal Cereal', 'Large box of healthy breakfast oats.', 800, 'https://item-shopping.c.yimg.jp/i/j/proteinusa_wm-15777?resolution=2x', false),
('Sparkling Water (12-Pack)', 'Refreshing carbonated mineral water.', 1400, 'https://s.yimg.jp/c/logo/f/2.0/shopping_r_34_2x.png', false),
('Honey Jar', '500g raw organic wildflower honey.', 1800, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0d9c1d23x-n?resolution=2x', false),
('Spicy Ramen Pack', '5 servings of extra hot instant noodles.', 600, 'https://s.yimg.jp/c/logo/f/2.0/shopping_r_34_2x.png', false),

-- Furniture (81-90)
('Ergonomic Office Chair', 'Breathable mesh chair with lumbar support.', 15000, 'https://item-shopping.c.yimg.jp/i/j/import-tabaido_hfayb085xq3ykhk?resolution=2x', false),
('Wooden Computer Desk', 'Spacious 120cm oak finish study desk.', 12500, 'https://item-shopping.c.yimg.jp/i/j/import-tabaido_hfayb0c9pfy1lbk?resolution=2x', false),
('3-Seater Sofa', 'Modern fabric living room couch.', 45000, 'https://item-shopping.c.yimg.jp/i/j/yamagiwa_745fs50330-55251?resolution=2x', false),
('Bookshelf', '5-tier minimalist display rack.', 6500, 'https://item-shopping.c.yimg.jp/i/j/monreve_yamatoya-norsta3-bookshelf?resolution=2x', false),
('Coffee Table', 'Glass top center table with wooden legs.', 8000, 'https://item-shopping.c.yimg.jp/i/j/yamagiwa_996rgct88?resolution=2x', false),
('Queen Size Bed Frame', 'Sturdy metal frame with wooden slats.', 22000, 'https://item-shopping.c.yimg.jp/i/j/noxi_hfayb0blttf1f2k?resolution=2x', false),
('Memory Foam Mattress', '10-inch supportive queen mattress.', 38000, 'https://item-shopping.c.yimg.jp/i/j/noxi_hfayb0fhw96sdjk?resolution=2x', false),
('Nightstand', 'Small bedside table with a drawer.', 3500, 'https://item-shopping.c.yimg.jp/i/j/discas_vncm9051?resolution=2x', false),
('TV Stand', 'Entertainment console for up to 60-inch TVs.', 11000, 'https://item-shopping.c.yimg.jp/i/j/stakeba3_s-b0bys38z9t-20240417?resolution=2x', false),
('Floor Lamp', 'Adjustable reading lamp with LED bulb.', 4500, 'https://item-shopping.c.yimg.jp/i/j/aws_aw-0644?resolution=2x', false),

-- Office & Stationery (91-100)
('Gel Pens (10-Pack)', 'Smooth writing black ink pens.', 800, 'https://item-shopping.c.yimg.jp/i/j/dep-dreamfactory_hfayb00uav21qak?resolution=2x', false),
('A4 Printer Paper', '500 sheets of multipurpose copy paper.', 600, 'https://item-shopping.c.yimg.jp/i/j/printjaws_zgaa0535?resolution=2x', false),
('Desk Organizer', 'Mesh metal holder for pens and files.', 1500, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0fl19g4js-n?resolution=2x', false),
('Sticky Notes', 'Pack of 12 colorful sticky note pads.', 900, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b002ixce3s-n?resolution=2x', false),
('Wireless Presenter', 'Laser pointer clicker for PowerPoint presentations.', 2200, 'https://item-shopping.c.yimg.jp/i/j/whatfun_elite-pre-mouse?resolution=2x', false),
('Whiteboard', 'Small magnetic dry-erase board with markers.', 3000, 'https://item-shopping.c.yimg.jp/i/j/officecom_oc-wb1890w?resolution=2x', false),
('Stapler', 'Heavy-duty stapler with 1000 staples included.', 1200, 'https://item-shopping.c.yimg.jp/i/j/livingut_354082?resolution=2x', false),
('File Folders', 'Box of 50 manila organizing folders.', 1800, 'https://item-shopping.c.yimg.jp/i/j/best-importer_yh-b0grfshgml-n?resolution=2x', false),
('Scientific Calculator', 'Advanced calculator for math and engineering.', 2500, 'https://item-shopping.c.yimg.jp/i/j/hotmeteor_b079ytkdfp-a2kvsmoljnqwwp-20260428?resolution=2x', false),
('Webcam 1080p', 'HD video camera for Zoom and video calls.', 4800, 'https://item-shopping.c.yimg.jp/i/j/logicool_c920na?resolution=2x', false);