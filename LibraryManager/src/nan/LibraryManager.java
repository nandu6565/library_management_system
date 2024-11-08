package nan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryManager {
    private static final String URL = "jdbc:mysql://localhost:3306/library_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Nandhu@65";
    private Connection connection;

    public LibraryManager() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to the database.");
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection closed.");
        }
    }

    // Book management methods
    public void addBook( String title, String author, String publisher, int year) throws SQLException {
        String query = "INSERT INTO Books ( title, author, publisher, year, status) VALUES ( ?, ?, ?, ?, 'available')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, publisher);
            stmt.setInt(4, year);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        }
    }
    
   


    public void updateBookStatus(int bookId, String status) throws SQLException {
        String query = "UPDATE Books SET status = ? WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
            System.out.println("Book status updated.");
        }
    }

    // Borrower management methods
    public void addBorrower( String name, String email, String phone) throws SQLException {
        String query = "INSERT INTO Borrowers ( name, email, phone) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
            System.out.println("Borrower added successfully.");
        }
    }

    // Transaction management methods
    public void issueBook(int bookId, int borrowerId) throws SQLException {
        String checkAvailabilityQuery = "SELECT status FROM Books WHERE book_id = ?";
        String updateBookStatusQuery = "UPDATE Books SET status = 'issued' WHERE book_id = ?";
        String insertTransactionQuery = "INSERT INTO Transactions (book_id, borrower_id, issue_date) VALUES (?, ?, NOW())";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkAvailabilityQuery);
             PreparedStatement updateStmt = connection.prepareStatement(updateBookStatusQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertTransactionQuery)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getString("status").equals("available")) {
                // Update book status to 'issued'
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                // Insert transaction record
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, borrowerId);
                insertStmt.executeUpdate();

                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book is not available.");
            }
        }
    }


    public void returnBook(int transactionId) throws SQLException {
        String updateTransactionQuery = "UPDATE Transactions SET return_date = NOW() WHERE transaction_id = ?";
        String updateBookStatusQuery = "UPDATE Books SET status = 'available' WHERE book_id = (SELECT book_id FROM Transactions WHERE transaction_id = ?)";

        try (PreparedStatement updateTransStmt = connection.prepareStatement(updateTransactionQuery);
             PreparedStatement updateBookStmt = connection.prepareStatement(updateBookStatusQuery)) {

            updateTransStmt.setInt(1, transactionId);
            updateTransStmt.executeUpdate();

            updateBookStmt.setInt(1, transactionId);
            updateBookStmt.executeUpdate();

            System.out.println("Book returned successfully.");
        }
    }
}

