package views;

import javax.swing.*;
import java.awt.event.ActionEvent;

import fonts.*;
import model.*;

public class ReaderHistoryView extends ReaderView {

    private JButton borrowButton;
    private JButton returnButton;

    private JTable dataTable;
    private JScrollPane tableHolderScrollPane;

    public ReaderHistoryView() {
        super();
        placeSubComponents();
        handleBorrowButton();
    }

    private void placeSubComponents() {

        // Borrow Button
        borrowButton = new JButton("Borrow");
        borrowButton.setFont(new TLPlainTextFont());
        borrowButton.setBounds(50, 120, 100, 50);
        borrowButton.setActionCommand("borrowButton");
        borrowButton.addActionListener(this);
        this.add(borrowButton);

        // Return Button
        returnButton = new JButton("Return");
        returnButton.setFont(new TLPlainTextFont());
        returnButton.setBounds(50, 170, 100, 50);
        returnButton.setActionCommand("returnButton");
        returnButton.addActionListener(this);
        this.add(returnButton);

        // Data Table
        dataTable = new JTable();
        dataTable.setFont(new TLCommentFont());
        dataTable.setSize(550, 1000);
        dataTable.setRowHeight(20);

        // Table Holder Scroll Pane
        tableHolderScrollPane = new JScrollPane();
        tableHolderScrollPane.setBounds(175, 120, 550, 380);
        tableHolderScrollPane.add(dataTable);
        this.add(tableHolderScrollPane);
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

    private void handleBorrowButton() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][4];
        if (TLDataManager.sharedInstance.fetchReaderBorrowRecord(tableData)) {
            System.out.println("User borrow data fetched");
        } else {
            JOptionPane.showMessageDialog(null, "Cannot fetch your borrow data.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] attributeName = {"reader_id", "book_name", "borrow_time"};
        reloadTable(tableData, attributeName);
    }

    private void handleReturnButton() {
        int maxSize = TLDataManager.sharedInstance.maxItemSize;
        String[][] tableData = new String[maxSize][5];
        if (TLDataManager.sharedInstance.fetchReaderReturnRecord(tableData)) {
            System.out.println("User eturn data fetched");
        } else {
            JOptionPane.showMessageDialog(null, "Cannot your fetch return data.",
                    "Tiny Library", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] attributeName = {"reader_id", "book_name", "return_time", "borrow_time"};
        reloadTable(tableData, attributeName);
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
