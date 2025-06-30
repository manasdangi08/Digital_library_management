import java.util.*;

class Book {
    String title;
    String author;
    boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Admin {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.title.equals(title));
    }

    public void modifyBook(String oldTitle, String newTitle, String newAuthor) {
        for (Book book : books) {
            if (book.title.equals(oldTitle)) {
                book.title = newTitle;
                book.author = newAuthor;
            }
        }
    }

    public void addUser(String name, String email) {
        users.add(new User(name, email));
    }
}

public class Library {
    Admin admin = new Admin();

    public void issueBook(String title) {
        for (Book book : admin.books) {
            if (book.title.equals(title) && !book.isIssued) {
                book.isIssued = true;
                System.out.println("Book issued: " + title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String title) {
        for (Book book : admin.books) {
            if (book.title.equals(title) && book.isIssued) {
                book.isIssued = false;
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("This book was not issued.");
    }

    public void searchBook(String title) {
        for (Book book : admin.books) {
            if (book.title.equals(title)) {
                System.out.println("Book found: " + book.title + " by " + book.author);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public static void main(String[] args) {
        Library library = new Library();

        // Example usage
        library.admin.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        library.admin.addBook("1984", "George Orwell");

        library.searchBook("1984");
        library.issueBook("1984");
        library.searchBook("1984");
        library.returnBook("1984");
        library.searchBook("1984");
    }
}
