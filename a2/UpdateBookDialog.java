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
        titleTextField.setText(book.getTitle());
        authorsTextField.setText(book.getAuthors());
        pagesTextField.setText(String.valueOf(book.getPages()));
        categoryComboBox.setSelectedItem(book.getCategory().name());
    }

    @Override
    protected void doSave(Book book) {
        // TODO Add your code here...
        bookCollection.update(book);
    }
}
