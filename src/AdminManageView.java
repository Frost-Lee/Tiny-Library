import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminManageView extends AdminView implements ActionListener {

    private JLabel featureNotationLabel;
    private JButton manageButton;
    private JButton listButton;

    private JLabel userIdLabel;
    private JLabel userNameLabel;
    private JLabel userContactLabel;
    private JLabel userPasswordLabel;

    private JTextField userIdTextField;
    private JTextField userNameTextField;
    private JTextField userContactTextField;
    private JTextField userPasswordTextField;

    private JLabel dropUserLabel;
    private JTextField dropUserNameTextField;

    private JButton addUserButton;
    private JButton dropUserButton;

    private JButton bookButton;
    private JButton borrowButton;
    private JButton returnButton;
    private JButton userButton;

    private JTable dataTable;
    private JScrollPane tableHolderScrollPane;

    private boolean tapBarSwitch;

    AdminManageView() {
        super();
        placeSubComponents();
        tapBarSwitch = true;
        placeManageComponents();
    }

    private void placeSubComponents() {

        // Manage User Label
        featureNotationLabel = new JLabel("Manage Users");
        featureNotationLabel.setFont(new TLSubtitleFont());
        featureNotationLabel.setBounds(50, 120, 200, 70);
        this.add(featureNotationLabel);

        // Manage Button
        manageButton = new JButton("Manage");
        manageButton.setFont(new TLCommentFont());
        manageButton.setBounds(400, 140, 100, 35);
        manageButton.setActionCommand("manageButton");
        manageButton.addActionListener(this);
        this.add(manageButton);

        // List Button
        listButton = new JButton("List");
        listButton.setFont(new TLCommentFont());
        listButton.setBounds(500, 140, 100, 35);
        listButton.setActionCommand("listButton");
        listButton.addActionListener(this);
        this.add(listButton);

        // User ID Label
        userIdLabel = new JLabel("User ID");
        userIdLabel.setFont(new TLPlainTextFont());
        userIdLabel.setBounds(100, 250, 150, 50);

        // User Name Label
        userNameLabel = new JLabel("User Name");
        userNameLabel.setFont(new TLPlainTextFont());
        userNameLabel.setBounds(100, 300, 150, 50);

        // User Contact Text Field
        userContactLabel = new JLabel("Contact");
        userContactLabel.setFont(new TLPlainTextFont());
        userContactLabel.setBounds(100, 350, 150, 50);

        // User Password Label
        userPasswordLabel = new JLabel("User Password");
        userPasswordLabel.setFont(new TLPlainTextFont());
        userPasswordLabel.setBounds(100, 400, 150, 50);

        // User ID Text Field
        userIdTextField = new JTextField();
        userIdTextField.setFont(new TLPlainTextFont());
        userIdTextField.setBounds(250, 250, 200, 50);

        // User Name Text Field
        userNameTextField = new JTextField();
        userNameTextField.setFont(new TLPlainTextFont());
        userNameTextField.setBounds(250, 300, 200, 50);

        // User Contact Text Field
        userContactTextField = new JTextField();
        userContactTextField.setFont(new TLPlainTextFont());
        userContactTextField.setBounds(250, 350, 200, 50);

        // User Password Text Field
        userPasswordTextField = new JTextField();
        userPasswordTextField.setFont(new TLPlainTextFont());
        userPasswordTextField.setBounds(250, 400, 200, 50);

        // Drop User Label
        dropUserLabel = new JLabel("Drop by Name");
        dropUserLabel.setFont(new TLPlainTextFont());
        dropUserLabel.setBounds(500, 250, 150, 50);

        // Drop User Name TextField
        dropUserNameTextField = new JTextField();
        dropUserNameTextField.setFont(new TLPlainTextFont());
        dropUserNameTextField.setBounds(500, 300, 200, 50);

        // Add User Button
        addUserButton = new JButton("Add");
        addUserButton.setFont(new TLPlainTextFont());
        addUserButton.setBounds(250, 450, 100, 40);
        addUserButton.setActionCommand("addUserButton");
        addUserButton.addActionListener(this);

        // Drop User Button
        dropUserButton = new JButton("Drop");
        dropUserButton.setFont(new TLPlainTextFont());
        dropUserButton.setBounds(500, 350, 100, 40);
        dropUserButton.setActionCommand("dropUserButton");
        dropUserButton.addActionListener(this);

        // Book Button
        bookButton = new JButton("Book");
        bookButton.setFont(new TLPlainTextFont());
        bookButton.setBounds(50, 200, 100, 50);
        bookButton.setActionCommand("bookButton");
        bookButton.addActionListener(this);

        // Borrow Button
        borrowButton = new JButton("Borrow");
        borrowButton.setFont(new TLPlainTextFont());
        borrowButton.setBounds(50, 250, 100, 50);
        borrowButton.setActionCommand("borrowButton");
        borrowButton.addActionListener(this);

        // Return Button
        returnButton = new JButton("Return");
        returnButton.setFont(new TLPlainTextFont());
        returnButton.setBounds(50, 300, 100, 50);
        returnButton.setActionCommand("returnButton");
        returnButton.addActionListener(this);

        // User Button
        userButton = new JButton("User");
        userButton.setFont(new TLPlainTextFont());
        userButton.setBounds(50, 350, 100, 50);
        userButton.setActionCommand("userButton");
        userButton.addActionListener(this);

        // Data Table
        dataTable = new JTable();
        dataTable.setFont(new TLCommentFont());
        dataTable.setSize(550, 1000);
        dataTable.setRowHeight(20);

        // Table Holder Scroll Pane
        tableHolderScrollPane = new JScrollPane();
        tableHolderScrollPane.setBounds(175, 200, 550, 300);
        tableHolderScrollPane.add(dataTable);

    }

    private void placeManageComponents() {
        this.add(userIdLabel);
        this.add(userNameLabel);
        this.add(userContactLabel);
        this.add(userPasswordLabel);
        this.add(userIdTextField);
        this.add(userNameTextField);
        this.add(userContactTextField);
        this.add(userPasswordTextField);
        this.add(dropUserLabel);
        this.add(dropUserNameTextField);
        this.add(addUserButton);
        this.add(dropUserButton);
    }

    private void removeManageComponents() {
        this.remove(userIdLabel);
        this.remove(userNameLabel);
        this.remove(userContactLabel);
        this.remove(userPasswordLabel);
        this.remove(userIdTextField);
        this.remove(userNameTextField);
        this.remove(userContactTextField);
        this.remove(userPasswordTextField);
        this.remove(dropUserLabel);
        this.remove(dropUserNameTextField);
        this.remove(addUserButton);
        this.remove(dropUserButton);
    }

    private void placeListComponents() {
        this.add(bookButton);
        this.add(borrowButton);
        this.add(returnButton);
        this.add(userButton);
        this.add(tableHolderScrollPane);
    }

    private void removeListComponents() {
        this.remove(bookButton);
        this.remove(borrowButton);
        this.remove(returnButton);
        this.remove(userButton);
        this.remove(tableHolderScrollPane);
    }

    private void reloadTable(String[][] tableData, String[] attributes) {
        tableHolderScrollPane.remove(dataTable);
        dataTable = new JTable(tableData, attributes);
        dataTable.setFont(new TLCommentFont());
        dataTable.setSize(550, 1000);
        dataTable.setRowHeight(20);
        tableHolderScrollPane.setViewportView(dataTable);
        this.repaint();
    }

    private void handleAddUser() {
        String[] newDataContainer = new String[5];
        newDataContainer[0] = userIdTextField.getText();
        newDataContainer[1] = userNameTextField.getText();
        newDataContainer[2] = userContactTextField.getText();
        newDataContainer[3] = userPasswordTextField.getText();
        if (TLDataManager.sharedInstance.addReader(newDataContainer)) {
            userIdTextField.setText("");
            userNameTextField.setText("");
            userContactTextField.setText("");
            userPasswordTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Create User Failed.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDropUser() {
        int numberOfUnreturnedBooks =
                TLDataManager.sharedInstance.userBorrowRecord(dropUserNameTextField.getText());
        if (numberOfUnreturnedBooks == -1) {
            System.out.println("Reader not found.");
            JOptionPane.showMessageDialog(null, "Reader not found.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (numberOfUnreturnedBooks > 0) {
            String warning = "The reader have " + String.valueOf(numberOfUnreturnedBooks) +
            " books kept, still drop?";
            int choice = JOptionPane.showConfirmDialog(null, warning,
                    "Tiny Library", JOptionPane.YES_NO_OPTION);
            if (choice == 1) return;
        }
        if (TLDataManager.sharedInstance.dropReader(dropUserNameTextField.getText())) {
            dropUserNameTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Reader not found.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBookButton() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][7];
        if (TLDataManager.sharedInstance.selectAllBooks(tableData)) {
            System.out.println("Table book data fetched");
        } else {
            JOptionPane.showMessageDialog(null, "Cannot fetch book data.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] attributeName = {"Book Name", "Publisher", "Author",
        "Year of Publication", "Call Number", "Collection Amount"};
        reloadTable(tableData, attributeName);
    }

    private void handleBorrowButton() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][4];
        if (TLDataManager.sharedInstance.selectAllBorrows(tableData)) {
            System.out.println("Borrow data fetched");
        } else {
            JOptionPane.showMessageDialog(null, "Cannot fetch borrow data.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] attributeName = {"reader_id", "book_name", "borrow_time"};
        reloadTable(tableData, attributeName);
    }

    private void handleReturnButton() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][5];
        if (TLDataManager.sharedInstance.selectAllReturns(tableData)) {
            System.out.println("Return data fetched");
        } else {
            JOptionPane.showMessageDialog(null, "Cannot fetch return data.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] attributeName = {"reader_id", "book_name", "return_time", "borrow_time"};
        reloadTable(tableData, attributeName);
    }

    private void handleUserButton() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][4];
        if (TLDataManager.sharedInstance.selectAllUsers(tableData)) {
            System.out.println("Reader data fetched");
        } else {
            JOptionPane.showMessageDialog(null, "Cannot fetch reader data.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] attributeName = {"reader_id", "name", "contact_info"};
        reloadTable(tableData, attributeName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();
        switch (command) {
            case "manageButton":
                if (!tapBarSwitch) {
                    removeListComponents();
                    placeManageComponents();
                    tapBarSwitch = !tapBarSwitch;
                    this.repaint();
                }
                break;
            case "listButton":
                if (tapBarSwitch) {
                    removeManageComponents();
                    placeListComponents();
                    tapBarSwitch = !tapBarSwitch;
                    handleBookButton();
                    this.repaint();
                }
                break;
            case "addUserButton":
                handleAddUser();
                break;
            case "dropUserButton":
                handleDropUser();
                break;
            case "bookButton":
                handleBookButton();
                break;
            case "borrowButton":
                handleBorrowButton();
                break;
            case "returnButton":
                handleReturnButton();
                break;
            case "userButton":
                handleUserButton();
                break;
        }
    }
}
