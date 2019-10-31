import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A collection of {@link Book}.
 */
public class BookStorage {

    private Map<String, Book> bookMap;

    public BookStorage() {

    }

    /**
     * Initializes the book storage with some arbitrary book objects.
     */
    public void initBooks() {
        // TODO Add your code here...
    }

    /**
     * Uses the given book to update the existing book with the same title.
     */
    public void update(Book book) {
        // TODO Add your code here...
    }

    /**
     * Removes a book by title.
     */
    public void remove(String bookTitle) {
        // TODO Add your code here...
    }

    /**
     * Adds a new book.
     */
    public void add(Book book) {
        // TODO Add your code here...
    }

    /**
     * Gets a book by title.
     */
    public Book getByTitle(String title) {
        // TODO Add your code here...
        return null;
    }

    /**
     * Searches for books whose title contains the keyword and returns them ordered by titles (in alphabet order).
     */
    public List<Book> titleSearch(String keyword) {
        // TODO Add your code here...
        return Collections.emptyList();
    }

    /**
     * Returns all books sorted by their titles (in alphabet order).
     */
    public List<Book> getAll() {
        // TODO Add your code here...
        return Collections.emptyList();
    }

    /**
     * Sorts a list of books by their titles in alphabet order.
     */
    private List<Book> sortByTitle(List<Book> book) {
        // TODO Add your code here...
        return book;
    }

}
