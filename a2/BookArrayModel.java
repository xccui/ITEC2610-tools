import javax.swing.AbstractListModel;

/**
 * A book array model used by {@link javax.swing.JList}.
 */
public class BookArrayModel extends AbstractListModel<String> {
    private Book[] bookArray;

    public BookArrayModel(Book[] bookArray) {
        this.bookArray = bookArray;
    }

    public void setBookArray(Book[] bookArray) {
        this.bookArray = bookArray;
    }

    @Override
    public int getSize() {
        return bookArray.length;
    }

    @Override
    public String getElementAt(int index) {
        return bookArray[index].getTitle();
    }
}
