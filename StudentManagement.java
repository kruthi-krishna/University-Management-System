import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentManagement extends JFrame {
    private JTextField idField, nameField, deptField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private JTextArea displayArea;
    
    // Database connection details
    private final String URL = "jdbc:mysql://localhost:3306/university";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    public StudentManagement() {
        setTitle("Student Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input Fields
        add(new JLabel("Student ID:"));
        idField = new JTextField(10);
        add(idField);
        
        add(new JLabel("Name:"));
        nameField = new JTextField(10);
        add(nameField);
        
        add(new JLabel("Department:"));
        deptField = new JTextField(10);
        add(deptField);
        
        // Buttons
        addButton = new JButton("Add Student");
        updateButton = new JButton("Update Student");
        deleteButton = new JButton("Delete Student");
        viewButton = new JButton("View Students");
        
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);
        
        // Display Area
        displayArea = new JTextArea(10, 40);
        add(new JScrollPane(displayArea));
        
        // Button Listeners
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        viewButton.addActionListener(e -> viewStudents());
    }

    private void addStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO students (id, name, department) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.setString(3, deptField.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Added Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Adding Student");
        }
    }

    private void updateStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE students SET name=?, department=? WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nameField.getText());
            stmt.setString(2, deptField.getText());
            stmt.setInt(3, Integer.parseInt(idField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Updated Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Updating Student");
        }
    }

    private void deleteStudent() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Deleted Successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Deleting Student");
        }
    }

    private void viewStudents() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM students";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            displayArea.setText("ID\tName\tDepartment\n");
            while (rs.next()) {
                displayArea.append(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("department") + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Fetching Students");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagement().setVisible(true));
    }
}
