package nan;

public class Borrower {
    private int borrowerId;
    private String name;
    private String email;
    private String phone;

    public Borrower(int borrowerId, String name, String email, String phone) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getBorrowerId() { return borrowerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

