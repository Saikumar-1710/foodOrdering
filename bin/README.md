# 🍔 Food Customization & Ordering System

## Major Project — Student Assignment

A **Food Customization & Ordering System** developed using **Java, Spring Boot, Spring MVC, JPA/Hibernate, MySQL and Thymeleaf**.

This project follows the **MVC (Model–View–Controller) Architecture** and provides separate functionalities for **Admin, Staff, Guest Customers and Prime Customers**.

### Project Repository

**GitHub Repository:**
https://github.com/Saikumar-1710/foodOrdering

---

# 🎯 Project Objective

The objective of this major project is to develop and enhance a complete **Food Customization & Ordering System** using the existing Spring Boot MVC project.

Students are required to work on their assigned module, understand the existing code structure, and implement new functionality without breaking the existing application.

The final system should provide:

* Admin Management
* Food Management
* Food Customization
* Cart Management
* Sales & Expenditure Reports
* Staff Management
* Staff Attendance
* Staff Leave Management
* Staff Achievements & Qualifications
* Customer Ratings
* Guest Ordering
* Prime User Ordering
* Discounts
* Nutrition-Based Food Recommendations
* Order Placement

---

# 🏗️ Architecture

The application follows the **MVC Architecture**:

```text
                ┌─────────────────────┐
                │      Browser        │
                │  Thymeleaf + HTML   │
                │   CSS + JavaScript  │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │     Controller      │
                │   Spring MVC        │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │       Service       │
                │   Business Logic    │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │     Repository      │
                │ Spring Data JPA     │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │       MySQL         │
                │      Database       │
                └─────────────────────┘
```

### MVC Flow

```text
User
 ↓
Thymeleaf Page
 ↓
Controller
 ↓
Service
 ↓
Repository
 ↓
Database
 ↓
Repository
 ↓
Service
 ↓
Controller
 ↓
Thymeleaf Page
```

---

# 🛠️ Technology Stack

| Technology      | Purpose                 |
| --------------- | ----------------------- |
| Java            | Backend Programming     |
| Spring Boot     | Application Development |
| Spring MVC      | MVC Architecture        |
| Spring Data JPA | Database Operations     |
| Hibernate       | ORM                     |
| MySQL           | Database                |
| Thymeleaf       | Server-Side UI          |
| HTML5           | Page Structure          |
| CSS3            | Styling                 |
| JavaScript      | Dynamic UI              |
| Maven           | Dependency Management   |
| Git & GitHub    | Version Control         |

---

# 📋 Student Assignment

The project is divided into **6 major tasks**.

Each student/team will be assigned one task.

Students must understand the existing project before modifying the application.

---

# 🔴 TASK 1 — Admin Dashboard & Food Management

## Objective

Enhance the Admin Dashboard and make the existing Admin functionality dynamic.

### Requirements

### 1. Admin Navigation

Connect all Admin Dashboard navigation menu items to their respective pages.

Students must:

* Verify every navigation menu.
* Connect broken/dummy links.
* Create missing pages where required.
* Ensure navigation works correctly.

### 2. Dynamic Admin Dashboard

Replace static/hard-coded dashboard values with dynamic database values.

Display information such as:

* Total Foods
* Total Customizations
* Total Orders
* Total Customers
* Total Staff
* Other relevant project statistics

### 3. Food Management

Enhance the existing Food Management module.

Implement/display relevant fields from the existing `Food` entity.

The Admin should be able to:

* Add Food
* View Food
* Edit Food
* Delete Food
* Manage Food Availability/Status

### 4. UI Enhancement

Improve the Admin UI using:

* Dashboard cards
* Animations
* Hover effects
* Modern buttons
* Improved tables
* Form styling
* Transitions
* Responsive layout

### Expected Result

The Admin should be able to manage food and view **dynamic restaurant statistics** from the dashboard.

---

# 🔵 TASK 2 — Cart, Customization & Reports

## Objective

Enhance Cart and Food Customization functionality and develop a complete Admin Reporting module.

### 1. Cart Functionality

Improve the existing Cart functionality.

Display:

* Food Name
* Customization
* Quantity
* Price
* Subtotal
* Total Amount

### 2. Food Customization

At:

```text
/admin/customizations/food/{foodId}
```

