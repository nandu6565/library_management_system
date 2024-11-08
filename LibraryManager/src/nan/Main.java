package nan;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            LibraryManager libraryManager = new LibraryManager();

            // Adding a book
            libraryManager.addBook( "Java Programming", "Author A", "Publisher X", 2023);

            // Adding a borrower
            libraryManager.addBorrower( "John Doe", "johndoe@example.com", "1234567890");

            // Issuing a book
            libraryManager.issueBook(1, 1);

            // Returning a book
            libraryManager.returnBook(1);

            libraryManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

