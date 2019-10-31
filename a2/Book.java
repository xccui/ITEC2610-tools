/**
 * Book
 */
public class Book {

    public enum BookCategory {
        Programming, Database, Design
    }

    private String title;
    private String authors;
    private int pages;
    private BookCategory category;

    public Book(String title, String authors, int pages, BookCategory category) {
        this.title = title;
        this.authors = authors;
        this.pages = pages;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
