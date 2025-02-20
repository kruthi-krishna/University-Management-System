import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class FacultyManagement extends JFrame {
    private JTextField idField, nameField, departmentField;
    private JButton addButton, updateButton, deleteButton, viewButton;

    public FacultyManagement() {
        setTitle("Faculty Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Faculty ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Department:"));
        departmentField = new JTextField();
        add(departmentField);

        addButton = new JButton("Add Faculty");
        updateButton = new JButton("Update Faculty");
        deleteButton = new JButton("Delete Faculty");
        viewButton = new JButton("View Faculty");

        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);

        addButton.addActionListener(e -> addFaculty());
        updateButton.addActionListener(e -> updateFaculty());
        deleteButton.addActionListener(e -> deleteFaculty());
        viewButton.addActionListener(e -> viewFaculty());
    }

    private void addFaculty() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "")) {
            String sql = "INSERT INTO faculty (id, name, department) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.setString(3, departmentField.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Faculty Added Successfully!");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Adding Faculty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFaculty() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "")) {
            String sql = "UPDATE faculty SET name = ?, department = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nameField.getText());
            stmt.setString(2, departmentField.getText());
            stmt.setInt(3, Integer.parseInt(idField.getText()));
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Faculty Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Faculty ID Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Updating Faculty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFaculty() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "")) {
            String sql = "DELETE FROM faculty WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Faculty Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Faculty ID Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Deleting Faculty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewFaculty() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "")) {
            String sql = "SELECT * FROM faculty";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            StringBuilder result = new StringBuilder("Faculty List:\n");
            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("id"))
                      .append(", Name: ").append(rs.getString("name"))
                      .append(", Department: ").append(rs.getString("department"))
                      .append("\n");
            }
            JOptionPane.showMessageDialog(this, result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Viewing Faculty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FacultyManagement().setVisible(true));
    }
}
