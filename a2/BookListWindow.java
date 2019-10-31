import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * BookListWindow
 */
public class BookListWindow extends JFrame {

    //======== Top ========
    private JPanel topPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton clearButton;

    //======== Middle ========
    private JScrollPane titleListScrollPane;
    private JList<String> bookTitleList;

    //======== Bottom ========
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton detailButton;
    private JButton removeButton;

    //======== Data ========
    private BookStorage bookStorage;
    private BookListModel bookListModel;

    public BookListWindow(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
        bookListModel = new BookListModel(bookStorage.getAll());
        initComponents();
    }

    /**
     * Clears the search results and list all the books.
     */
    public void resetToAll() {
        bookListModel.setBookList(bookStorage.getAll());
        searchTextField.setText("");
        bookTitleList.updateUI();
    }

    /**
     * Returns the book storage.
     */
    public BookStorage getBookStorage() {
        return bookStorage;
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        Container contentPane = getContentPane();
        this.setTitle("Book Management");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== Top ========
        topPanel = new JPanel();
        searchTextField = new JTextField();
        searchButton = new JButton("SEARCH");
        clearButton = new JButton("CLEAR");

        searchButton.addActionListener(e -> searchAction(e));
        clearButton.addActionListener(e -> clearAction(e));

        {
            // Set the layout for topPanel and add the buttons.
            // TODO Add your code here...
            topPanel.setLayout(new GridLayout(1, 3));
            topPanel.add(searchTextField);
            topPanel.add(searchButton);
            topPanel.add(clearButton);
        }

        //======== Middle ========
        titleListScrollPane = new JScrollPane();
        bookTitleList = new JList<>();

        {
            // Configure bookTitleList.
            //TODO Add your code here...
            bookTitleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            bookTitleList.setModel(bookListModel);

            titleListScrollPane.setViewportView(bookTitleList);
        }

        //======== Bottom ========
        bottomPanel = new JPanel();
        addButton = new JButton("ADD");
        detailButton = new JButton("DETAIL");
        removeButton = new JButton("REMOVE");

        addButton.addActionListener(e -> addAction(e));
        detailButton.addActionListener(e -> detailAction(e));
        removeButton.addActionListener(e -> removeAction(e));

        {
            // Set the layout for bottomPanel and add the buttons.
            // TODO Add your code here...
            bottomPanel.setLayout(new GridLayout(1, 4));
            bottomPanel.add(addButton);
            bottomPanel.add(detailButton);
            bottomPanel.add(removeButton);
        }

        contentPane.setLayout(new BorderLayout());
        {
            // Add the components to contentPane with proper layout options.
            // TODO Add your code here...
            contentPane.add(topPanel, BorderLayout.NORTH);
            contentPane.add(titleListScrollPane, BorderLayout.CENTER);
            contentPane.add(bottomPanel, BorderLayout.SOUTH);
        }

        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * Action for the SEARCH button.
     */
    private void searchAction(ActionEvent e) {
        // TODO Add your code here...
        bookListModel.setBookList(bookStorage.titleSearch(searchTextField.getText()));
        bookTitleList.updateUI();
    }

    /**
     * Action for the CLEAR button.
     */
    private void clearAction(ActionEvent e) {
        // TODO Add your code here...
        resetToAll();
    }

    /**
     * Action for the ADD button.
     */
    private void addAction(ActionEvent e) {
        // TODO Add your code here...
        AddBookDialog addBookDialog = new AddBookDialog(this);
        addBookDialog.setVisible(true);
    }

    /**
     * Action for the DETAIL button.
     */
    private void detailAction(ActionEvent e) {
        // TODO Add your code here...
        if (!bookTitleList.isSelectionEmpty()) {
            UpdateBookDialog updateBookDialog = new UpdateBookDialog(this);
            updateBookDialog.showBook(bookStorage.getByTitle(bookTitleList.getSelectedValue()));
            updateBookDialog.setVisible(true);
        }
    }

    /**
     * Action for the REMOVE button.
     */
    private void removeAction(ActionEvent e) {
        if (!bookTitleList.isSelectionEmpty()) {
            bookStorage.remove(bookTitleList.getSelectedValue());
            JOptionPane.showMessageDialog(this, "Remove Successful!");
            resetToAll();
        }
    }


    public static void main(String[] args) {
        BookStorage bookStore = new BookStorage();
        bookStore.initBooks();
        BookListWindow bookListWindow = new BookListWindow(bookStore);
        bookListWindow.setVisible(true);
    }
}
