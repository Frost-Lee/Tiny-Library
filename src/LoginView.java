import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {

    public JLabel iconLabel;
    public JLabel userLabel;
    public JLabel passwordLabel;
    public JTextField userTextField;
    public JPasswordField passwordField;
    public JButton loginButton;

    private String iconURL = "/Users/licanchen/Desktop/Tiny Library/Resources/MainIcon.png";

    LoginView() {
        this.setLayout(null);
        placeComponents();
    }

    private void placeComponents() {

        // Image Icon
        ImageIcon iconImage = new ImageIcon(iconURL);
        iconImage.setImage(iconImage.getImage().
                getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        iconLabel = new JLabel(iconImage);
        iconLabel.setBounds(325, 100, 150, 150);
        this.add(iconLabel);

        // User Label
        userLabel = new JLabel("User");
        userLabel.setFont(new TLSubtitleFont());
        userLabel.setBounds(300, 300, 70, 50);
        this.add(userLabel);

        // Password Label
        passwordLabel = new JLabel("Key");
        passwordLabel.setFont(new TLSubtitleFont());
        passwordLabel.setBounds(300, 350, 70, 50);
        this.add(passwordLabel);

        // User Text Field
        userTextField = new JTextField();
        userTextField.setBounds(380, 310, 130, 30);
        this.add(userTextField);

        // Password Text Field
        passwordField = new JPasswordField();
        passwordField.setBounds(380, 360, 130, 30);
        this.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new TLPlainTextFont());
        loginButton.setBounds(350, 420, 100, 40);
        loginButton.setActionCommand("loginButton");
        loginButton.addActionListener(this);
        this.add(loginButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "loginButton":
                TLDataManager dataManager = new TLDataManager();
                if (dataManager.connectToDatabase()) {
                    System.out.println("Connected to database.");
                } else {
                    System.out.println("Error when trying to connect to the database.");
                    JOptionPane.showMessageDialog(null,
                            "Cannot connect to the database!", "Tiny Library", JOptionPane.ERROR_MESSAGE);
                }
                String password = String.valueOf(passwordField.getPassword());
                if (userTextField.getText().equals(dataManager.administratorName)) {
                    if (password.equals(dataManager.administratorPassword)) {
                        System.out.println("Administrator logged in.");
                        AdminImportView adminImportView = new AdminImportView();
                        Main.sharedInstance.reloadView(adminImportView);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Wrong Password!", "Tiny Library", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (dataManager.readerLogin(userTextField.getText(), password)) {
                    System.out.println("User logged in.");
                    ReaderSearchView readerSearchView = new ReaderSearchView();
                    Main.sharedInstance.reloadView(readerSearchView);
                } else {
                    System.out.println("User logging failed.");
                    JOptionPane.showMessageDialog(null,
                            "Wrong user name or password!", "Tiny Library", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
