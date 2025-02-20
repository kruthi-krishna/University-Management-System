# University Management System

## 1. Project Description
The **University Management System** is a comprehensive **desktop application** built using **Java (Swing) and MySQL**, designed to streamline student and faculty management. It provides an interactive user interface for handling core administrative functions, including student records, faculty administration, course scheduling, attendance tracking, and examination management.

## 2. Technologies Used
- **Java (Swing)** - For the user interface
- **MySQL** - For database management
- **XAMPP** - For running MySQL locally
- **JDBC (mysql-connector-java-8.0.27.jar)** - For database connectivity

## 3. Key Features
###  Authentication System
- User login and authentication via **Login.java**
- Secure access control for different management modules

### Student & Faculty Management
- Add, update, delete, and view **students and faculty** records
- Data stored in **students** and **faculty** tables

### Course Scheduling
- Assign courses to students and faculty
- View and manage courses from the **Course Management** module

###  Attendance Tracking
- Mark attendance for students per course
- View attendance history

### Examination & Fee Management *(Coming Soon)*
- Track student grades and tuition payments

## 4. Installation
### Step 1: Clone the Repository
```bash
git clone https://github.com/kruthi-krishna/University-Management-System.git
```

### Step 2: Set Up the Database
- Open **XAMPP** and start **MySQL**.
- Open **phpMyAdmin** and create a database called `university_db`.
- Execute the SQL script located in `src/university/mysql_commands.txt` to create tables.

### Step 3: Install Dependencies
- Ensure **Java 11+** is installed.
- Add the required libraries:
  - `mysql-connector-java-8.0.27.jar`
  - `rs2xml.jar`

### Step 4: Run the Application
- Open the project in an IDE (**NetBeans, IntelliJ, Eclipse**).
- Run `Login.java` to launch the system.
- Log in with valid credentials (or create a new admin user in the database).

## 5. Usage
- **Login with credentials** to access the dashboard.
- **Navigate through modules** to manage students, faculty, courses, and attendance.
- **View and update records** using respective management screens.

## 6. Future Enhancements
- **Web-based version** for remote access
- **AI-powered analytics** for student performance tracking
- **Automated report generation** for administration

## 7. Contributing
Contributions are welcome! Feel free to fork this repository and submit a pull request for improvements.




