package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fonts.*;
import model.*;

public class ReaderBorrowView extends ReaderView implements ActionListener {

    private JLabel featureNotationLabel;

    private JTextField borrowTextField;
    private JTextField returnTextField;

    private JButton borrowButton;
    private JButton returnButton;

    public ReaderBorrowView() {
        super();
        placeSubComponents();
    }

    private void placeSubComponents() {
        // Borrow and Return Label
        featureNotationLabel = new JLabel("Borrow & Return");
        featureNotationLabel.setFont(new TLSubtitleFont());
        featureNotationLabel.setBounds(50, 120, 250, 50);
        this.add(featureNotationLabel);

        // Borrow Text Field
        borrowTextField = new JTextField();
        borrowTextField.setFont(new TLPlainTextFont());
        borrowTextField.setBounds(125, 250, 250, 50);
        this.add(borrowTextField);

        // Return Text Field
        returnTextField = new JTextField();
        returnTextField.setFont(new TLPlainTextFont());
        returnTextField.setBounds(125, 350, 250, 50);
        this.add(returnTextField);

        // Borrow Button
        borrowButton = new JButton("Borrow");
        borrowButton.setFont(new TLPlainTextFont());
        borrowButton.setBounds(375, 250, 100, 50);
        borrowButton.setActionCommand("borrowButton");
        borrowButton.addActionListener(this);
        this.add(borrowButton);

        // Return Button
        returnButton = new JButton("Return");
        returnButton.setFont(new TLPlainTextFont());
        returnButton.setBounds(375, 350, 100, 50);
        returnButton.setActionCommand("returnButton");
        returnButton.addActionListener(this);
        this.add(returnButton);
    }

    private void handleBorrowButton() {
        if (!TLDataManager.sharedInstance.checkForBookAmount(borrowTextField.getText())) {
            System.out.println("Book not found");
            JOptionPane.showMessageDialog(null, "Book not found.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (TLDataManager.sharedInstance.borrowBook(borrowTextField.getText())) {
            System.out.println("Book borrowed");
            borrowTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Book not found.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleReturnButton() {
        if (TLDataManager.sharedInstance.returnBook(returnTextField.getText())) {
            System.out.println("Book returned");
            returnTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Book not found",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();
        switch (command) {
            case "borrowButton":
                handleBorrowButton();
                break;
            case "returnButton":
                handleReturnButton();
                break;
        }
    }
}
