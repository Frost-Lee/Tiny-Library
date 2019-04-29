package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fonts.*;
import model.*;

public class ReaderSearchView extends ReaderView implements ActionListener {

    private JLabel featureNotationLabel;
    private JTextField searchNameTextField;
    private JButton searchButton;

    int searchMode = 0;
    private JButton searchNameButton;
    private JButton searchAuthorButton;
    private JButton searchYearButton;

    private JTable dataTable;
    private JScrollPane tableHolderScrollPane;

    public ReaderSearchView() {
        super();
        placeSubComponents();
        handleSearchNameButton();
        searchByName();
    }

    private void placeSubComponents() {

        // Search Book Label
        featureNotationLabel = new JLabel("Search for Books");
        featureNotationLabel.setFont(new TLSubtitleFont());
        featureNotationLabel.setBounds(50, 120, 250, 50);
        this.add(featureNotationLabel);

        // Search Name Button
        searchNameButton = new JButton("Name");
        searchNameButton.setFont(new TLCommentFont());
        searchNameButton.setBounds(100, 170, 100, 30);
        searchNameButton.setActionCommand("searchNameButton");
        searchNameButton.addActionListener(this);
        this.add(searchNameButton);

        // Search Author Button
        searchAuthorButton = new JButton("Author");
        searchAuthorButton.setFont(new TLCommentFont());
        searchAuthorButton.setBounds(200, 170, 100, 30);
        searchAuthorButton.setActionCommand("searchAuthorButton");
        searchAuthorButton.addActionListener(this);
        this.add(searchAuthorButton);

        // Search Year Button
        searchYearButton = new JButton("Year");
        searchYearButton.setFont(new TLCommentFont());
        searchYearButton.setBounds(300, 170, 100, 30);
        searchYearButton.setActionCommand("searchYearButton");
        searchYearButton.addActionListener(this);
        this.add(searchYearButton);

        // Search Name Text Field
        searchNameTextField = new JTextField();
        searchNameTextField.setFont(new TLCommentFont());
        searchNameTextField.setBounds(400, 170, 200, 30);
        this.add(searchNameTextField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setFont(new TLCommentFont());
        searchButton.setBounds(600, 170, 100, 30);
        searchButton.setActionCommand("searchButton");
        searchButton.addActionListener(this);
        this.add(searchButton);

        // Data Table
        dataTable = new JTable();
        dataTable.setFont(new TLCommentFont());
        dataTable.setSize(600, 1000);
        dataTable.setRowHeight(20);

        // Table Holder Scroll Pane
        tableHolderScrollPane = new JScrollPane();
        tableHolderScrollPane.setBounds(100, 220, 600, 300);
        tableHolderScrollPane.add(dataTable);
        this.add(tableHolderScrollPane);
    }

    private void reloadTable(String[][] tableData, String[] attributes) {
        tableHolderScrollPane.remove(dataTable);
        dataTable = new JTable(tableData, attributes);
        dataTable.setFont(new TLCommentFont());
        dataTable.setSize(600, 1000);
        dataTable.setRowHeight(20);
        tableHolderScrollPane.setViewportView(dataTable);
        this.repaint();
    }

    private void handleSearchNameButton() {
        searchNameTextField.setText("");
        switch (searchMode) {
            case 0: searchNameButton.setForeground(Color.BLACK); break;
            case 1: searchAuthorButton.setForeground(Color.BLACK); break;
            case 2: searchYearButton.setForeground(Color.BLACK); break;
        }
        searchMode = 0;
        searchNameButton.setForeground(Color.BLUE);
    }

    private void handleSearchAuthorButton() {
        searchNameTextField.setText("");
        switch (searchMode) {
            case 0: searchNameButton.setForeground(Color.BLACK); break;
            case 1: searchAuthorButton.setForeground(Color.BLACK); break;
            case 2: searchYearButton.setForeground(Color.BLACK); break;
        }
        searchMode = 1;
        searchAuthorButton.setForeground(Color.BLUE);
    }

    private void handleSearchYearButton() {
        searchNameTextField.setText(" - ");
        switch (searchMode) {
            case 0: searchNameButton.setForeground(Color.BLACK); break;
            case 1: searchAuthorButton.setForeground(Color.BLACK); break;
            case 2: searchYearButton.setForeground(Color.BLACK); break;
        }
        searchMode = 2;
        searchYearButton.setForeground(Color.BLUE);
    }

    private void searchByName() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][7];
        if (TLDataManager.sharedInstance.searchNameForBook(tableData, searchNameTextField.getText())) {
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

    private void searchByAuthor() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][7];
        if (TLDataManager.sharedInstance.searchAuthorForBook(tableData, searchNameTextField.getText())) {
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

    private void searchByYear() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][7];
        String[] year = new String[2];
        year = searchNameTextField.getText().split("-");
        if (year.length < 2) {
            JOptionPane.showMessageDialog(null, "Wrong search format.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (TLDataManager.sharedInstance.searchYearForBook(tableData, year[0], year[1])) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        String command = e.getActionCommand();
        switch (command) {
            case "searchButton":
                if (searchMode == 0) {
                    searchByName();
                } else if (searchMode == 1) {
                    searchByAuthor();
                } else if (searchMode == 2) {
                    searchByYear();
                }
                break;
            case "searchNameButton":
                handleSearchNameButton();
                break;
            case "searchAuthorButton":
                handleSearchAuthorButton();
                break;
            case "searchYearButton":
                handleSearchYearButton();
                break;
        }
    }
}
