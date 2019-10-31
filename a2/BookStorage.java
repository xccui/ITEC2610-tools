import java.util.ArrayList;
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
        bookMap = new HashMap<>();
        bookMap.put("Book1", new Book("Book1", "AuthorA", 100, Book.BookCategory.Programming));
        bookMap.put("Book2", new Book("Book2", "AuthorB", 200, Book.BookCategory.Design));
        bookMap.put("Book3", new Book("Book3", "AuthorB", 300, Book.BookCategory.Database));
        bookMap.put("Book4", new Book("Book4", "AuthorB", 400, Book.BookCategory.Programming));
        bookMap.put("Book5", new Book("Book5", "AuthorB", 500, Book.BookCategory.Programming));
        bookMap.put("Book11", new Book("Book11", "AuthorB", 600, Book.BookCategory.Database));
        bookMap.put("Book21", new Book("Book21", "AuthorB", 700, Book.BookCategory.Programming));
        bookMap.put("Book31", new Book("Book31", "AuthorB", 800, Book.BookCategory.Design));
        bookMap.put("Book41", new Book("Book41", "AuthorB", 900, Book.BookCategory.Programming));
    }

    /**
     * Uses the given book to update the existing book with the same title.
     */
    public void update(Book book) {
        // TODO Add your code here...
        bookMap.put(book.getTitle(), book);
    }

    /**
     * Removes a book by title.
     */
    public void remove(String bookTitle) {
        // TODO Add your code here...
        bookMap.remove(bookTitle);
    }

    /**
     * Adds a new book.
     */
    public void add(Book book) {
        // TODO Add your code here...
        bookMap.put(book.getTitle(), book);
    }

    /**
     * Gets a book by title.
     */
    public Book getByTitle(String title) {
        // TODO Add your code here...
        return bookMap.get(title);
    }

    /**
     * Searches for books whose title contains the keyword and returns them ordered by titles (in alphabet order).
     */
    public List<Book> titleSearch(String keyword) {
        // TODO Add your code here...
        return sortByTitle(bookMap.entrySet().stream().filter(e -> e.getKey().contains(keyword)).map(Map.Entry::getValue).collect(Collectors.toList()));
    }

    /**
     * Returns all books sorted by their titles (in alphabet order).
     */
    public List<Book> getAll() {
        // TODO Add your code here...
        return sortByTitle(new ArrayList<>(bookMap.values()));
    }

    /**
     * Sorts a list of books by their titles in alphabet order.
     */
    private List<Book> sortByTitle(List<Book> book) {
        // TODO Add your code here...
        book.sort(Comparator.comparing(Book::getTitle));
        return book;
    }

}
