import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CourseManagement extends JFrame {
    private JTextField courseIdField, courseNameField, studentIdField, facultyIdField;
    private JButton assignToStudentButton, assignToFacultyButton, viewCoursesButton, deleteCourseButton;
    private JTextArea displayArea;
    
    private final String URL = "jdbc:mysql://localhost:3306/university_db";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    public CourseManagement() {
        setTitle("Course Scheduling");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        
        add(new JLabel("Course ID:"));
        courseIdField = new JTextField(10);
        add(courseIdField);
        
        add(new JLabel("Course Name:"));
        courseNameField = new JTextField(10);
        add(courseNameField);
        
        add(new JLabel("Student ID:"));
        studentIdField = new JTextField(10);
        add(studentIdField);
        
        add(new JLabel("Faculty ID:"));
        facultyIdField = new JTextField(10);
        add(facultyIdField);
        
        assignToStudentButton = new JButton("Assign Course to Student");
        assignToFacultyButton = new JButton("Assign Course to Faculty");
        viewCoursesButton = new JButton("View Courses");
        deleteCourseButton = new JButton("Delete Course");
        
        add(assignToStudentButton);
        add(assignToFacultyButton);
        add(viewCoursesButton);
        add(deleteCourseButton);
        
        displayArea = new JTextArea(10, 40);
        add(new JScrollPane(displayArea));
        
        assignToStudentButton.addActionListener(e -> assignCourseToStudent());
        assignToFacultyButton.addActionListener(e -> assignCourseToFaculty());
        viewCoursesButton.addActionListener(e -> viewCourses());
        deleteCourseButton.addActionListener(e -> deleteCourse());
    }
    
    private void assignCourseToStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO student_courses (course_id, student_id) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(courseIdField.getText()));
            stmt.setInt(2, Integer.parseInt(studentIdField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Course Assigned to Student Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Assigning Course to Student");
        }
    }
    
    private void assignCourseToFaculty() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO faculty_courses (course_id, faculty_id) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(courseIdField.getText()));
            stmt.setInt(2, Integer.parseInt(facultyIdField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Course Assigned to Faculty Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Assigning Course to Faculty");
        }
    }
    
    private void viewCourses() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM courses";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            displayArea.setText("Course ID\tCourse Name\n");
            while (rs.next()) {
                displayArea.append(rs.getInt("course_id") + "\t" + rs.getString("course_name") + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Fetching Courses");
        }
    }
    
    private void deleteCourse() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM courses WHERE course_id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(courseIdField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Course Deleted Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Deleting Course");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseManagement().setVisible(true));
    }
}
