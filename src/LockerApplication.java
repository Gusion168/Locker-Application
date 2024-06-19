import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LockerApplication extends JFrame {

    private JLabel promptLabel;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JButton[] numericButtons;
    private String storedPassword;
    private boolean passwordSet;

    public LockerApplication() {
        super("Locker Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        promptLabel = new JLabel("Enter password to set or verify:");
        passwordField = new JPasswordField(20);
        enterButton = new JButton("Enter");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));

        // Create numeric buttons (1-9)
        numericButtons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            final int num = i + 1;
            numericButtons[i] = new JButton(String.valueOf(num));
            numericButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentPassword = new String(passwordField.getPassword());
                    passwordField.setText(currentPassword + num);
                }
            });
            buttonPanel.add(numericButtons[i]);
        }

        // Button for number 0
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPassword = new String(passwordField.getPassword());
                passwordField.setText(currentPassword + "0");
            }
        });
        buttonPanel.add(zeroButton);

        // Button to clear password field
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
            }
        });
        buttonPanel.add(clearButton);

        // Panel for main components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(promptLabel, BorderLayout.NORTH);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(enterButton, BorderLayout.SOUTH);

        // Combine everything in the main frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!passwordSet) {
                    // Setting password
                    String newPassword = new String(passwordField.getPassword());
                    storedPassword = newPassword;
                    passwordSet = true;
                    promptLabel.setText("Password set successfully.");
                } else {
                    // Verifying password
                    String enteredPassword = new String(passwordField.getPassword());
                    if (enteredPassword.equals(storedPassword)) {
                        promptLabel.setText("Correct Password");
                    } else {
                        promptLabel.setText("Incorrect Password");
                    }
                }
                // Clear the password field after each action
                passwordField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LockerApplication();
            }
        });
    }
}
