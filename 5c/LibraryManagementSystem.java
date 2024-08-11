import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1. Single Responsibility Principle (SRP)

// Book class to handle book details
class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Member class to handle member details
class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}

// 2. Open/Closed Principle (OCP)

// Interface for borrowing rules
interface BorrowingRule {
    boolean canBorrow(Book book, Member member);
}

// Concrete borrowing rule: Standard rule
class StandardBorrowingRule implements BorrowingRule {
    @Override
    public boolean canBorrow(Book book, Member member) {
        return book.isAvailable();
    }
}

// 3. Liskov Substitution Principle (LSP)

// Interface for library management
interface LibraryManagement {
    void addBook(Book book);
    void addMember(Member member);
    boolean borrowBook(String isbn, String memberId);
    boolean returnBook(String isbn);
    List<Book> getAvailableBooks();
}

// 4. Interface Segregation Principle (ISP)

// Concrete implementation of library management
class Library implements LibraryManagement {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();
    private BorrowingRule borrowingRule;

    public Library(BorrowingRule borrowingRule) {
        this.borrowingRule = borrowingRule;
    }

    @Override
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    @Override
    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    @Override
    public boolean borrowBook(String isbn, String memberId) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);
        if (book != null && member != null && borrowingRule.canBorrow(book, member)) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}

// 5. Dependency Inversion Principle (DIP)

// Main class to demonstrate the Library Management System
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create borrowing rule
        BorrowingRule rule = new StandardBorrowingRule();

        // Create library with borrowing rule
        Library library = new Library(rule);

        // Create books
        Book book1 = new Book("123", "Java Programming", "John Doe");
        Book book2 = new Book("456", "Advanced Java", "Jane Smith");

        // Create members
        Member member1 = new Member("M001", "Alice");
        Member member2 = new Member("M002", "Bob");

        // Add books and members to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member1);
        library.addMember(member2);

        // Demonstrate borrowing and returning books
        System.out.println("Available books before borrowing:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle());
        }

        library.borrowBook("123", "M001");
        System.out.println("Available books after borrowing:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle());
        }

        library.returnBook("123");
        System.out.println("Available books after returning:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle());
        }
    }
}