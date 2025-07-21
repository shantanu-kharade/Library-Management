import java.util.*;

// Book Class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; // By default, a new book is available
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Display book details
    public void getDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }

    // Check availability
    public boolean checkAvailability() {
        return isAvailable;
    }

    // Borrow book
    public boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    // Return book
    public void returnBook() {
        isAvailable = true;
    }
}

// Library Class
class Library {
    private List<Book> books = new ArrayList<>();

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Search for a book by title
    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Not found
    }

    // Borrow a book by title
    public void borrowBook(String title) {
        Book book = searchBook(title);
        if (book != null) {
            if (book.borrowBook()) {
                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Sorry, the book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Return a book by title
    public void returnBook(String title) {
        Book book = searchBook(title);
        if (book != null) {
            if (!book.isAvailable()) {
                book.returnBook();
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("This book was not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // List all available books
    public void listAvailableBooks() {
        System.out.println("Available Books:");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                book.getDetails();
                System.out.println("---------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books available.");
        }
    }
}


public class Upload {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nLibrary Management System");
            System.out.println("""
                    1. Add Book
                    2. Search Book
                    3. Borrow Book
                    4. Return Book
                    5. List Available Books
                    6. Exit""");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter book author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = sc.nextLine();

                    Book book = new Book(title, author, isbn);
                    library.addBook(book);
                }
                case 2 -> {
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    Book book = library.searchBook(title);
                    if (book != null) {
                        book.getDetails();
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter book title to borrow: ");
                    String title = sc.nextLine();
                    library.borrowBook(title);
                }
                case 4 -> {
                    System.out.print("Enter book title to return: ");
                    String title = sc.nextLine();
                    library.returnBook(title);
                }
                case 5 -> library.listAvailableBooks();
                case 6 -> {
                    System.out.println("Exiting...");
                    isTrue = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}
