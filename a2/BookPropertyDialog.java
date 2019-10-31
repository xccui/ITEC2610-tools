import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The dialog to show/save the book's properties.
 */
public abstract class BookPropertyDialog extends JDialog implements ActionListener {

    protected JLabel titleLabel;
    protected JTextField titleTextField;
    protected JLabel authorsLabel;
    protected JTextField authorsTextField;
    protected JLabel pagesLabel;
    protected JTextField pagesTextField;
    protected JLabel categoryLabel;
    protected JComboBox<String> categoryComboBox;
    protected JButton saveButton;
    protected JButton cancelButton;
    protected BookStorage bookCollection;
    protected BookListWindow bookListWindow;

    public BookPropertyDialog(BookListWindow owner, String title) {
        super(owner);
        this.bookListWindow = owner;
        this.setTitle(title);
        this.bookCollection = owner.getBookStorage();
        initComponents();
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        titleLabel = new JLabel("    Title:");
        titleTextField = new JTextField();
        authorsLabel = new JLabel("    Authors:");
        authorsTextField = new JTextField();
        pagesLabel = new JLabel("    Pages:");
        pagesTextField = new JTextField();
        categoryLabel = new JLabel("    Category:");
        categoryComboBox = new JComboBox<>();
        saveButton = new JButton("SAVE");
        cancelButton = new JButton("CANCEL");

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(5, 2));

        contentPane.add(titleLabel);
        contentPane.add(titleTextField);

        contentPane.add(authorsLabel);
        contentPane.add(authorsTextField);

        contentPane.add(pagesLabel);
        contentPane.add(pagesTextField);

        contentPane.add(categoryLabel);
        categoryComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                Book.BookCategory.Programming.name(),
                Book.BookCategory.Database.name(),
                Book.BookCategory.Design.name()
        }));

        contentPane.add(categoryComboBox);
        contentPane.add(saveButton);
        contentPane.add(cancelButton);
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        this.pack();
        this.setLocationRelativeTo(this.getOwner());
        this.setModal(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            // Action for the SAVE button
            // TODO Add your code here...
            // Handle the NumberFormatException caused by Integer.valueOf().
            {
                Book book = new Book(
                        titleTextField.getText(),
                        authorsTextField.getText(),
                        Integer.valueOf(pagesTextField.getText()),
                        Book.BookCategory.valueOf(Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString()));
                doSave(book);
            }

            JOptionPane.showMessageDialog(this, "Save successful!");
            bookListWindow.resetToAll();
            this.dispose();
        } else if (e.getSource() == cancelButton) {
            // Action for the CANCEL button
            this.dispose();
        }
    }

    /**
     * Do the actual save job here.
     */
    protected abstract void doSave(Book book);

}
