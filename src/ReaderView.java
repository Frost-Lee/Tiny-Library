import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderView extends JPanel implements ActionListener {
    private JLabel titleLabel;
    private JLabel navigationLabel;
    private JButton readerSearchButton;
    private JButton readerBorrowButton;
    private JButton readerHistoryButton;
    private JLabel navigationSeparatorLabel;
    private JButton logoutButton;

    ReaderView() {
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
        navigationLabel = new JLabel("Logged as " + TLDataManager.sharedInstance.currentUser);
        navigationLabel.setFont(new TLPlainTextFont());
        navigationLabel.setBounds(20, 60, 250, 30);
        this.add(navigationLabel);

        // Search Button
        readerSearchButton = new JButton("Search");
        readerSearchButton.setFont(new TLPlainTextFont());
        readerSearchButton.setBounds(350, 25, 120, 60);
        readerSearchButton.setActionCommand("readerSearchButton");
        readerSearchButton.addActionListener(this);
        this.add(readerSearchButton);

        // Edit Books Button
        readerBorrowButton = new JButton("Borrow");
        readerBorrowButton.setFont(new TLPlainTextFont());
        readerBorrowButton.setBounds(470, 25, 120, 60);
        readerBorrowButton.setActionCommand("readerBorrowButton");
        readerBorrowButton.addActionListener(this);
        this.add(readerBorrowButton);

        // Reader History Button
        readerHistoryButton = new JButton("History");
        readerHistoryButton.setFont(new TLPlainTextFont());
        readerHistoryButton.setBounds(590, 25, 120, 60);
        readerHistoryButton.setActionCommand("readerHistoryButton");
        readerHistoryButton.addActionListener(this);
        this.add(readerHistoryButton);

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
            case "readerSearchButton":
                ReaderSearchView readerSearchView = new ReaderSearchView();
                Main.sharedInstance.reloadView(readerSearchView);
                break;
            case "readerBorrowButton":
                ReaderBorrowView readerBorrowView = new ReaderBorrowView();
                Main.sharedInstance.reloadView(readerBorrowView);
                break;
            case "readerHistoryButton":
                ReaderHistoryView readerHistoryView = new ReaderHistoryView();
                Main.sharedInstance.reloadView(readerHistoryView);
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
