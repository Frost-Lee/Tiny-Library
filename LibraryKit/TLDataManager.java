import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class TLDataManager {

    static TLDataManager sharedInstance;

    final String administratorName = "Administrator";
    final String administratorPassword = "admin";

    String currentUser;
    int currentUserId;

    int maxItemSize = 100;

    private static final String DB_URL = "jdbc:mysql://localhost/TinyLibrary?"
            + "user=root&password=alert&useUnicode=true&characterEncoding=UTF8&useSSL=true";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    TLDataManager() {
        setSharedInstance();
    }

    private void setSharedInstance() {
        sharedInstance = this;
    }

    private String trimStringToSQL(String initialString) {
        return initialString.replace("'", "\\'");
    }

    boolean connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database");
            connection = DriverManager.getConnection(DB_URL);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Database connection error.");
            return false;
        }
        return true;
    }

    boolean logoutFromDatabase() {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (resultSet != null) resultSet.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Database close error.");
            return false;
        }
        return true;
    }

    boolean readerLogin(String userName, String password) {
        String sql = "SELECT reader_id FROM Reader WHERE name = '" + userName + "'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("User name not found.");
                return false;
            }
            int reader_id = resultSet.getInt("reader_id");
            sql = "SELECT password FROM Reader_pass WHERE reader_id = " + String.valueOf(reader_id);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            String systemPassword = resultSet.getString("password");
            if (password.equals(systemPassword)) {
                System.out.println("Reader identified.");
                this.currentUser = userName;
                this.currentUserId = reader_id;
                return true;
            } else {
                System.out.println("Wrong password, login denied.");
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error when trying to login the reader.");
            return false;
        }
    }

    boolean importBooks(String bookName, String publisher, String author,
                               String year, String callNumber, String amount) {
        bookName = trimStringToSQL(bookName);
        publisher = trimStringToSQL(publisher);
        author = trimStringToSQL(author);
        String sql = "INSERT INTO Book VALUES ('" + bookName + "','" + publisher +
                "','" + author + "'," + year + "," + callNumber + "," + amount + ")";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when inserting a book to the database");
            return false;
        }
        return true;
    }

    boolean importBooksFromFile(String fileAddress) {
        try {
            File dataFile = new File(fileAddress);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(dataFile));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            if (!dataFile.isFile() || !dataFile.exists()) {
                System.out.println("File not found");
                return false;
            }
            String[] itemContainer;
            String newItem = bufferedReader.readLine();
            while (newItem != null) {
                itemContainer = newItem.split(" ");
                if (importBooks(itemContainer[0], itemContainer[1], itemContainer[2], itemContainer[3],
                        itemContainer[4], itemContainer[5])) {
                    System.out.println("Book inserted from file");
                } else {
                    System.out.println("Error when inserting a book from file");
                    return false;
                }
                newItem = bufferedReader.readLine();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when import book data file");
            return false;
        }
        return true;
    }

    boolean selectBook(String bookName, String[] resultContainer) {
        String sql = "SELECT * FROM Book WHERE book_name LIKE '%" + trimStringToSQL(bookName) + "%'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("Book not found.");
                return false;
            }
            resultContainer[0] = resultSet.getString("book_name");
            resultContainer[1] = resultSet.getString("publisher");
            resultContainer[2] = resultSet.getString("author");
            resultContainer[3] = String.valueOf(resultSet.getInt("year_of_publication"));
            resultContainer[4] = String.valueOf(resultSet.getInt("call_number"));
            resultContainer[5] = String.valueOf(resultSet.getInt("collection_amount"));
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when selecting a book");
            return false;
        }
        return true;
    }

    boolean selectAllBooks(String[][] dataContainer) {
        String sql = "SELECT * FROM Book";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = resultSet.getString("book_name");
                dataContainer[iterator][1] = resultSet.getString("publisher");
                dataContainer[iterator][2] = resultSet.getString("author");
                dataContainer[iterator][3] = String.valueOf(
                        resultSet.getInt("year_of_publication"));
                dataContainer[iterator][4] = String.valueOf(
                        resultSet.getInt("call_number"));
                dataContainer[iterator][5] = String.valueOf(
                        resultSet.getInt("collection_amount"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Failed when fetching the book data");
            return false;
        }
        return true;
    }

    boolean selectAllBorrows(String[][] dataContainer) {
        String sql = "SELECT * FROM borrow_book ORDER BY borrow_time DESC";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = String.valueOf(
                        resultSet.getInt("reader_id"));
                dataContainer[iterator][1] = resultSet.getString("book_name");
                dataContainer[iterator][2] = String.valueOf(
                        resultSet.getTimestamp("borrow_time"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Failed when fetching the borrow_book data");
            return false;
        }
        return true;
    }

    boolean selectAllReturns(String[][] dataContainer) {
        String sql = "SELECT * FROM return_book ORDER BY return_time DESC";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = String.valueOf(
                        resultSet.getInt("reader_id"));
                dataContainer[iterator][1] = resultSet.getString("book_name");
                dataContainer[iterator][2] = String.valueOf(
                        resultSet.getTimestamp("return_time"));
                dataContainer[iterator][3] = String.valueOf(
                        resultSet.getString("borrow_time"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Failed when fetching the return_book data");
            return false;
        }
        return true;
    }

    boolean selectAllUsers(String[][] dataContainer) {
        String sql = "SELECT * FROM Reader";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = String.valueOf(
                        resultSet.getInt("reader_id"));
                dataContainer[iterator][1] = resultSet.getString("name");
                dataContainer[iterator][2] = resultSet.getString("contact_info");
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Failed when fetching the Reader data");
            return false;
        }
        return true;
    }

    boolean searchNameForBook(String[][] dataContainer, String bookName) {
        String sql = "SELECT * FROM Book WHERE book_name LIKE '%" + trimStringToSQL(bookName) + "%'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = resultSet.getString("book_name");
                dataContainer[iterator][1] = resultSet.getString("publisher");
                dataContainer[iterator][2] = resultSet.getString("author");
                dataContainer[iterator][3] = String.valueOf(
                        resultSet.getInt("year_of_publication"));
                dataContainer[iterator][4] = String.valueOf(
                        resultSet.getInt("call_number"));
                dataContainer[iterator][5] = String.valueOf(
                        resultSet.getInt("collection_amount"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when searching for a book");
            return false;
        }
        return true;
    }

    boolean searchAuthorForBook(String[][] dataContainer, String author) {
        String sql = "SELECT * FROM Book WHERE author LIKE '%" + trimStringToSQL(author) + "%'";
        System.out.println(author);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = resultSet.getString("book_name");
                dataContainer[iterator][1] = resultSet.getString("publisher");
                dataContainer[iterator][2] = resultSet.getString("author");
                dataContainer[iterator][3] = String.valueOf(
                        resultSet.getInt("year_of_publication"));
                dataContainer[iterator][4] = String.valueOf(
                        resultSet.getInt("call_number"));
                dataContainer[iterator][5] = String.valueOf(
                        resultSet.getInt("collection_amount"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when searching for a book");
            return false;
        }
        return true;
    }

    boolean searchYearForBook(String[][] dataContainer, String begin, String end) {
        String sql = "SELECT * FROM Book WHERE year_of_publication >= " + begin +
                " AND year_of_publication <=" + end;
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = resultSet.getString("book_name");
                dataContainer[iterator][1] = resultSet.getString("publisher");
                dataContainer[iterator][2] = resultSet.getString("author");
                dataContainer[iterator][3] = String.valueOf(
                        resultSet.getInt("year_of_publication"));
                dataContainer[iterator][4] = String.valueOf(
                        resultSet.getInt("call_number"));
                dataContainer[iterator][5] = String.valueOf(
                        resultSet.getInt("collection_amount"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when searching for a book");
            return false;
        }
        return true;
    }

    boolean checkForBookAmount(String bookName) {
        String sql = "SELECT collection_amount FROM Book WHERE book_name = '" +
                trimStringToSQL(bookName) + "'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("Book not found");
                return false;
            }
            int amount = resultSet.getInt("collection_amount");
            if (amount < 1) {
                System.out.println("Run out of specific book");
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when searching for a book");
            return false;
        }
        return true;
    }

    boolean updateBook(String newDataContainer[]) {
        String sql = "UPDATE Book SET publisher = '" + trimStringToSQL(newDataContainer[1]) +
                "', author = '" + trimStringToSQL(newDataContainer[2]) +
                "', year_of_publication = " + trimStringToSQL(newDataContainer[3]) +
                ", call_number = " + trimStringToSQL(newDataContainer[4]) +
                ", collection_amount = " + trimStringToSQL(newDataContainer[5]) +
                " WHERE book_name = '" + trimStringToSQL(newDataContainer[0]) + "'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when updating a book");
            return false;
        }
        return true;
    }

    boolean addReader(String newDataContainer[]) {
        String sql = "INSERT INTO Reader VALUES ('" + newDataContainer[0] + "','" +
                newDataContainer[1] + "','" + newDataContainer[2] + "')";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when inserting a reader");
            return false;
        }
        sql = "INSERT INTO Reader_pass VALUES (" + newDataContainer[0] + "," + newDataContainer[3] + ")";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when inserting a reader password");
            return false;
        }
        return true;
    }

    boolean borrowBook(String bookName) {
        String sql = "INSERT INTO borrow_book VALUES (" + String.valueOf(currentUserId) +
                ",'" + bookName + "', NOW())";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Book to borrow not found");
            return false;
        }
        return true;
    }

    boolean returnBook(String bookName) {
        String sql = "SELECT * FROM Book WHERE book_name = '" + bookName + "'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("Book to return not found");
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Book to return not found");
            return false;
        }
        sql = "DELETE FROM borrow_book WHERE book_name = '" + bookName + "' AND reader_id = "
                + String.valueOf(currentUserId);
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Book to return not found");
            return false;
        }
        return true;
    }

    boolean fetchReaderBorrowRecord(String[][] dataContainer) {
        String sql = "SELECT * FROM borrow_book WHERE reader_id = " + String.valueOf(currentUserId) +
                " ORDER BY borrow_time DESC";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = String.valueOf(
                        resultSet.getInt("reader_id"));
                dataContainer[iterator][1] = resultSet.getString("book_name");
                dataContainer[iterator][2] = String.valueOf(
                        resultSet.getTimestamp("borrow_time"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error when fetching user's borrow record");
            return false;
        }
        return true;
    }

    boolean fetchReaderReturnRecord(String[][] dataContainer) {
        String sql = "SELECT * FROM return_book WHERE reader_id = " + String.valueOf(currentUserId) +
                " ORDER BY return_time DESC";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int iterator = 0;
            while (resultSet.next()) {
                dataContainer[iterator][0] = String.valueOf(
                        resultSet.getInt("reader_id"));
                dataContainer[iterator][1] = resultSet.getString("book_name");
                dataContainer[iterator][2] = String.valueOf(
                        resultSet.getTimestamp("return_time"));
                dataContainer[iterator][3] = String.valueOf(
                        resultSet.getString("borrow_time"));
                iterator ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error when fetching user's return record");
            return false;
        }
        return true;
    }

    int userBorrowRecord(String readerName) {
        int reader_id = 0;
        int numberOfUnreturnedBooks = 0;
        String sql = "SELECT reader_id FROM Reader WHERE name = '" + readerName + "'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                reader_id = resultSet.getInt("reader_id");
            }
            if (reader_id == 0) {
                System.out.println("Reader not found when checking record");
                return -1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Reader not found when checking record");
            return -1;
        }
        sql = "SELECT * FROM borrow_book WHERE reader_id = " + String.valueOf(reader_id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                numberOfUnreturnedBooks ++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Reader not found when checking record");
        }
        return numberOfUnreturnedBooks;
    }

    boolean dropReader(String readerName) {
        String sql = "DELETE FROM Reader WHERE name = '" + readerName + "'";
        System.out.println(sql);
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error occurred when dropping a user");
            return false;
        }
        return true;
    }
}