Implement:

* Increase (`+`)
* Decrease (`−`)
* Edit
* Delete

Customization information should be displayed clearly.

### 3. Sales Reports

Create an Admin Reports page.

The Admin should be able to view:

* Monthly Sales
* Monthly Expenditure
* Category-wise Sales
* Food-wise Sales
* Total Sales
* Number of Orders
* Best-Selling Food

### 4. Date Filtering

Provide:

* Month
* Year
* From Date
* To Date

Example:

```text
From Date: 01-08-2026
To Date:   31-08-2026
```

Reports should change according to the selected date range.

### 5. Graphical Reports

Display reports using suitable charts such as:

* Bar Chart
* Pie Chart
* Line Chart

Example:

```text
Burger       ₹25,000
Pizza        ₹18,000
Drinks       ₹10,000
Desserts      ₹8,000
```

### 6. UI Enhancement

The Reports Dashboard must contain:

* Animated statistic cards
* Interactive filters
* Charts
* Responsive tables
* Hover effects
* Smooth transitions

### Expected Result

Admin should be able to understand **sales, expenditure, popular foods and category performance** through dynamic reports.

---

# 🟢 TASK 3 — Staff Orders, Payments & Working Hours

## Objective

Develop Staff Dashboard functionality for order management and attendance monitoring.

### 1. Staff Orders

A logged-in staff member should be able to view the orders handled by them.

Display:

* Order ID
* Customer
* Food Items
* Quantity
* Total Amount
* Order Date
* Order Status

### 2. Payment Information

Display payment method:

```text
Cash
Card
Online
```

Also display the payment status where applicable.

### 3. Staff Attendance

The staff member should be able to view:

* Login Time
* Logout Time
* Working Hours
* Date

Example:

```text
Date:          01-09-2026
Login Time:    09:30 AM
Logout Time:   06:30 PM
Working Hours: 9 Hours
```

### 4. Staff Dashboard Summary

Display dynamic information such as:

* Today's Orders
* Total Orders
* Completed Orders
* Today's Working Hours

### 5. UI Enhancement

Improve the Staff Dashboard with:

* Modern dashboard cards
* Animations
* Status badges
* Attractive tables
* Attendance cards
* Hover effects
* Responsive design

### Expected Result

Staff should be able to monitor **their orders, payments and working hours** from their dashboard.

---

# 🟡 TASK 4 — Staff Leave, Qualifications & Ratings

## Objective

Extend the Staff Dashboard with leave management, achievements and customer feedback.

---

## 1. Leave / Holiday Management

Allow staff to apply for leave.

The staff member should be able to:

* Select leave date using a calendar.
* Enter leave reason.
* Submit leave request.
* View previous leave requests.
* View leave status.

Possible statuses:

```text
Pending
Approved
Rejected
```

---

## 2. Extra Qualifications & Achievements

Create a Staff section for:

* Qualification
* Certification
* Achievement
* Special Skill
* Experience
* Description

Example:

```text
Achievement:
5 years of experience in handling VIP guests.
```

Admin should be able to view staff qualifications and achievements.

---

## 3. Customer Staff Rating

Allow customers to rate staff after an applicable service/order interaction.

Display:

```text
★★★★★
```

Store:

* Rating
* Review
* Date
* Staff
* Customer

Display:

* Average Rating
* Number of Reviews
* Customer Comments

### 4. UI Enhancement

Use:

* Calendar UI
* Star rating UI
* Achievement cards
* Animated rating elements
* Staff profile cards
* Hover effects
* Responsive design

### Expected Result

Staff should have a complete profile containing **leave information, qualifications, achievements and customer feedback**.

---

# 🟠 TASK 5 — Guest & Prime Customer Ordering

## Objective

Implement two different customer experiences:

1. Guest User
2. Prime User

---

# A. Guest User

A Guest User should be able to order food without logging in.

Guest functionality:

* View Foods
* View Food Details
* View Customizations
* Select Food
* Select Customization
* Add to Cart
* Place Order

The system should identify the customer as a **Guest User**.

---

# B. Prime User

A logged-in customer should receive additional features.

Prime users should be able to:

* View Profile
* Browse Foods
* Customize Foods
* Add Foods to Cart
* Place Orders
* Receive Discounts

