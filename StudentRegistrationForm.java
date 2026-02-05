package uganda.studentregistrationform;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.*;
import java.util.regex.Pattern;

public class StudentRegistrationForm extends JFrame {

    // Form Components
    private JTextField txtFirst, txtLast, txtEmail, txtConfirmEmail;
    private JPasswordField txtPass, txtConfirmPass;
    private JComboBox<Integer> cbYear, cbDay;
    private JComboBox<String> cbMonth;
    private JRadioButton rbMale, rbFemale;
    private JRadioButton[] deptRadios;
    private JTextArea displayArea;

    private static int idCounter = 1;

    public StudentRegistrationForm() {
        setTitle("New Student Registration Form");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ===== INPUT FIELDS =====
        addField(leftPanel, gbc, 0, "Student First Name:", txtFirst = new JTextField(15));
        addField(leftPanel, gbc, 1, "Student Last Name:", txtLast = new JTextField(15));
        addField(leftPanel, gbc, 2, "Email Address:", txtEmail = new JTextField(15));
        addField(leftPanel, gbc, 3, "Confirm Email:", txtConfirmEmail = new JTextField(15));
        addField(leftPanel, gbc, 4, "Password:", txtPass = new JPasswordField(15));
        addField(leftPanel, gbc, 5, "Confirm Password:", txtConfirmPass = new JPasswordField(15));

        // ===== DOB =====
        gbc.gridy = 6;
        gbc.gridx = 0;
        leftPanel.add(new JLabel("Date of Birth:"), gbc);

        cbYear = new JComboBox<>();
        for (int i = 1960; i <= LocalDate.now().getYear(); i++) cbYear.addItem(i);

        cbMonth = new JComboBox<>(new String[]{
                "January","February","March","April","May","June",
                "July","August","September","October","November","December"
        });

        cbDay = new JComboBox<>();
        updateDays();

        JPanel dobPanel = new JPanel();
        dobPanel.add(cbYear);
        dobPanel.add(cbMonth);
        dobPanel.add(cbDay);

        gbc.gridx = 1;
        leftPanel.add(dobPanel, gbc);

        // ===== GENDER =====
        gbc.gridy = 7;
        gbc.gridx = 0;
        leftPanel.add(new JLabel("Gender:"), gbc);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JPanel genderPanel = new JPanel();
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);

        gbc.gridx = 1;
        leftPanel.add(genderPanel, gbc);

        // ===== DEPARTMENT =====
        gbc.gridy = 8;
        gbc.gridx = 0;
        leftPanel.add(new JLabel("Department:"), gbc);

        String[] depts = {
                "Civil", "CSE", "Electrical", "E & C", "Mechanical"
        };

        deptRadios = new JRadioButton[depts.length];
        ButtonGroup deptGroup = new ButtonGroup();
        JPanel deptPanel = new JPanel(new GridLayout(3, 2));

        for (int i = 0; i < depts.length; i++) {
            deptRadios[i] = new JRadioButton(depts[i]);
            deptGroup.add(deptRadios[i]);
            deptPanel.add(deptRadios[i]);
        }

        gbc.gridx = 1;
        leftPanel.add(deptPanel, gbc);

        // ===== BUTTONS =====
        JButton btnSubmit = new JButton("Submit");
        JButton btnCancel = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSubmit);
        btnPanel.add(btnCancel);

        gbc.gridy = 9;
        leftPanel.add(btnPanel, gbc);

        // ===== RIGHT PANEL =====
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Your Data is Below:"), BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        rightPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel);

        // ===== EVENTS =====
        cbMonth.addActionListener(e -> updateDays());
        cbYear.addActionListener(e -> updateDays());

        btnSubmit.addActionListener(e -> handleSubmit());
        btnCancel.addActionListener(e -> clearForm());
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String label, JComponent field) {
        gbc.gridy = y;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void updateDays() {
        int year = (int) cbYear.getSelectedItem();
        int month = cbMonth.getSelectedIndex() + 1;
        int days = YearMonth.of(year, month).lengthOfMonth();
        cbDay.removeAllItems();
        for (int i = 1; i <= days; i++) cbDay.addItem(i);
    }

    private void handleSubmit() {
        displayArea.setText("");
        StringBuilder errors = new StringBuilder();

        String first = txtFirst.getText().trim();
        String last = txtLast.getText().trim();
        String email = txtEmail.getText().trim();
        String confirmEmail = txtConfirmEmail.getText().trim();
        String pass = new String(txtPass.getPassword());
        String confirmPass = new String(txtConfirmPass.getPassword());

        // ===== VALIDATION =====
        if (first.isEmpty() || last.isEmpty())
            errors.append("• First and Last names are required\n");

        if (email.isEmpty() || confirmEmail.isEmpty())
            errors.append("• Email fields are required\n");
        else if (!email.equals(confirmEmail))
            errors.append("• Emails do not match\n");
        else if (!Pattern.matches("^(.+)@(.+)$", email))
            errors.append("• Invalid email format\n");

        if (pass.length() < 8 || pass.length() > 20 ||
                !pass.matches(".*[A-Za-z].*") ||
                !pass.matches(".*\\d.*"))
            errors.append("• Password must be 8–20 chars with letter & digit\n");

        if (!pass.equals(confirmPass))
            errors.append("• Passwords do not match\n");

        LocalDate dob = LocalDate.of(
                (int) cbYear.getSelectedItem(),
                cbMonth.getSelectedIndex() + 1,
                (int) cbDay.getSelectedItem()
        );

        int age = Period.between(dob, LocalDate.now()).getYears();
        if (age < 16 || age > 60)
            errors.append("• Age must be between 16 and 60\n");

        if (!rbMale.isSelected() && !rbFemale.isSelected())
            errors.append("• Gender must be selected\n");

        boolean deptSelected = false;
        for (JRadioButton rb : deptRadios)
            if (rb.isSelected()) deptSelected = true;

        if (!deptSelected)
            errors.append("• Department must be selected\n");

        // ===== RESULT =====
        if (errors.length() > 0) {
            displayArea.append("ERRORS:\n" + errors);
            return;
        }

        processSuccess(first, last, email, dob);
    }

    private void processSuccess(String first, String last, String email, LocalDate dob) {
        String gender = rbMale.isSelected() ? "M" : "F";
        String dept = "";

        for (JRadioButton rb : deptRadios)
            if (rb.isSelected()) dept = rb.getText();

        String id = LocalDate.now().getYear() + "-" + String.format("%05d", idCounter++);

        String record = String.format(
                "ID: %s | %s %s | %s | %s | %s",
                id, last, first, gender, dept, dob
        );

        displayArea.append(record + "\n");

        try (PrintWriter pw = new PrintWriter(new FileWriter("students.csv", true))) {
            pw.println(id + "," + last + " " + first + "," + gender + "," + dept + "," + dob + "," + email);
        } catch (IOException ex) {
            displayArea.append("File error occurred\n");
        }
    }

    private void clearForm() {
        txtFirst.setText("");
        txtLast.setText("");
        txtEmail.setText("");
        txtConfirmEmail.setText("");
        txtPass.setText("");
        txtConfirmPass.setText("");

        rbMale.setSelected(false);
        rbFemale.setSelected(false);

        for (JRadioButton rb : deptRadios)
            rb.setSelected(false);

        cbYear.setSelectedIndex(0);
        cbMonth.setSelectedIndex(0);
        updateDays();

        displayArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRegistrationForm().setVisible(true));
    }
}
