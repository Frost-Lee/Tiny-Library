import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JPanel implements ActionListener {

    private JLabel titleLabel;
    private JLabel navigationLabel;
    private JButton adminImportButton;
    private JButton adminEditButton;
    private JButton adminManageButton;
    private JLabel navigationSeparatorLabel;
    private JButton logoutButton;

    AdminView() {
        this.setLayout(null);
        placeComponents();
    }

    private void placeComponents() {

        // Title Label
        titleLabel = new JLabel("Tiny Library");
        titleLabel.setFont(new TLTitleFont());
        titleLabel.setBounds(20, 10, 280, 50);
        this.add(titleLabel);

        // Navigation Label
        navigationLabel = new JLabel("Logged as Administrator");
        navigationLabel.setFont(new TLPlainTextFont());
        navigationLabel.setBounds(20, 60, 250, 30);
        this.add(navigationLabel);

        // Import Books Button
        adminImportButton = new JButton("Import");
        adminImportButton.setFont(new TLPlainTextFont());
        adminImportButton.setBounds(350, 25, 120, 60);
        adminImportButton.setActionCommand("adminImportButton");
        adminImportButton.addActionListener(this);
        this.add(adminImportButton);

        // Edit Books Button
        adminEditButton = new JButton("Edit");
        adminEditButton.setFont(new TLPlainTextFont());
        adminEditButton.setBounds(470, 25, 120, 60);
        adminEditButton.setActionCommand("adminEditButton");
        adminEditButton.addActionListener(this);
        this.add(adminEditButton);

        // Manage Reader Button
        adminManageButton = new JButton("Manage");
        adminManageButton.setFont(new TLPlainTextFont());
        adminManageButton.setBounds(590, 25, 120, 60);
        adminManageButton.setActionCommand("adminManageButton");
        adminManageButton.addActionListener(this);
        this.add(adminManageButton);

        // Navigation Separator Label
        navigationSeparatorLabel = new JLabel();
        navigationSeparatorLabel.setBounds(20, 100, 760, 3);
        navigationSeparatorLabel.setOpaque(true);
        navigationSeparatorLabel.setBackground(Color.DARK_GRAY);
        this.add(navigationSeparatorLabel);

        // Log Out Button
        logoutButton = new JButton("Log out");
        logoutButton.setFont(new TLCommentFont());
        logoutButton.setBounds(720, 550, 80, 30);
        logoutButton.setForeground(Color.red);
        logoutButton.setActionCommand("logoutButton");
        logoutButton.addActionListener(this);
        this.add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "adminImportButton":
                AdminImportView adminImportView = new AdminImportView();
                Main.sharedInstance.reloadView(adminImportView);
                break;
            case "adminEditButton":
                AdminEditView adminEditView = new AdminEditView();
                Main.sharedInstance.reloadView(adminEditView);
                break;
            case "adminManageButton":
                AdminManageView adminManageView = new AdminManageView();
                Main.sharedInstance.reloadView(adminManageView);
                break;
            case "logoutButton":
                LoginView loginView = new LoginView();
                if (!TLDataManager.sharedInstance.logoutFromDatabase()) {
                    System.out.println("Logout Failed.");
                    JOptionPane.showMessageDialog(null, "Logout failed.",
                            "Tiny Library", JOptionPane.ERROR_MESSAGE);
                } else {
                    Main.sharedInstance.reloadView(loginView);
                }
        }
    }
}
