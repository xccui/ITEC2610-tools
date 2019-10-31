import java.util.Arrays;
import java.util.Comparator;

/**
 * A collection of {@link Book}.
 */
public class BookStorage {

    private Book[] books = new Book[100];

    public BookStorage() {

    }

    /**
     * Initializes the book storage with some arbitrary book objects.
     */
    public void initBooks() {
        // TODO Add your code here...
        books[0] = new Book("Book1", "AuthorA", 100, Book.BookCategory.Programming);
        books[1] = new Book("Book2", "AuthorB", 200, Book.BookCategory.Design);
        books[2] = new Book("Book3", "AuthorB", 300, Book.BookCategory.Database);
        books[3] = new Book("Book4", "AuthorB", 400, Book.BookCategory.Programming);
        books[4] = new Book("Book5", "AuthorB", 500, Book.BookCategory.Programming);
        books[5] = new Book("Book11", "AuthorB", 600, Book.BookCategory.Database);
        books[6] = new Book("Book21", "AuthorB", 700, Book.BookCategory.Programming);
        books[7] = new Book("Book31", "AuthorB", 800, Book.BookCategory.Design);
        books[8] = new Book("Book41", "AuthorB", 900, Book.BookCategory.Programming);
    }

    /**
     * Uses the given book to update the existing book with the same title.
     */
    public void update(Book book) {
        // TODO Add your code here...
        for (int i = 0; i < books.length; ++i) {
            if (null != books[i] && books[i].getTitle().equals(book.getTitle())) {
                books[i] = book;
                break;
            }
        }
    }

    /**
     * Removes a book by title.
     */
    public void remove(String bookTitle) {
        // TODO Add your code here...
        for (int i = 0; i < books.length; ++i) {
            if (null != books[i] && books[i].getTitle().equals(bookTitle)) {
                books[i] = null;
                break;
            }
        }
    }

    /**
     * Adds a new book.
     */
    public void add(Book book) {
        // TODO Add your code here...
        for (int i = 0; i < books.length; ++i) {
            if (null == books[i]) {
                books[i] = book;
                break;
            }
        }
    }

    /**
     * Gets a book by title.
     */
    public Book getByTitle(String title) {
        // TODO Add your code here...
        for (int i = 0; i < books.length; ++i) {
            if (null != books[i] && books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }

    /**
     * Searches for books whose title contains the keyword and returns them ordered by titles (in alphabet order).
     */
    public Book[] titleSearch(String keyword) {
        // TODO Add your code here...
        int[] qualified = new int[100];
        int num = 0;
        for (int i = 0; i < books.length; ++i) {
            if (null != books[i] && books[i].getTitle().contains(keyword)) {
                qualified[num++] = i;
            }
        }
        Book[] result = new Book[num];
        for (int i = 0; i < num; ++i) {
            result[i] = books[qualified[i]];
        }
        return sortByTitle(result);
    }

    /**
     * Returns all books sorted by their titles (in alphabet order).
     */
    public Book[] getAll() {
        // TODO Add your code here...
        int[] qualified = new int[100];
        int num = 0;
        for (int i = 0; i < books.length; ++i) {
            if (null != books[i]) {
                qualified[num++] = i;
            }
        }
        Book[] result = new Book[num];
        for (int i = 0; i < num; ++i) {
            result[i] = books[qualified[i]];
        }
        return sortByTitle(result);
    }

    /**
     * Sorts an array of books by their titles in alphabet order.
     */
    private Book[] sortByTitle(Book[] bookArray) {
        // TODO Add your code here...
        Arrays.sort(bookArray, Comparator.comparing(Book::getTitle));
        return bookArray;
    }

}
