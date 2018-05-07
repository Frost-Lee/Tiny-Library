import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminEditView extends AdminView implements ActionListener {

    private JLabel featureNotationLabel;
    private JLabel bookNameLabel;
    private JLabel publisherLabel;
    private JLabel authorLabel;
    private JLabel yearLabel;
    private JLabel callNumberLabel;
    private JLabel amountLabel;
    private JLabel searchNameLabel;

    private JTextField bookNameTextField;
    private JTextField publisherTextField;
    private JTextField authorTextField;
    private JTextField yearTextField;
    private JTextField callNumberTextField;
    private JTextField amountTextField;
    private JTextField searchNameTextField;

    private JButton searchButton;
    private JButton replaceButton;

    AdminEditView() {
        super();
        placeSubComponent();
    }

    private void placeSubComponent() {
        // Import a Book Label
        featureNotationLabel = new JLabel("Edit a Book");
        featureNotationLabel.setFont(new TLSubtitleFont());
        featureNotationLabel.setBounds(50, 120, 200, 70);
        this.add(featureNotationLabel);

        // Book Name Label
        bookNameLabel = new JLabel("Book Name");
        bookNameLabel.setFont(new TLPlainTextFont());
        bookNameLabel.setBounds(300, 200, 150, 50);
        this.add(bookNameLabel);

        // Publisher Label
        publisherLabel = new JLabel("Publisher");
        publisherLabel.setFont(new TLPlainTextFont());
        publisherLabel.setBounds(300, 250, 150, 50);
        this.add(publisherLabel);

        // Author Label
        authorLabel = new JLabel("Author");
        authorLabel.setFont(new TLPlainTextFont());
        authorLabel.setBounds(300, 300, 150, 50);
        this.add(authorLabel);

        // Year Label
        yearLabel = new JLabel("Year");
        yearLabel.setFont(new TLPlainTextFont());
        yearLabel.setBounds(300, 350, 150, 50);
        this.add(yearLabel);

        // Call Number Label
        callNumberLabel = new JLabel("Call Number");
        callNumberLabel.setFont(new TLPlainTextFont());
        callNumberLabel.setBounds(300, 400, 150, 50);
        this.add(callNumberLabel);

        // Amount Label
        amountLabel = new JLabel("Amount");
        amountLabel.setFont(new TLPlainTextFont());
        amountLabel.setBounds(300, 450, 150, 50);
        this.add(amountLabel);

        // Search Name Label
        searchNameLabel = new JLabel("Seach Book's Name");
        searchNameLabel.setFont(new TLPlainTextFont());
        searchNameLabel.setBounds(50, 200, 200, 50);
        this.add(searchNameLabel);

        // Book Name Text Field
        bookNameTextField = new JTextField();
        bookNameTextField.setFont(new TLPlainTextFont());
        bookNameTextField.setBounds(450, 200, 250, 50);
        bookNameTextField.setEditable(false);
        this.add(bookNameTextField);

        // Publisher Text Field
        publisherTextField = new JTextField();
        publisherTextField.setFont(new TLPlainTextFont());
        publisherTextField.setBounds(450, 250, 250, 50);
        this.add(publisherTextField);

        // Author Text Field
        authorTextField = new JTextField();
        authorTextField.setFont(new TLPlainTextFont());
        authorTextField.setBounds(450, 300, 250, 50);
        this.add(authorTextField);

        // Year Text Field
        yearTextField = new JTextField();
        yearTextField.setFont(new TLPlainTextFont());
        yearTextField.setBounds(450, 350, 250, 50);
        this.add(yearTextField);

        // Call Number Text Field
        callNumberTextField = new JTextField();
        callNumberTextField.setFont(new TLPlainTextFont());
        callNumberTextField.setBounds(450, 400, 250, 50);
        this.add(callNumberTextField);

        // Amount Text Field
        amountTextField = new JTextField();
        amountTextField.setFont(new TLPlainTextFont());
        amountTextField.setBounds(450, 450, 250, 50);
        this.add(amountTextField);

        // Search Name Text Field
        searchNameTextField = new JTextField();
        searchNameTextField.setFont(new TLPlainTextFont());
        searchNameTextField.setBounds(50, 250, 200, 50);
        this.add(searchNameTextField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setFont(new TLPlainTextFont());
        searchButton.setBounds(50, 300, 100, 40);
        searchButton.setActionCommand("searchButton");
        searchButton.addActionListener(this);
        this.add(searchButton);

        // Edit Button
        replaceButton = new JButton("Replace");
        replaceButton.setFont(new TLPlainTextFont());
        replaceButton.setBounds(50, 450, 100, 40);
        replaceButton.setActionCommand("replaceButton");
        replaceButton.addActionListener(this);
        this.add(replaceButton);
    }

    private void handleSearchButton() {
        String[] resultContainer = new String[7];
        if (TLDataManager.sharedInstance.selectBook(searchNameTextField.getText(), resultContainer)) {
            bookNameTextField.setText(resultContainer[0]);
            publisherTextField.setText(resultContainer[1]);
            authorTextField.setText(resultContainer[2]);
            yearTextField.setText(resultContainer[3]);
            callNumberTextField.setText(resultContainer[4]);
            amountTextField.setText(resultContainer[5]);
        } else {
            JOptionPane.showMessageDialog(null, "Book not found.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleReplaceButton() {
        String[] newDataContainer = new String[7];
        newDataContainer[0] = bookNameTextField.getText();
        newDataContainer[1] = publisherTextField.getText();
        newDataContainer[2] = authorTextField.getText();
        newDataContainer[3] = yearTextField.getText();
        newDataContainer[4] = callNumberTextField.getText();
        newDataContainer[5] = amountTextField.getText();
        if (TLDataManager.sharedInstance.updateBook(newDataContainer)) {
            bookNameTextField.setText("");
            publisherTextField.setText("");
            authorTextField.setText("");
            yearTextField.setText("");
            callNumberTextField.setText("");
            amountTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Update Failed.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();
        switch (command) {
            case "searchButton":
                handleSearchButton();
                break;
            case "replaceButton":
                handleReplaceButton();
                break;
        }
    }
}
