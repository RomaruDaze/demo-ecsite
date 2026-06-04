# Demo EC Site

A full-stack e-commerce web application built with Spring Boot and Thymeleaf, featuring product browsing, cart management, order processing, wishlists, reviews, and an admin dashboard.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.6 |
| Template Engine | Thymeleaf |
| Database | PostgreSQL |
| ORM | Spring JDBC |
| Build Tool | Gradle |
| Other | Lombok, spring-dotenv |

## Features

- **Product Catalog** — Browse 100 items across 10 categories
- **User Auth** — Register, login, and session management
- **Cart** — Add/remove items, adjust quantities, checkout
- **Wishlist** — Save items for later
- **Orders** — Place orders and view order history
- **Reviews** — Rate items (1–5 stars), leave comments, like reviews
- **Admin Dashboard** — Manage users, items, and orders (role-based access)

## Getting Started

### Prerequisites

- Java 21
- PostgreSQL
- Gradle

### Setup

1. Clone the repository
   ```bash
   git clone <repo-url>
   cd demo-ecsite
   ```

2. Create a `.env` file in the project root (copy from `.env.example`):
   ```env
   DB_URL=jdbc:postgresql://localhost:5432/your_database_name
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   ```

3. Initialize the database by running the combined schema file:
   ```bash
   psql -U your_username -d your_database_name -f sql/schema.sql
   ```

4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

5. Open your browser at: `http://localhost:8080/demo`

## Default Credentials

After running `schema.sql`, an admin account is seeded:

| Field | Value |
|---|---|
| Email | `romaru@sample.com` |
| Password | `123` |
| Role | `ADMIN` |

## Project Structure

```
src/main/java/com/example/
├── controller/     # MVC controllers (User, Item, Cart, Order, Wishlist, Review, Admin)
├── domain/         # Domain models (User, Item, Order, CartItem, Review, etc.)
├── repository/     # JDBC repositories
├── service/        # Business logic
└── form/           # Form binding classes

src/main/resources/
├── templates/      # Thymeleaf HTML templates
└── application.yaml

sql/
├── schema.sql      # Combined schema + seed data (single file, run this)
├── users.sql
├── items.sql
├── cart.sql
├── orders.sql
└── review.sql
```

## Database Schema

| Table | Description |
|---|---|
| `users` | User accounts with role-based access |
| `items` | Product catalog |
| `cart` | Shopping cart items per user |
| `wishlist` | Saved items per user |
| `orders` | Order headers |
| `order_items` | Line items per order |
| `reviews` | Product ratings and comments |
| `review_comments` | Replies on reviews |
| `review_likes` | Likes on reviews and comments |
