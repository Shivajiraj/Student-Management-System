# Student Management System

A simple Student Management System built using Java Servlets, JDBC, MySQL, HTML, and CSS.

This project allows users to:

* Add student details
* Store student data in MySQL database
* View all student records
* Delete student records
* Manage academic details
* Use a clean web interface with HTML/CSS

---

# Technologies Used

* Java
* Java Servlets
* JDBC
* MySQL
* Apache Tomcat Server
* HTML
* CSS

---

# Project Structure

```text
Student-Management-System/
│
├── StudentMain.java        -> Handles student basic details
├── AcademicMain.java       -> Handles academic details
├── ViewStudents.java       -> Displays all students
├── DeleteStudent.java      -> Deletes student records
│
├── home.html               -> Home page
├── basic.html              -> Student form page
├── academic.html           -> Academic details page
│
└── README.md
```

---

# How This Project Works

## 1. Home Page

The application starts with:

```text
home.html
```

This page contains navigation buttons and links to:

* Add Student
* Academic Details
* View Students

---

## 2. Adding Student Details

When the user fills the form in:

```text
basic.html
```

The form sends data to:

```java
StudentMain.java
```

This servlet:

1. Receives form data using `request.getParameter()`
2. Connects to MySQL using JDBC
3. Executes SQL INSERT query
4. Saves the student details into database
5. Sends response back to browser

Example:

```java
String name = request.getParameter("name");
```

---

## 3. Academic Details

The:

```text
academic.html
```

page collects academic information such as:

* Marks
* Grade
* Branch
* Other academic data

This data is handled by:

```java
AcademicMain.java
```

The servlet inserts academic details into the database.

---

## 4. Viewing Student Records

The:

```java
ViewStudents.java
```

servlet:

1. Connects to database
2. Executes SELECT query
3. Reads data using ResultSet
4. Dynamically generates HTML table
5. Displays all students in browser

Example:

```java
ResultSet rs = ps.executeQuery();
```

---

## 5. Deleting Student Records

The:

```java
DeleteStudent.java
```

servlet removes student records from database.

It:

1. Receives student ID
2. Executes DELETE SQL query
3. Removes the student from database
4. Refreshes the student list

Example:

```sql
DELETE FROM students WHERE id = ?
```

---

# Database Setup (MySQL)

## Step 1: Install MySQL

Install:

* MySQL Server
* MySQL Workbench (optional)

---

## Step 2: Create Database

Open MySQL and run:

```sql
CREATE DATABASE studentdb;
```

---

## Step 3: Use Database

```sql
USE studentdb;
```

---

## Step 4: Create Student Table

```sql
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    branch VARCHAR(50)
);
```

---

## Step 5: Create Academic Table

```sql
CREATE TABLE academic (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    marks DOUBLE,
    grade VARCHAR(10)
);
```

---

# JDBC Setup

## What is JDBC?

JDBC (Java Database Connectivity) is used to connect Java applications with databases.

This project uses JDBC to connect Java Servlets with MySQL.

---

# Download MySQL JDBC Driver

Download:

```text
mysql-connector-j
```

from:

[https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)

---

# Add JDBC Driver in Eclipse

## Method 1 (Recommended)

1. Right click project
2. Build Path
3. Configure Build Path
4. Libraries
5. Add External JARs
6. Select MySQL connector JAR
7. Apply and Close

---

# JDBC Connection Example

```java
Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/studentdb",
    "root",
    "password"
);
```

Replace:

* `root` with your MySQL username
* `password` with your MySQL password

---

# Apache Tomcat Setup

## Step 1: Download Tomcat

Download Apache Tomcat:

[https://tomcat.apache.org/](https://tomcat.apache.org/)

Recommended version:

```text
Apache Tomcat 10
```

because this project uses:

```java
jakarta.servlet
```

---

## Step 2: Extract Tomcat

Extract Tomcat ZIP file.

Example:

```text
C:\apache-tomcat-10
```

---

## Step 3: Configure Tomcat in Eclipse

1. Open Eclipse
2. Go to:

```text
Window → Preferences → Server → Runtime Environments
```

3. Click:

```text
Add
```

4. Select:

```text
Apache Tomcat v10.0
```

5. Browse Tomcat folder
6. Finish

---

# Convert Project to Dynamic Web Project

If needed:

1. Right click project
2. Configure
3. Convert to Dynamic Web Project

---

# Add Server in Eclipse

1. Open Servers tab
2. Right click
3. New → Server
4. Select Tomcat v10
5. Finish

---

# Run the Project

## Step 1

Right click project.

## Step 2

Select:

```text
Run As → Run on Server
```

## Step 3

Choose:

```text
Tomcat Server
```

## Step 4

Application opens in browser.

Example:

```text
http://localhost:8080/StudentManagementSystem/home.html
```

---

# Important Concepts Used

## Servlets

Servlets handle:

* Form requests
* Database operations
* Dynamic responses

---

## JDBC

Used for:

* Connecting Java with MySQL
* Executing SQL queries
* Reading database data

---

## HTML/CSS

Used for:

* Frontend UI
* Forms
* Tables
* Buttons
* Layout design

---

# CRUD Operations in Project

| Operation | Description          |
| --------- | -------------------- |
| Create    | Add new student      |
| Read      | View student details |
| Update    | Edit student details |
| Delete    | Remove student       |

---

# Common Errors and Solutions

## 1. HTTP Status 404

Cause:

* Wrong URL
* Servlet not mapped properly

Solution:

* Check `@WebServlet()` mapping
* Verify project path

---

## 2. JDBC Driver Not Found

Cause:

* MySQL connector JAR not added

Solution:

* Add connector JAR in Build Path

---

## 3. Access Denied for User

Cause:

* Wrong MySQL username/password

Solution:

* Check database credentials

---

## 4. Port 8080 Already in Use

Cause:

* Another server using Tomcat port

Solution:

* Change Tomcat port in server.xml

---

# Future Improvements

You can improve this project by adding:

* Login Authentication
* Search Functionality
* Student Photo Upload
* Edit Student Feature
* Session Management
* Bootstrap UI
* REST API
* Pagination
* Spring Boot Migration

---

# Learning Outcome

This project helps in learning:

* Java Web Development
* Servlets
* JDBC
* MySQL Integration
* CRUD Operations
* Frontend + Backend Integration
* MVC Concepts
* Tomcat Deployment

---

# Author

Developed by Shivaji Raj.

GitHub:

[https://github.com/Shivajiraj](https://github.com/Shivajiraj)

LinkedIn:

[https://www.linkedin.com/in/shivaji-raj-55580a309](https://www.linkedin.com/in/shivaji-raj-55580a309)

