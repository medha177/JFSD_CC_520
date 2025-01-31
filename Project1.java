import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Project1 {
    private final Map<String, String> students = new HashMap<>();
    private final Map<String, Double> grades = new HashMap<>();
    private JFrame frame;

    public Project1() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Simple Student Grading System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        showMainMenu();
        frame.setVisible(true);
    }

    private void showMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton addStudentButton = new JButton("Add Student");
        JButton addGradeButton = new JButton("Add Grade");
        JButton viewGradeButton = new JButton("View Grade");

        addStudentButton.addActionListener(e -> showAddStudentForm());
        addGradeButton.addActionListener(e -> showAddGradeForm());
        viewGradeButton.addActionListener(e -> showViewGradeForm());

        panel.add(addStudentButton);
        panel.add(addGradeButton);
        panel.add(viewGradeButton);

        updateFrameContent(panel);
    }

    private void showAddStudentForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField studentIDField = new JTextField();
        JTextField studentNameField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(e -> {
            String id = studentIDField.getText();
            String name = studentNameField.getText();
            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Student ID and Name cannot be empty.");
            } else {
                students.put(id, name);
                JOptionPane.showMessageDialog(frame, "Student added: " + name);
                studentIDField.setText("");
                studentNameField.setText("");
            }
        });

        backButton.addActionListener(e -> showMainMenu());

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIDField);
        panel.add(new JLabel("Student Name:"));
        panel.add(studentNameField);
        panel.add(submitButton);
        panel.add(backButton);

        updateFrameContent(panel);
    }

    private void showAddGradeForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField studentIDField = new JTextField();
        JTextField gradeField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(e -> {
            String id = studentIDField.getText();
            String gradeText = gradeField.getText();
            if (id.isEmpty() || gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Student ID and Grade cannot be empty.");
            } else {
                try {
                    double grade = Double.parseDouble(gradeText);
                    grades.put(id, grade);
                    JOptionPane.showMessageDialog(frame, "Grade added for student ID: " + id);
                    studentIDField.setText("");
                    gradeField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid grade value.");
                }
            }
        });

        backButton.addActionListener(e -> showMainMenu());

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIDField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);
        panel.add(submitButton);
        panel.add(backButton);

        updateFrameContent(panel);
    }

    private void showViewGradeForm() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField studentIDField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(e -> {
            String id = studentIDField.getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Student ID cannot be empty.");
            } else {
                Double grade = grades.get(id);
                if (grade == null) {
                    JOptionPane.showMessageDialog(frame, "No grade found for student ID: " + id);
                } else {
                    JOptionPane.showMessageDialog(frame, "Grade for student ID " + id + ": " + grade);
                }
                studentIDField.setText("");
            }
        });

        backButton.addActionListener(e -> showMainMenu());

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIDField);
        panel.add(submitButton);
        panel.add(backButton);

        updateFrameContent(panel);
    }

    private void updateFrameContent(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Project1::new);
    }
}
