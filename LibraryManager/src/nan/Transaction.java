package nan;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private int bookId;
    private int borrowerId;
    private Date issueDate;
    private Date returnDate;

    public Transaction(int transactionId, int bookId, int borrowerId, Date issueDate, Date returnDate) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getTransactionId() { return transactionId; }
    public int getBookId() { return bookId; }
    public int getBorrowerId() { return borrowerId; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturnDate() { return returnDate; }

    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
}

