/**
 * The dialog for updating a book.
 */
public class UpdateBookDialog extends BookPropertyDialog {

    public UpdateBookDialog(BookListWindow owner) {
        super(owner, "Update Book");
        titleTextField.setEnabled(false);
        saveButton.setText("UPDATE");
    }

    /**
     * Initializes the text fields with the given book.
     */
    public void showBook(Book book) {
        // TODO Add your code here...
    }

    @Override
    protected void doSave(Book book) {
        // TODO Add your code here...
    }
}
