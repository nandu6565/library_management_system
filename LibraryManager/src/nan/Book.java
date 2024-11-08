package nan;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String status;

    public Book(int bookId, String title, String author, String publisher, int year, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.status = status;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getYear() { return year; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

