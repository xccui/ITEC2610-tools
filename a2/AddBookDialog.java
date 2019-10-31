/**
 * The dialog for adding a new book.
 */
public class AddBookDialog extends BookPropertyDialog {

    public AddBookDialog(BookListWindow owner) {
        super(owner, "Add Book");
    }

    @Override
    protected void doSave(Book book) {
        // TODO Add your code here...
        bookCollection.add(book);
    }
}