---

# 💰 Discount Functionality

Implement discounts dynamically.

### Bill-Level Discount

Example:

```text
Total Amount : ₹1,000
Discount     : 10%
Discount     : ₹100
Final Amount : ₹900
```

### Item-Level Discount

Example:

```text
Burger Price : ₹200
Discount     : 10%
Final Price  : ₹180
```

The discount should be calculated dynamically.

### UI Enhancement

Improve the customer UI with:

* Attractive food cards
* Food images
* Animated Add-to-Cart buttons
* Prime User badges
* Discount badges
* Hover effects
* CSS animations
* Smooth transitions
* Responsive design

### Expected Result

The application should support both **Guest Ordering and Prime User Ordering**, with additional benefits for Prime Users.

---

# 🟣 TASK 6 — Prime Profile, Nutrition & Checkout

## Objective

Create a personalized food experience for Prime Users.

---

## 1. Prime User Profile

Enhance the customer profile.

The customer should be able to define their nutrition requirement.

Example:

```text
My Requirement:

Protein Required: 50 gm
```

The requirement should be stored in the database.

---

# 2. Nutrition-Based Food Recommendation

Based on the customer's requirement, suggest suitable food items.

Example:

### Customer Requirement

```text
Protein Required: 50 gm
```

### Recommended Foods

| Food            | Protein | Price |
| --------------- | ------: | ----: |
| Grilled Chicken |   35 gm |  ₹250 |
| Protein Bowl    |   42 gm |  ₹300 |
| Chicken Salad   |   30 gm |  ₹220 |

Recommendations should use the nutrition information available in the project.

---

# 3. Cart Quantity Management

In the Cart page implement:

```text
       −       2       +
```

The customer should be able to:

* Increase Quantity
* Decrease Quantity
* Remove Item
* Update Subtotal
* Update Total
* Recalculate Discount
* Recalculate Final Amount

All calculations should be updated correctly.

---

# 4. Place Order

When the customer clicks:

```text
PLACE ORDER
```

The system should:

1. Validate the cart.
2. Calculate total amount.
3. Apply discount.
4. Create the order.
5. Save order information.
6. Clear the cart.
7. Display an order confirmation popup.

Example:

```text
🎉 ORDER PLACED SUCCESSFULLY!

Your order has been placed successfully.

Order ID: #ORD1025
```

### UI Enhancement

The checkout page must contain:

* Animated quantity buttons
* Order summary card
* Discount display
* Nutrition badges
* Food recommendation cards
* Animated Place Order button
* Success popup/modal
* Responsive design

### Expected Result

Prime Users should receive a **personalized food-ordering experience** from nutrition-based recommendations to final order placement.

---

# 🎨 Mandatory UI Enhancement

## This requirement applies to ALL 6 TASKS.

Students must **not submit the existing basic UI without improvement**.

Every assigned module must have a modern and attractive user interface.

### Minimum UI Requirements

Students should implement:

* CSS animations
* Hover effects
* Button animations
* Smooth transitions
* Cards
* Modern tables
* Form styling
* Responsive layouts
* Proper spacing
* Icons where appropriate
* Success/Error messages
* Attractive dashboards

### Example

Instead of:

```text
[Add]
```

Create a styled button with:

* Hover effect
* Transition
* Proper spacing
* Visual feedback

Instead of displaying plain text:

```text
Total Orders: 25
```

Use a dashboard card:

```text
┌─────────────────────────┐
│      TOTAL ORDERS       │
│                         │
│          25             │
│                         │
│       View Orders →     │
└─────────────────────────┘
```

---

# 🔧 Technical Requirements

Students must follow the existing project architecture.

### Backend

