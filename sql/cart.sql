DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS wishlist;

-- 1. Create Table "cart"
CREATE TABLE cart (
    id serial primary key,
    user_id integer,
    item_id integer,
    quantity integer,
    checked boolean,
)

--2. Creat Table "wishlist"
CREATE TABLE wishlist (
    id serial primary key ,
    user_id integer,
    item_id integer
)