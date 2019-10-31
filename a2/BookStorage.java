
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
    public Book[] titleSearch(String keyword) {
        // TODO Add your code here...
        return new Book[0];
    }

    /**
     * Returns all books sorted by their titles (in alphabet order).
     */
    public Book[] getAll() {
        // TODO Add your code here...
        return new Book[0];
    }

    /**
     * Sorts an array of books by their titles in alphabet order.
     */
    private Book[] sortByTitle(Book[] bookArray) {
        // TODO Add your code here...
        return bookArray;
    }

}