Use:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
Database
```

### Frontend

Use:

```text
Thymeleaf
HTML
CSS
JavaScript
```

### Database

Use the existing MySQL database structure and relationships wherever applicable.

Do not unnecessarily create duplicate entities or tables.

---

# ⚠️ Important Development Rules

### 1. Do Not Break Existing Functionality

Students must ensure that their changes do not break:

* Login
* Logout
* Existing Admin functionality
* Existing Staff functionality
* Existing Customer functionality
* Food Management
* Food Customization
* Cart
* Existing database relationships

### 2. No Hard-Coded Database Values

Avoid:

```java
int totalOrders = 25;
```

Use actual database information.

### 3. Business Logic Should Not Be Written Directly in Controller

Avoid putting complete business logic inside:

```java
@Controller
```

Business logic should be handled in:

```text
Service / ServiceImpl
```

### 4. Use Proper Validation

Validate:

* Required fields
* Quantity
* Price
* Dates
* User input
* Order information

### 5. Follow Naming Conventions

Use meaningful names.

Example:

```java
findOrdersByStaffId()
```

instead of:

```java
getData()
```

---

# 📊 Task Distribution

| Task   | Module                              | Major Concepts                        |
| ------ | ----------------------------------- | ------------------------------------- |
| Task 1 | Admin Dashboard & Food Management   | CRUD, MVC, JPA, Dynamic Data          |
| Task 2 | Cart, Customization & Reports       | Cart, Calculations, Filtering, Charts |
| Task 3 | Staff Orders & Attendance           | Orders, Payments, Attendance          |
| Task 4 | Staff Leave, Achievements & Ratings | CRUD, Calendar, Ratings               |
| Task 5 | Guest & Prime Customer              | Authentication, Ordering, Discounts   |
| Task 6 | Prime Profile & Nutrition           | Recommendation, Cart, Checkout        |

---

# 🧪 Testing Requirements

Students must test their assigned functionality using different scenarios.

### Positive Testing

Example:

```text
Valid Food
Valid Quantity
Valid Date
Valid Order
Valid Customer
```

### Negative Testing

Example:

```text
Invalid Quantity
Empty Required Field
Invalid Date
Empty Cart
Unavailable Food
Invalid User Input
```

Students should verify that proper error messages are displayed instead of allowing the application to fail.

---

# 📦 Submission Requirements

Each student/team must submit:

### 1. Source Code

Complete updated Spring Boot project.

### 2. Database

Submit:

* Database SQL script
* Required table changes
* Required sample data

### 3. Screenshots

Provide screenshots of:

* Dashboard
* Forms
* Tables
* Reports
* Cart
* Customer pages
* Success/Error messages
* Other implemented functionality

### 4. Documentation

Include:

```text
Project Overview
Implemented Features
MVC Architecture
Database Changes
Screenshots
Testing
Challenges Faced
Conclusion
```

---

# 🏆 Final Project Outcome

After completing all six tasks, the project should become a complete:

## 🍔 Food Customization & Ordering System

with the following modules:

```text
                    FOOD ORDERING SYSTEM
                            │
          ┌─────────────────┼─────────────────┐
          │                 │                 │
        ADMIN             STAFF           CUSTOMER
          │                 │                 │
    ┌─────┼─────┐      ┌────┼─────┐      ┌───┴────────┐
    │     │     │      │    │     │      │            │
  Food  Custom Reports Orders Attendance Guest      Prime
  Mgmt  ization         │       │          │           │
                        │       │       Ordering    Profile
                        │       │                   Nutrition
                     Payments  Leave                Cart
                              Rating                Discount
                           Achievements              Order
```

The final application should demonstrate practical knowledge of:

* **Java**
* **Spring Boot**
* **Spring MVC**
* **JPA/Hibernate**
* **MySQL**
* **Thymeleaf**
* **HTML/CSS**
* **JavaScript**
* **CRUD Operations**
* **Entity Relationships**
* **Business Logic**
* **Database Integration**
* **Authentication**
* **Cart Management**
* **Order Management**
* **Reporting**
* **Responsive UI Design**

---

# 🚀 Student Goal

> **Do not just complete the functionality. Build your assigned module as if it were going into a real-world restaurant application.**

Every task should contain:

**Working Backend + Database Integration + MVC Architecture + Functional UI + Validation + Modern Design + Animations**

---

## Repository

**Food Ordering Project:**
https://github.com/Saikumar-1710/foodOrdering

### Start Here

1. Clone the existing project.
2. Run the application successfully.
3. Understand the existing MVC structure.
4. Identify existing entities, repositories, services and controllers.
5. Understand the existing Thymeleaf pages.
6. Implement only your assigned task.
7. Test the complete functionality.
8. Improve the UI.
9. Verify that existing functionality still works.
10. Submit the completed module.
