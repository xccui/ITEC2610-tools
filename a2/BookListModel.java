import javax.swing.AbstractListModel;
import java.util.List;

/**
 * A book list model used for {@link javax.swing.JList}.
 */
public class BookListModel extends AbstractListModel<String> {
    private List<Book> bookList;

    public BookListModel(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getSize() {
        return bookList.size();
    }

    @Override
    public String getElementAt(int index) {
        return bookList.get(index).getTitle();
    }
}
