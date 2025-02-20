import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AttendanceManagement extends JFrame {
    private JTextField studentIdField, courseIdField;
    private JButton markAttendanceButton, viewAttendanceButton;
    private JTextArea displayArea;
    
    private final String URL = "jdbc:mysql://localhost:3306/university_db";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    public AttendanceManagement() {
        setTitle("Attendance Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        
        add(new JLabel("Student ID:"));
        studentIdField = new JTextField(10);
        add(studentIdField);
        
        add(new JLabel("Course ID:"));
        courseIdField = new JTextField(10);
        add(courseIdField);
        
        markAttendanceButton = new JButton("Mark Attendance");
        viewAttendanceButton = new JButton("View Attendance");
        
        add(markAttendanceButton);
        add(viewAttendanceButton);
        
        displayArea = new JTextArea(10, 40);
        add(new JScrollPane(displayArea));
        
        markAttendanceButton.addActionListener(e -> markAttendance());
        viewAttendanceButton.addActionListener(e -> viewAttendance());
    }
    
    private void markAttendance() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO attendance (student_id, course_id, date) VALUES (?, ?, NOW())";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(studentIdField.getText()));
            stmt.setInt(2, Integer.parseInt(courseIdField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Attendance Marked Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Marking Attendance");
        }
    }
    
    private void viewAttendance() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM attendance";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            displayArea.setText("Student ID\tCourse ID\tDate\n");
            while (rs.next()) {
                displayArea.append(rs.getInt("student_id") + "\t" + rs.getInt("course_id") + "\t" + rs.getString("date") + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Fetching Attendance Records");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceManagement().setVisible(true));
    }
}
