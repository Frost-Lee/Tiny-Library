package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fonts.*;
import model.*;

public class AdminImportView extends AdminView implements ActionListener {

    private JLabel featureNotationLabel;
    private JLabel bookNameLabel;
    private JLabel publisherLabel;
    private JLabel authorLabel;
    private JLabel yearLabel;
    private JLabel callNumberLabel;
    private JLabel amountLabel;

    private JTextField bookNameTextField;
    private JTextField publisherTextField;
    private JTextField authorTextField;
    private JTextField yearTextField;
    private JTextField callNumberTextField;
    private JTextField amountTextField;

    private JButton singleImportButton;
    private JButton bulkImportButton;

    public AdminImportView() {
        super();
        placeSubComponents();
    }

    private void placeSubComponents() {

        // Import a Book Label
        featureNotationLabel = new JLabel("Import a Book");
        featureNotationLabel.setFont(new TLSubtitleFont());
        featureNotationLabel.setBounds(50, 120, 200, 70);
        this.add(featureNotationLabel);

        // Book Name Label
        bookNameLabel = new JLabel("Book Name");
        bookNameLabel.setFont(new TLPlainTextFont());
        bookNameLabel.setBounds(100, 200, 150, 50);
        this.add(bookNameLabel);

        // Publisher Label
        publisherLabel = new JLabel("Publisher");
        publisherLabel.setFont(new TLPlainTextFont());
        publisherLabel.setBounds(100, 250, 150, 50);
        this.add(publisherLabel);

        // Author Label
        authorLabel = new JLabel("Author");
        authorLabel.setFont(new TLPlainTextFont());
        authorLabel.setBounds(100, 300, 150, 50);
        this.add(authorLabel);

        // Year Label
        yearLabel = new JLabel("Year");
        yearLabel.setFont(new TLPlainTextFont());
        yearLabel.setBounds(100, 350, 150, 50);
        this.add(yearLabel);

        // Call Number Label
        callNumberLabel = new JLabel("Call Number");
        callNumberLabel.setFont(new TLPlainTextFont());
        callNumberLabel.setBounds(100, 400, 150, 50);
        this.add(callNumberLabel);

        // Amount Label
        amountLabel = new JLabel("Amount");
        amountLabel.setFont(new TLPlainTextFont());
        amountLabel.setBounds(100, 450, 150, 50);
        this.add(amountLabel);

        // Book Name Text Field
        bookNameTextField = new JTextField();
        bookNameTextField.setFont(new TLPlainTextFont());
        bookNameTextField.setBounds(250, 200, 250, 50);
        this.add(bookNameTextField);

        // Publisher Text Field
        publisherTextField = new JTextField();
        publisherTextField.setFont(new TLPlainTextFont());
        publisherTextField.setBounds(250, 250, 250, 50);
        this.add(publisherTextField);

        // Author Text Field
        authorTextField = new JTextField();
        authorTextField.setFont(new TLPlainTextFont());
        authorTextField.setBounds(250, 300, 250, 50);
        this.add(authorTextField);

        // Year Text Field
        yearTextField = new JTextField();
        yearTextField.setFont(new TLPlainTextFont());
        yearTextField.setBounds(250, 350, 250, 50);
        this.add(yearTextField);

        // Call Number Text Field
        callNumberTextField = new JTextField();
        callNumberTextField.setFont(new TLPlainTextFont());
        callNumberTextField.setBounds(250, 400, 250, 50);
        this.add(callNumberTextField);

        // Amount Text Field
        amountTextField = new JTextField();
        amountTextField.setFont(new TLPlainTextFont());
        amountTextField.setBounds(250, 450, 250, 50);
        this.add(amountTextField);

        // Single Import Button
        singleImportButton = new JButton("Import");
        singleImportButton.setFont(new TLPlainTextFont());
        singleImportButton.setBounds(575, 280, 150, 60);
        singleImportButton.setActionCommand("singleImportButton");
        singleImportButton.addActionListener(this);
        this.add(singleImportButton);

        // Bulk Import Button
        bulkImportButton = new JButton("Browse");
        bulkImportButton.setFont(new TLPlainTextFont());
        bulkImportButton.setBounds(575, 350, 150, 45);
        bulkImportButton.setActionCommand("bulkImportButton");
        bulkImportButton.addActionListener(this);
        this.add(bulkImportButton);
    }

    private void handleSingleImportButton() {
        boolean returnValue = TLDataManager.sharedInstance.importBooks(bookNameTextField.getText(),
                publisherTextField.getText(), authorTextField.getText(), yearTextField.getText(),
                callNumberTextField.getText(), amountTextField.getText());
        if (returnValue) {
            System.out.println("Book inserted.");
            bookNameTextField.setText("");
            publisherTextField.setText("");
            authorTextField.setText("");
            yearTextField.setText("");
            callNumberTextField.setText("");
            amountTextField.setText("");
        } else {
            System.out.println("Error occurred when trying to insert a book");
            JOptionPane.showMessageDialog(null, "Wrong Book Info Format.",
                    "TinyLibrary", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBulkImportButton() {
        String fileAddress = JOptionPane.showInputDialog(null, "Input the file address:\n",
                "Tiny Library", JOptionPane.PLAIN_MESSAGE);
        if (fileAddress.equals(""))
            return;
        if (TLDataManager.sharedInstance.importBooksFromFile(fileAddress)) {
            System.out.println("Books imported");
        } else {
            JOptionPane.showMessageDialog(null, "File format Error.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();
        switch (command) {
            case "singleImportButton":
                handleSingleImportButton();
                break;
            case "bulkImportButton":
                handleBulkImportButton();
                break;
        }
    }
}
