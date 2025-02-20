import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("University Management System - Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton studentButton = new JButton("Manage Students");
        JButton facultyButton = new JButton("Manage Faculty");
        JButton attendanceButton = new JButton("Track Attendance");
        JButton examsButton = new JButton("Examination Details");
        JButton feesButton = new JButton("Manage Fees");
        JButton courseButton = new JButton("Manage Courses");
        JButton logoutButton = new JButton("Logout");

        panel.add(studentButton);
        panel.add(facultyButton);
        panel.add(attendanceButton);
        panel.add(examsButton);
        panel.add(feesButton);
        panel.add(courseButton);
        panel.add(logoutButton);

        add(panel);

        studentButton.addActionListener(e -> new StudentManagement().setVisible(true));
        facultyButton.addActionListener(e -> new FacultyManagement().setVisible(true));
        attendanceButton.addActionListener(e -> new AttendanceManagement().setVisible(true));
        examsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Examination Management Coming Soon!"));
        feesButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Fee Management Coming Soon!"));
        courseButton.addActionListener(e -> new CourseManagement().setVisible(true));
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logging out...");
            dispose();
            new Login().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}
