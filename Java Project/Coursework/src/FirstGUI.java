import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
//import java.awt.Component;
import java.awt.Dimension;


public class FirstGUI extends JFrame {
	
	// Declaring all attributes
	private JPanel contentPane;
	private JComboBox<User> cbLogin;
    private UserAccountsStorage userAccountsStorage;
    private JTable tblAdmin;
    private DefaultTableModel dtmAdmin;
    private BookStorage bookStorage;
    private JComboBox<String> cbBookType;
    private JComboBox<String> cbLanguage;
    private JComboBox<String> cbGenre;
    private JTable tblCustomerView;
    private DefaultTableModel dtmCustomer;
    private ShoppingBasket basket;
    private JTable tblShoppingBasket;
    private DefaultTableModel dtmShoppingBasket;
    private JTextField txtSearchByBarcode;
    private JTable tblSearchByBarcode;
    private Book foundBook;
    private DefaultTableModel dtmSearchByBarcode;
    private JTextField txtFilterAudioBooks;
    private JTable table;
    private DefaultTableModel dtmFilteredAudioBooks;
    private User user1;
    private JTextField txtBarcode;
    private JTextField txtTitle;
    private JTextField txtQuantityInStock;
    private JTextField txtRetailPrice;
    private JTextField txtAdditionalInfo1;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private LocalDate selectedDate;
    private JComboBox<String> cbCondition;
    private JComboBox<String> cbEbookFormat;
    private JComboBox<String> cbAudiobookFormat;
    private JLabel lblBasketTotal;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		    e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstGUI frame = new FirstGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	



	/**
	 * Create the frame.
	 */
	public FirstGUI() {
		
		this.user1=null;
		
	    // Creating a new instance of `ShoppingBasket`. This object will be used to manage the items that the user wants to purchase.
	    basket = new ShoppingBasket();
	    // Creating a new instance of `UserAccountsStorage`. This object will be used to manage user account data.	    
        userAccountsStorage = new UserAccountsStorage();
        // Reading user account data from a file. This is typically done at startup to load existing user account information into the application.
        userAccountsStorage.readUserAccountsFromFile("UserAccounts.txt");
        // Getting all user accounts loaded into the application. It's not clear why this is done here as the `users` variable isn't used elsewhere in this method. 
        List<User> users = userAccountsStorage.getAllUsers();
        
        // Creating a new instance of `BookStorage`. This object will be used to manage book data.
        bookStorage = new BookStorage();
        // Reading book data from a file. This is typically done at startup to load existing book information into the application.
        bookStorage.readBooksFromFile("Stock.txt");
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1157, 869);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 1145, 771);
		contentPane.add(tabbedPane);

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		tabbedPane.addTab("User Login", null, panel, null);
		panel.setLayout(null);
		
		
		cbLogin = new JComboBox<User>(new DefaultComboBoxModel<User>(users.toArray(new User[0])));
        cbLogin.setBounds(335, 297, 258, 27);
		cbLogin.setRenderer(new UserRenderer());
        panel.add(cbLogin);
		
        /*This block of code is defining the action for the "Login" button. When the button is clicked,
         * the selected user is retrieved from a dropdown menu (cbLogin). Depending on whether the user
         * is an Admin or a Customer, different tabs of the interface are enabled and the view is updated
         * accordingly. The login tab is then disabled.
         * */
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User selectedUser = (User) cbLogin.getSelectedItem();
                user1=selectedUser;
                if (selectedUser instanceof Admin) {
                    tabbedPane.setEnabledAt(1, true); // Enable Admin Menu tab
                    tabbedPane.setSelectedIndex(1);
                    updateAdminTable(); // Load the table data automatically

                    //tabbedPane.setSelectedIndex(1);
                } else if (selectedUser instanceof Customer) {
                    //tabbedPane.setSelectedIndex(2);
                    tabbedPane.setEnabledAt(2, true); // Enable Customer Menu tab
                    tabbedPane.setSelectedIndex(2);
                    updateCustomerViewTable();
                }
                tabbedPane.setEnabledAt(0, false); // Disable User Login tab

            }
        });
        btnLogin.setBounds(775, 297, 117, 29);
        panel.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Select User:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(255, 302, 106, 16);
		panel.add(lblNewLabel);
		
		JLabel lblToLoginTo = new JLabel("To login to this Bookshop system, select a user and click \"Login\".");
		lblToLoginTo.setFont(new Font("Arial", Font.BOLD, 20));
		lblToLoginTo.setBounds(255, 233, 637, 29);
		panel.add(lblToLoginTo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		tabbedPane.addTab("Admin Menu", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnPickReleaseDate = new JButton("Pick Release Date");
		btnPickReleaseDate.setBounds(218, 507, 208, 29);
		panel_1.add(btnPickReleaseDate);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 62, 1037, 300);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel_1.add(scrollPane_1);
		
		tblAdmin = new JTable();
		scrollPane_1.setViewportView(tblAdmin);
		tblAdmin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		

		// Set grid color and line width
		tblAdmin.setGridColor(Color.BLACK);
		tblAdmin.setShowGrid(true);
		tblAdmin.setShowHorizontalLines(true);
		tblAdmin.setShowVerticalLines(true);
		tblAdmin.setIntercellSpacing(new Dimension(1, 1));

		// Custom header renderer to display grid lines
		class CustomTableHeaderRenderer extends DefaultTableCellRenderer {
		    public CustomTableHeaderRenderer() {
		        setHorizontalAlignment(SwingConstants.LEFT); // Change alignment to left
		        setOpaque(true);
		        setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        setBackground(Color.LIGHT_GRAY);
		    }
		}

		// Set the custom renderer for the column headers
		JTableHeader header = tblAdmin.getTableHeader();
		header.setDefaultRenderer(new CustomTableHeaderRenderer());
		
		
		dtmAdmin= new DefaultTableModel();
		dtmAdmin.setColumnIdentifiers(new Object[] {"Barcode","Title","Language","Genre","Release Date",
				"Quanitiy","Price","Book Type","Condition","Pages","Ebook Format","Audio Length","Audio Format"});
		tblAdmin.setModel(dtmAdmin);
		
		cbBookType = new JComboBox<String>();
        cbBookType.setModel(new DefaultComboBoxModel<>(new String[]{"ebook", "paperback", "audiobook"}));
		cbBookType.setBounds(218, 406, 208, 66);
		panel_1.add(cbBookType);
		
		JLabel btnBookType = new JLabel("Select book type");
		btnBookType.setBounds(40, 430, 163, 16);
		panel_1.add(btnBookType);
		
		JLabel lblAdditionalInfo2 = new JLabel("Format");
		lblAdditionalInfo2.setName("lblAdditionalInfo2");
		lblAdditionalInfo2.setBounds(40, 680, 121, 16);
		panel_1.add(lblAdditionalInfo2);
		
		JLabel lblAdditionalInfo1 = new JLabel("Number of Pages");
		lblAdditionalInfo1.setName("lblAdditionalInfo1");
		lblAdditionalInfo1.setBounds(40, 652, 121, 16);
		panel_1.add(lblAdditionalInfo1);
		
		cbLanguage = new JComboBox<String>();
		cbLanguage.setModel(new DefaultComboBoxModel<>(new String[]{"English", "French"}));
		cbLanguage.setBounds(218, 441, 208, 48);
		panel_1.add(cbLanguage);
		
		cbGenre = new JComboBox<String>();
		cbGenre.setModel(new DefaultComboBoxModel<>(new String[]{"Politics", "Business", "Computer Science", "Biography"}));
		cbGenre.setBounds(218, 469, 208, 48);
		panel_1.add(cbGenre);
		
		cbCondition = new JComboBox<String>();
		cbCondition.setModel(new DefaultComboBoxModel<String>(new String[]{"new", "used"}));
		cbCondition.setBounds(218, 665, 208, 48);
		panel_1.add(cbCondition);
		cbCondition.setVisible(false);

		cbEbookFormat = new JComboBox<String>();
		cbEbookFormat.setModel(new DefaultComboBoxModel<String>(new String[]{"EPUB", "MOBI", "PDF"}));
		cbEbookFormat.setBounds(218, 665, 208, 48);
		panel_1.add(cbEbookFormat);
		cbEbookFormat.setVisible(true);

		cbAudiobookFormat = new JComboBox<String>();
		cbAudiobookFormat.setModel(new DefaultComboBoxModel<String>(new String[]{"MP3", "WMA", "AAC"}));
		cbAudiobookFormat.setBounds(218, 665, 208, 48);
		panel_1.add(cbAudiobookFormat);
		cbAudiobookFormat.setVisible(false);

		
		btnPickReleaseDate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        DatePickerDialog datePickerDialog = new DatePickerDialog(FirstGUI.this);
		        datePickerDialog.setVisible(true);
		        selectedDate = datePickerDialog.getSelectedDate();
		        java.sql.Date sqlDate = null;

		        if (selectedDate != null) {
		            btnPickReleaseDate.setText(selectedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		        }
		    }
		});
		cbBookType.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String bookType = (String) cbBookType.getSelectedItem();
		        switch (bookType.toLowerCase()) {
		            case "paperback":
		                lblAdditionalInfo1.setText("Number of Pages");
		                lblAdditionalInfo2.setText("Condition");
		                cbCondition.setVisible(true);
		                cbEbookFormat.setVisible(false);
		                cbAudiobookFormat.setVisible(false);
		                break;
		            case "ebook":
		                lblAdditionalInfo1.setText("Number of Pages");
		                lblAdditionalInfo2.setText("Format");
		                cbCondition.setVisible(false);
		                cbEbookFormat.setVisible(true);
		                cbAudiobookFormat.setVisible(false);
		                break;
		            case "audiobook":
		                lblAdditionalInfo1.setText("Length");
		                lblAdditionalInfo2.setText("Format");
		                cbCondition.setVisible(false);
		                cbEbookFormat.setVisible(false);
		                cbAudiobookFormat.setVisible(true);
		                break;
		        }
		    }
		});
		
		/*This block of code is defining the action for the "Add Book" button. When the button is clicked,
		 * it retrieves all the input values from the GUI form. There are several validation checks 
		 * performed to ensure that all the fields are filled and the inputs are of the correct type. 
		 * If everything is valid, it checks whether a book with the same barcode already exists. 
		 * If it does, it updates the quantity. If not, it creates a new book and adds it to the storage. 
		 * All the books in the storage are then saved to a file, and the admin table is updated. 
		 * Finally, it resets all the input fields for the next operation.
		 * */
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the book type from the JComboBox
		        String bookType = (String) cbBookType.getSelectedItem();
		        
		        // Get the input values from the individual JTextFields
		        String barcodeText = txtBarcode.getText().trim();
		        String title = txtTitle.getText().trim();
		        String language = (String) cbLanguage.getSelectedItem();
		        String genre = (String) cbGenre.getSelectedItem();
		        String releaseDate = btnPickReleaseDate.getText();
		        String quantityInStockText = txtQuantityInStock.getText().trim();
		        String retailPriceText = txtRetailPrice.getText().trim();
		        String additionalInfo1 = txtAdditionalInfo1.getText().trim();
		        String additionalInfo2;
		        if (cbCondition.isVisible()) {
		            additionalInfo2 = (String) cbCondition.getSelectedItem();
		        } else if (cbEbookFormat.isVisible()) {
		            additionalInfo2 = (String) cbEbookFormat.getSelectedItem();
		        } else {
		            additionalInfo2 = (String) cbAudiobookFormat.getSelectedItem();
		        }
		        
		        // Check if any required field is empty
		        if (isAnyFieldEmpty(barcodeText, title, language, genre, releaseDate, quantityInStockText, retailPriceText, additionalInfo1, additionalInfo2)|| "Pick Release Date".equals(releaseDate)) {
		            JOptionPane.showMessageDialog(FirstGUI.this,
		                    "One or more required fields are empty. Please fill in all the fields and try again.",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        //String barcode;
		        int quantityInStock;
		        double retailPrice;    
		        
		     // Check if barcodeText is numeric and 8 digits long
		        if (!barcodeText.matches("\\d{8}")) {
		            JOptionPane.showMessageDialog(FirstGUI.this,
		                    "Invalid barcode. The barcode should be exactly 8 digits and numeric.",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		                
		        String barcode = barcodeText;

		        try {
		            quantityInStock = Integer.parseInt(quantityInStockText);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(FirstGUI.this,
		                    "Invalid input for quantity in stock. Please enter a valid number and try again.",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            retailPrice = Double.parseDouble(retailPriceText);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(FirstGUI.this,
		                    "Invalid input for retail price. Please enter a valid number and try again.",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		        	boolean isValidAdditionalInfo1 = false;
		            try {
		                if ("paperback".equalsIgnoreCase(bookType) || "ebook".equalsIgnoreCase(bookType)) {
		                    int additionalInfo1AsInt = Integer.parseInt(additionalInfo1);
		                    isValidAdditionalInfo1 = true;
		                } else if ("audiobook".equalsIgnoreCase(bookType)) {
		                    double additionalInfo1AsDouble = Double.parseDouble(additionalInfo1);
		                    isValidAdditionalInfo1 = true;
		                }
		            } catch (NumberFormatException ex) {
		                String errorMessage;
		                if ("paperback".equalsIgnoreCase(bookType) || "ebook".equalsIgnoreCase(bookType)) {
		                    errorMessage = "Error: Invalid input for Number of Pages. Please enter a valid integer and try again.";
		                } else { // audiobook
		                    errorMessage = "Error: Invalid input for length. Please enter a valid double and try again.";
		                }
		                JOptionPane.showMessageDialog(FirstGUI.this,
		                    errorMessage,
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            java.sql.Date sqlReleaseDate = java.sql.Date.valueOf(selectedDate);
		            
		            // Check if book exists and update the quantity
		            boolean bookUpdated = bookStorage.updateQuantityAdmin(barcode, quantityInStock);
		            if (bookUpdated) {
		                JOptionPane.showMessageDialog(FirstGUI.this,
		                        "Book already exists. Updated the quantity.",
		                        "Success",
		                        JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                // Add new book
		            	Book newBook = null;
		            	//try {
		            	    newBook = bookStorage.addNewBook(
		            	        bookType.toLowerCase(),
		            	        barcode,
		            	        title,
		            	        language,
		            	        genre,
		            	        dateFormat.format(sqlReleaseDate),
		            	        quantityInStock,
		            	        retailPrice,
		            	        "paperback".equalsIgnoreCase(bookType) || "ebook".equalsIgnoreCase(bookType) ? Integer.parseInt(additionalInfo1) : Double.parseDouble(additionalInfo1),
		            	        additionalInfo2
		            	    );

		                if (newBook != null) {
		                    bookStorage.saveBookToFile(newBook, "Stock.txt");
		                    JOptionPane.showMessageDialog(FirstGUI.this,
		                            "New book added successfully.",
		                            "Success",
		                            JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(FirstGUI.this,
		                            "Error adding the new book.",
		                            "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                }
		            }

		            // Save the updated stock to file
		            bookStorage.saveAllBooksToFile("Stock.txt");
		            
		         // Update the table after adding the book or updating the quantity
		            updateAdminTable();

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(FirstGUI.this,
		                    "Invalid input for barcode, quantity, price, number of pages, or length. Please enter valid numbers and try again.",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		                    }
		      //Clear the input fields after the operation is completed
		        txtBarcode.setText("");
		        txtTitle.setText("");
		        txtQuantityInStock.setText("");
		        txtRetailPrice.setText("");
		        txtAdditionalInfo1.setText("");
		        btnPickReleaseDate.setText("Pick Release Date"); // Reset the release date button

		        }
		        });

		btnAddBook.setBounds(939, 392, 138, 29);
		panel_1.add(btnAddBook);
		
		/*This block of code is defining the action for the "Log Out" button in the admin menu. When the 
		 * button is clicked, it disables the admin tab and switches back to the login tab. It also resets 
		 * all the input fields to their default state.
		 * */
		JButton btnAdminLogOut = new JButton("Log Out");
		btnAdminLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(1, false); // Disable Admin Menu tab
		        tabbedPane.setSelectedIndex(0); // Go back to User Login tab
		        txtBarcode.setText("");
		        txtTitle.setText("");
		        txtQuantityInStock.setText("");
		        txtRetailPrice.setText("");
		        txtAdditionalInfo1.setText("");
		        btnPickReleaseDate.setText("Pick Release Date");

			}
		});
		btnAdminLogOut.setBounds(960, 28, 117, 29);
		panel_1.add(btnAdminLogOut);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setBounds(40, 568, 121, 16);
		panel_1.add(lblBarcode);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(40, 540, 121, 16);
		panel_1.add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(40, 456, 121, 16);
		panel_1.add(lblLanguage);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(40, 484, 121, 16);
		panel_1.add(lblGenre);
		
		JLabel lblReleaseDate = new JLabel("Release Date (dd-mm-yyyy)");
		lblReleaseDate.setBounds(40, 512, 182, 16);
		panel_1.add(lblReleaseDate);
		
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(218, 563, 208, 26);
		panel_1.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(218, 535, 208, 26);
		panel_1.add(txtTitle);
		
		JLabel lblQuantity = new JLabel("Quantity in Stock");
		lblQuantity.setBounds(40, 596, 121, 16);
		panel_1.add(lblQuantity);
		

		JLabel lblRetailPrice = new JLabel("Retail Price");
		lblRetailPrice.setBounds(40, 624, 121, 16);
		panel_1.add(lblRetailPrice);
		
		
		txtQuantityInStock = new JTextField();
		txtQuantityInStock.setColumns(10);
		txtQuantityInStock.setBounds(218, 591, 208, 26);
		panel_1.add(txtQuantityInStock);
		
		txtRetailPrice = new JTextField();
		txtRetailPrice.setColumns(10);
		txtRetailPrice.setBounds(218, 619, 208, 26);
		panel_1.add(txtRetailPrice);
		
		txtAdditionalInfo1 = new JTextField();
		txtAdditionalInfo1.setColumns(10);
		txtAdditionalInfo1.setBounds(218, 647, 208, 26);
		panel_1.add(txtAdditionalInfo1);
		
		JLabel lblViewBooksSorted_2 = new JLabel("View books sorted by quantities in ascending order");
		lblViewBooksSorted_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblViewBooksSorted_2.setBounds(40, 25, 664, 29);
		panel_1.add(lblViewBooksSorted_2);
		
		JLabel lblToAddA = new JLabel("To add a book, enter the below fields and click \"Add Book\".");
		lblToAddA.setFont(new Font("Arial", Font.BOLD, 18));
		lblToAddA.setBounds(40, 389, 689, 29);
		panel_1.add(lblToAddA);


		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		tabbedPane.addTab("Customer Menu", null, panel_2, null);
		panel_2.setLayout(null);
		
		tabbedPane.setEnabledAt(1, false); // Disable Admin Menu tab
		tabbedPane.setEnabledAt(2, false); // Disable Customer Menu tab

		
		tblCustomerView = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblCustomerView);
		
		// Set grid color and line width
		tblCustomerView.setGridColor(Color.BLACK);
		tblCustomerView.setShowGrid(true);
		tblCustomerView.setShowHorizontalLines(true);
		tblCustomerView.setShowVerticalLines(true);
		tblCustomerView.setIntercellSpacing(new Dimension(1, 1));


		// Set the custom renderer for the column headers
		JTableHeader header1 = tblCustomerView.getTableHeader();
		header1.setDefaultRenderer(new CustomTableHeaderRenderer());
		
		scrollPane.setBounds(30, 84, 1064, 181);
		//these three to set horizontal and vertical scrollbars
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblCustomerView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel_2.add(scrollPane);
		
		// Add basket total label
		lblBasketTotal = new JLabel("Basket Total: £0.0");
		lblBasketTotal.setBounds(947, 581, 147, 26);
		lblBasketTotal.setFont(new Font("Arial", Font.BOLD, 15));
		panel_2.add(lblBasketTotal);

		dtmCustomer= new DefaultTableModel();
		dtmCustomer.setColumnIdentifiers(new Object[] {"Barcode","Title","Language","Genre","Release Date",
				"Quanitiy","Price","Book Type","Condition","Pages","Ebook Format","Audio Length","Audio Format"});
		tblCustomerView.setModel(dtmCustomer);
		setColumnWidthsCustomer();
		
		/*This block of code is defining the action for the "Add Book to Basket" button. When the button 
		 * is clicked, it gets the selected row from the customer view table and retrieves the book with 
		 * the corresponding barcode. If the book is found and there is quantity available, it adds the book 
		 * to the shopping basket and updates the balance, total, and shopping basket table. If the book is 
		 * not found or out of stock, or if no book is selected, it shows an error message.
		 * */
		JButton btnAddBookBarcode = new JButton("Add Book to Basket");
		btnAddBookBarcode.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tblCustomerView.getSelectedRow();
		        if (selectedRow >= 0) {
		            String barcode = (String) tblCustomerView.getValueAt(selectedRow, 0);
		            Book book1 = bookStorage.getBook(barcode);
		            if (book1 != null && book1.getQuantity() > 0) {
		                basket.addItem(book1);
		                JOptionPane.showMessageDialog(FirstGUI.this,
		                        "Book added to your basket: " + book1.getTitle(),
		                        "Success",
		                        JOptionPane.INFORMATION_MESSAGE);
		                updateBalanceAndTotalLabels();
		                updateShoppingBasketTable(); // Update the shopping basket table
		            } else {
		                JOptionPane.showMessageDialog(FirstGUI.this,
		                        "Book not found or out of stock.",
		                        "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(FirstGUI.this,
		                    "Please select a book to add to the basket.",
		                    "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		tblShoppingBasket = new JTable();
		JScrollPane scrollPaneShoppingBasket = new JScrollPane(tblShoppingBasket);
		scrollPaneShoppingBasket.setBounds(165, 614, 929, 94);
		panel_2.add(scrollPaneShoppingBasket);
		
		// Set grid color and line width
		tblShoppingBasket.setGridColor(Color.BLACK);
		tblShoppingBasket.setShowGrid(true);
		tblShoppingBasket.setShowHorizontalLines(true);
		tblShoppingBasket.setShowVerticalLines(true);
		tblShoppingBasket.setIntercellSpacing(new Dimension(1, 1));
		
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblShoppingBasket.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel_2.add(scrollPane);


		// Set the custom renderer for the column headers
		JTableHeader header2 = tblShoppingBasket.getTableHeader();
		header2.setDefaultRenderer(new CustomTableHeaderRenderer());

		dtmShoppingBasket = new DefaultTableModel();
		dtmShoppingBasket.setColumnIdentifiers(new Object[]{"Barcode","Title","Language","Genre","Release Date","Price","Book Type","Condition","Pages","Ebook Format","Audio Length","Audio Format"});
		tblShoppingBasket.setModel(dtmShoppingBasket);

		
		btnAddBookBarcode.setBounds(932, 51, 162, 29);
		panel_2.add(btnAddBookBarcode);
		
		JButton btnViewBasket = new JButton("View Basket");
		btnViewBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basket.getItems().isEmpty()) {
		            // Show an error message if the basket is empty
		            JOptionPane.showMessageDialog(null, "Shopping basket is empty.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		        	updateShoppingBasketTable();
		        }

			}
		});
		btnViewBasket.setBounds(30, 614, 123, 29);
		panel_2.add(btnViewBasket);
		/*This block of code creates an "Empty Basket" button. When clicked, if the user's 
		 * shopping basket is not empty, it will clear out all items from the basket and 
		 * update the display accordingly. If the basket is already empty, it will display 
		 * an error message.*/
		JButton btnEmptyBasket = new JButton("Empty Basket");
		btnEmptyBasket.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (basket.getItems().isEmpty()) {
		            // Show an error message if the basket is empty
		            JOptionPane.showMessageDialog(null, "Your shopping basket is already empty.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Empty the basket
		            basket.emptyBasket();
		            updateBalanceAndTotalLabels();

		            // Clear the table
		            dtmShoppingBasket.setRowCount(0);

		            // Show a success message
		            JOptionPane.showMessageDialog(null, "Your shopping basket has been emptied.", "Success", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});

		btnEmptyBasket.setBounds(30, 649, 123, 29);
		panel_2.add(btnEmptyBasket);
		
		JLabel lblSearchByBarcode = new JLabel("Enter the barcode and click \"Search\", if you want to add the found book to basket, click \"Add Book to Basket\".");
		lblSearchByBarcode.setBounds(291, 285, 946, 16);
		panel_2.add(lblSearchByBarcode);
		
		txtSearchByBarcode = new JTextField();
		txtSearchByBarcode.setColumns(10);
		txtSearchByBarcode.setBounds(156, 313, 123, 26);
		panel_2.add(txtSearchByBarcode);
		
		tblSearchByBarcode = new JTable();
		JScrollPane scrollPaneSearchByBarcode = new JScrollPane(tblSearchByBarcode);
		scrollPaneSearchByBarcode.setBounds(30, 343, 1064, 59);
		panel_2.add(scrollPaneSearchByBarcode);
		setColumnWidthsBasket();
		
		// Set grid color and line width
		tblSearchByBarcode.setGridColor(Color.BLACK);
		tblSearchByBarcode.setShowGrid(true);
		tblSearchByBarcode.setShowHorizontalLines(true);
		tblSearchByBarcode.setShowVerticalLines(true);
		tblSearchByBarcode.setIntercellSpacing(new Dimension(1, 1));


		// Set the custom renderer for the column headers
		JTableHeader header3 = tblSearchByBarcode.getTableHeader();
		header3.setDefaultRenderer(new CustomTableHeaderRenderer());

		
		dtmSearchByBarcode = new DefaultTableModel();
		dtmSearchByBarcode.setColumnIdentifiers(new Object[]{"Barcode","Title","Language","Genre","Release Date","Quanitiy","Price","Book Type","Condition","Pages","Ebook Format","Audio Length","Audio Format"});

		tblSearchByBarcode.setModel(dtmSearchByBarcode);
		setColumnWidthsCustomerBarcodeSearch();
		//these three to set horizontal and vertical scrollbars
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblSearchByBarcode.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel_2.add(scrollPane);
		
		/*This block creates an "Add Book to Basket" button. When clicked, if the book is 
		 * available (i.e., it exists and is in stock), it will add the book to the user's 
		 * shopping basket and update the display. If the book is unavailable, it will display 
		 * an error message.*/
		JButton btnAddBookByBarcode = new JButton("Add Book to Basket");
		btnAddBookByBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (foundBook != null && foundBook.getQuantity() > 0) {
			            basket.addItem(foundBook);
			            JOptionPane.showMessageDialog(null, "Book added to your basket: " + foundBook.getTitle(), "Success", JOptionPane.INFORMATION_MESSAGE);
			            updateBalanceAndTotalLabels();
		                updateShoppingBasketTable(); // Update the shopping basket table

			        } else {
			            JOptionPane.showMessageDialog(null, "Book not found or out of stock.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				
			}
		});
		btnAddBookByBarcode.setBounds(932, 313, 162, 29);
		panel_2.add(btnAddBookByBarcode);
		
		/*This block creates a "Search Book" button. When clicked, it will search for a 
		 * book in the store's inventory using a given barcode. If a book is found with 
		 * the given barcode, it will update the display accordingly. If the entered barcode 
		 * is invalid or no book is found with the given barcode, it will display an error 
		 * message.*/
		
		JButton btnSearchBookByBarcode = new JButton("Search Book");
		btnSearchBookByBarcode.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String barcodeText = txtSearchByBarcode.getText().trim();
		        if (barcodeText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter a barcode.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Check if barcodeText is numeric and 8 digits long
		            if (!barcodeText.matches("\\d{8}")) {
		                JOptionPane.showMessageDialog(null, 
		                    "Invalid barcode. Barcode should be exactly 8 digits and numeric.", 
		                    "Error", 
		                    JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            
		            String barcode = barcodeText;
		            foundBook = bookStorage.getBook(barcode);
		            if (foundBook != null) {
		                updateSearchByBarcodeTable(foundBook);
		            } else {
		                JOptionPane.showMessageDialog(null, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		            txtSearchByBarcode.setText("");
		        }
		    }
		});
		
		
		btnSearchBookByBarcode.setBounds(287, 313, 187, 29);
		panel_2.add(btnSearchBookByBarcode);
		//setColumnWidthsAudioBook();
		//setColumnWidths();
		table = new JTable();
		
		// Set grid color and line width
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setIntercellSpacing(new Dimension(1, 1));


		// Set the custom renderer for the column headers
		JTableHeader header4 = table.getTableHeader();
		header4.setDefaultRenderer(new CustomTableHeaderRenderer());

		

		dtmFilteredAudioBooks = new DefaultTableModel();
		dtmFilteredAudioBooks.setColumnIdentifiers(new Object[]{"Barcode","Title","Language","Genre","Release Date","Quanitiy","Price","Audio Length","Audio Format"});

		table.setModel(dtmFilteredAudioBooks);

		JScrollPane scrollPaneFilteredAudioBooks = new JScrollPane(table);
		scrollPaneFilteredAudioBooks.setBounds(30, 499, 1064, 65);
		panel_2.add(scrollPaneFilteredAudioBooks);
		
		txtFilterAudioBooks = new JTextField();
		txtFilterAudioBooks.setBounds(154, 466, 130, 26);
		panel_2.add(txtFilterAudioBooks);
		txtFilterAudioBooks.setColumns(10);
		
		JLabel lblFilterAudioBooks = new JLabel("Number of hours:");
		lblFilterAudioBooks.setBounds(30, 471, 198, 16);
		panel_2.add(lblFilterAudioBooks);
		
		/*This block creates a "Filter Audio Books" button. When clicked, it will display 
		 * all audio books with a length greater than a user-specified minimum length. If 
		 * no audio books are found with a length greater than the specified minimum length
		 *  or if the input is invalid, it will display an error message.*/
		JButton btnFilterAudioBooks = new JButton("Filter Audio Books");
		btnFilterAudioBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		            double minLength = Double.parseDouble(txtFilterAudioBooks.getText());
		            List<AudioBook> audioBooks = bookStorage.getAudioBooksByLength(minLength);
		            if (audioBooks.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "No books found, please enter a lesser number of hours.", "Error", JOptionPane.ERROR_MESSAGE);
		            } else {
		                updateFilteredAudioBooksTable(audioBooks);
		            }
		            //updateFilteredAudioBooksTable(audioBooks);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number and try again.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        txtFilterAudioBooks.setText("");
			}
		});
		
		btnFilterAudioBooks.setBounds(296, 466, 178, 29);
		panel_2.add(btnFilterAudioBooks);
		

		/*This block creates an "Add Audio Book to Basket" button. When clicked, if a book 
		 * is selected and available, it will add the selected audio book to the user's shopping 
		 * basket and update the display. If no book is selected or the selected book is unavailable, 
		 * it will display an error message.*/
		JButton btnAddAudioBooks = new JButton("Add Audio Book to Basket");
		btnAddAudioBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            String barcode = (String) dtmFilteredAudioBooks.getValueAt(selectedRow, 0);
		            Book book = bookStorage.getBook(barcode);
		            if (book != null && book.getQuantity() > 0) {
		                basket.addItem(book);
		                JOptionPane.showMessageDialog(null, "Audio book added to your basket: " + book.getTitle(), "Success", JOptionPane.INFORMATION_MESSAGE);
		                updateBalanceAndTotalLabels();
		                updateShoppingBasketTable(); // Update the shopping basket table
		            } else {
		                JOptionPane.showMessageDialog(null, "Audio book not found or out of stock.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select an audio book to add to the basket.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnAddAudioBooks.setBounds(887, 466, 207, 29);
		panel_2.add(btnAddAudioBooks);
		
		/*This block creates a "Checkout" button. When clicked, if the user's shopping basket 
		 * is not empty and all items are in stock, it will proceed with the checkout process. 
		 * This includes checking whether the user has enough credit, updating the stock levels 
		 * of purchased items, adjusting the user's credit balance, and clearing out the 
		 * shopping basket. If the basket is empty, any item is out of stock, or the user doesn't 
		 * have enough credit, it will display an appropriate error message.*/
		JButton btnCheckoutBasket = new JButton("Checkout");
		
		btnCheckoutBasket.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (basket.getItems().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Your shopping basket is empty. Please add items to your basket before proceeding to checkout.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            boolean anyBookOutOfStock = false;
		            
		            // Check if all the books in the basket are in stock
		            for (Book basketBook : basket.getItems()) {
		                Book bookInStock = bookStorage.getBook(basketBook.getBarcode());
		                int basketQuantity = basket.getQuantity(basketBook); // Get the quantity of the book in the basket
		                if (bookInStock == null || bookInStock.getQuantity() < basketQuantity) { // Check if stock quantity is less than basket quantity
		                    //allBooksInStock = false;
		                	anyBookOutOfStock = true;
		                    //JOptionPane.showMessageDialog(null, "The book " + basketBook.getTitle() + " has insufficient stock. Please update the quantity in your basket.", "Error", JOptionPane.ERROR_MESSAGE);
		                    //break;
		                }
		            }
		            if (!anyBookOutOfStock) {
		            //if (allBooksInStock) {
		                double totalPrice = basket.getTotalPrice();
		                Customer customer = (Customer) user1;

		                if (customer.getCreditBalance() >= totalPrice) {
		                    // Update stock levels and save to Stock.txt
		                    for (Book purchasedBook : basket.getItems()) {
		                    	bookStorage.updateQuantity(purchasedBook.getBarcode(), 1);
		                        //bookStorage.updateQuantity(purchasedBook.getBarcode(), 1);
		                    }
		                    bookStorage.saveAllBooksToFile("Stock.txt");
		                    
		                    // Call updateCustomerViewTable() to refresh the book list
		                    updateCustomerViewTable();

		                    // Adjust credit balance and save to UserAccounts.txt
		                    double remainingCredit = customer.getCreditBalance() - totalPrice;
		                    userAccountsStorage.updateUserCredit(Integer.parseInt(customer.getUserID()), remainingCredit);

		                    // Empty the shopping basket
		                    basket.emptyBasket();
		                    
		    		        clearCustomerFields(); // Clear tables in the customer pane


		                    // Display receipt
		                    JOptionPane.showMessageDialog(null, String.format("Thank you for the purchase! £%.2f paid and your remaining credit balance is £%.2f. Your delivery address is %s.", totalPrice, remainingCredit, customer.getAddr()), "Success", JOptionPane.INFORMATION_MESSAGE);
		                    lblBasketTotal.setText("Basket Total: £0.0");
		                } else {
		                    JOptionPane.showMessageDialog(null, "You don't have enough credit to pay for the items in your basket.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            else {
		                JOptionPane.showMessageDialog(null, "Some or all of the books in your basket have insufficient stock. Please update the quantity in your basket.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		btnCheckoutBasket.setBounds(30, 679, 123, 29);
		panel_2.add(btnCheckoutBasket);
		
		/*This block creates a "Log Out" button. When clicked, it will clear out the user's 
		 * shopping basket, reset the display, disable the customer menu, and navigate back 
		 * to the user login screen.*/
		JButton btnCustomerLogOut = new JButton("Log Out");
		btnCustomerLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        basket.emptyBasket();
		        clearCustomerFields(); // Clear tables in the customer pane
				tabbedPane.setEnabledAt(2, false); // Disable Customer Menu tab
		        tabbedPane.setSelectedIndex(0); // Go back to User Login tab
			}
		});
		btnCustomerLogOut.setBounds(977, 8, 117, 29);
		panel_2.add(btnCustomerLogOut);
		
		JLabel lblNewLabel_5 = new JLabel("To add one/ more books to basket, select one book at a time from below and click \"Add Book to Basket\".");
		lblNewLabel_5.setBounds(30, 56, 671, 16);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Barcode: ");
		lblNewLabel_6.setBounds(30, 318, 61, 13);
		panel_2.add(lblNewLabel_6);
		JTextArea lblNewLabel_7 = new JTextArea("Enter number of hours and click \"Filter\", if you want to add one/ more books to basket, select one book at a time and click \"Add Audio Book to Basket\".");
		lblNewLabel_7.setBounds(32, 443, 1073, 16);
		lblNewLabel_7.setWrapStyleWord(true);
		lblNewLabel_7.setLineWrap(true);
		lblNewLabel_7.setEditable(false);
		lblNewLabel_7.setBackground(null);
		panel_2.add(lblNewLabel_7);
		

		
		JTextArea lblNewLabel_8 = new JTextArea("To Empty or Checkout Basket, click on the respective buttons beside it. Each row displays a single quantity.");
		lblNewLabel_8.setWrapStyleWord(true);
		lblNewLabel_8.setLineWrap(true);
		lblNewLabel_8.setEditable(false);
		lblNewLabel_8.setBackground(null);
		lblNewLabel_8.setBounds(218, 586, 692, 26);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblBasketHeader = new JLabel("Shopping Basket");
		lblBasketHeader.setFont(new Font("Arial", Font.BOLD, 18));  // Change the font size to a larger value
		lblBasketHeader.setBounds(30, 578, 256, 29);
		panel_2.add(lblBasketHeader);
		
		JLabel lblFilterAudioBooks_1 = new JLabel("Filter Audio Books by Minimum Length\n");
		lblFilterAudioBooks_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblFilterAudioBooks_1.setBounds(30, 410, 506, 29);
		panel_2.add(lblFilterAudioBooks_1);
		
		JLabel lblSearchBookBy = new JLabel("Search Book by Barcode");
		lblSearchBookBy.setFont(new Font("Arial", Font.BOLD, 18));
		lblSearchBookBy.setBounds(30, 277, 256, 29);
		panel_2.add(lblSearchBookBy);
		
		JLabel lblViewBooksSorted = new JLabel("View books sorted by prices in ascending order");
		lblViewBooksSorted.setFont(new Font("Arial", Font.BOLD, 18));
		lblViewBooksSorted.setBounds(30, 21, 471, 29);
		panel_2.add(lblViewBooksSorted);

		setColumnWidths();
		

		
	}


	//Customer table setting widths
	private void setColumnWidthsCustomer() {
	    int[] widths = {80, 150, 80, 150, 150, 70, 60, 80, 70, 50, 100, 90, 90};
	    for (int i = 0; i < tblCustomerView.getColumnCount(); i++) {
	        tblCustomerView.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
	    }
	}
	
	//Customer Barcode search table setting widths
	private void setColumnWidthsCustomerBarcodeSearch() {
	    int[] widths = {80, 150, 80, 150, 150, 70, 60, 80, 70, 50, 100, 90, 90};
	    for (int i = 0; i < tblSearchByBarcode.getColumnCount(); i++) {
	    	tblSearchByBarcode.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
	    }
	}
	
	//admin table setting widths
	private void setColumnWidths() {
	    int[] widths = {80, 150, 80, 150, 150, 70, 60, 80, 70, 50, 100, 90, 90};
	    for (int i = 0; i < tblAdmin.getColumnCount(); i++) {
	        tblAdmin.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
	    }
	}
	//shopping basket table setting widths
	private void setColumnWidthsBasket() {
	    int[] widths = {80, 150, 80, 150, 150, 60, 80, 70, 50, 100, 90, 90};
	    for (int i = 0; i < tblShoppingBasket.getColumnCount(); i++) {
	        tblShoppingBasket.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
	    }
	}
	
	/*This method updates the shopping basket table display. It iterates through each book 
	 * in the basket and adds its details to the table. Depending on the type of book (Paperback, 
	 * Ebook, or Audiobook), different attributes are included in the table.*/
	private void updateShoppingBasketTable() {
	    // Clear the table
	    dtmShoppingBasket.setRowCount(0);

	    // Add basket items to the table
	    for (Book basketBook : basket.getItems()) {
	        Object[] rowData = new Object[12];
	        rowData[0] = basketBook.getBarcode();
	        rowData[1] = basketBook.getTitle();
	        rowData[2] = basketBook.getLanguage();
	        rowData[3] = basketBook.getGenre();
	        rowData[4] = basketBook.getReleaseDate();
	        rowData[5] = basketBook.getRetailPrice();
	        
	        if (basketBook instanceof PaperBack) {
	            rowData[6] = "Paperback";
	            
	            PaperBack paperBook = (PaperBack) basketBook;
	            
	            rowData[7] = paperBook.getCondition();
	            rowData[8] = paperBook.getNumberOfPages();
	            rowData[9] = "";
	            rowData[10] = "";
	            rowData[11] = "";
	        } else if (basketBook instanceof Ebook) {
	            rowData[6] = "Ebook";
	            Ebook eBook = (Ebook) basketBook;
	            rowData[7] = "";
	            rowData[8] = eBook.getNumberOfPages();
	            rowData[9] = eBook.getFormat();
	            rowData[10] = "";
	            rowData[11] = "";
	        } else if (basketBook instanceof AudioBook) {
	            rowData[6] = "Audiobook";
	            AudioBook audioBook = (AudioBook) basketBook;
	            rowData[7] = "";
	            rowData[8] = "";
	            rowData[9] = "";
	            rowData[10] = audioBook.getListeningLength();
	            rowData[11] = audioBook.getFormat();
	        }
	        
	        dtmShoppingBasket.addRow(rowData);
	    }
	}
	/* This method clears the search table and adds the details of the provided book to 
	 * the table, if it's not null.*/
	private void updateSearchByBarcodeTable(Book book) {
	    dtmSearchByBarcode.setRowCount(0); // Clear the table
	    if (book != null) {
	        dtmSearchByBarcode.addRow(createRowData(book));
	    }
	}
	/*This method updates the admin table by clearing the existing table and adding all 
	 * the books in the store, sorted by quantity, to the table.*/
	private void updateAdminTable() {
	    dtmAdmin.setRowCount(0);
	    List<Book> books = bookStorage.getAllBooksSortedByQuantity(); // Use the sorted books list

	    for (Book book : books) {
	        dtmAdmin.addRow(createRowData(book));
	    }
	}
	/*This method updates the customer view table by clearing the existing table and adding 
	 * all the books in the store, sorted by price, to the table.*/
	private void updateCustomerViewTable() {
	    dtmCustomer.setRowCount(0); // Change this line from dtmAdmin.setRowCount(0);
	    List<Book> books = bookStorage.getAllBooksSortedByPrice(); // Use the sorted books list

	    for (Book book : books) {
	        dtmCustomer.addRow(createRowData(book));
	    }
	}

	/* This method generates a row of book data to be used in a table. It creates an array of 
	 * book attributes, which varies depending on the type of book (Paperback, Ebook, or Audiobook).*/
	private Object[] createRowData(Book book) {
	    Object[] rowData = new Object[13];
	    rowData[0] = book.getBarcode();
	    rowData[1] = book.getTitle();
	    rowData[2] = book.getLanguage();
	    rowData[3] = book.getGenre();
	    rowData[4] = book.getReleaseDate();
	    rowData[5] = book.getQuantity();
	    rowData[6] = book.getRetailPrice();

	    if (book instanceof PaperBack) {
	        rowData[7] = "Paperback";

	        PaperBack paperBook = (PaperBack) book;

	        rowData[8] = paperBook.getCondition();
	        rowData[9] = paperBook.getNumberOfPages();
	        rowData[10] = "";
	        rowData[11] = "";
	        rowData[12] = "";
	    } else if (book instanceof Ebook) {
	        rowData[7] = "Ebook";
	        Ebook eBook = (Ebook) book;
	        rowData[8] = "";
	        rowData[9] = eBook.getNumberOfPages();
	        rowData[10] = eBook.getFormat();
	        rowData[11] = "";
	        rowData[12] = "";
	    } else if (book instanceof AudioBook) {
	        rowData[7] = "Audiobook";
	        AudioBook audioBook = (AudioBook) book;
	        rowData[8] = "";
	        rowData[9] = "";
	        rowData[10] = "";
	        rowData[11] = audioBook.getListeningLength();
	        rowData[12] = audioBook.getFormat();
	    }

	    return rowData;
	}


	/*This method clears the filtered audio books table and adds the provided list of audio books to the table.*/
	private void updateFilteredAudioBooksTable(List<AudioBook> audioBooks) {
	    dtmFilteredAudioBooks.setRowCount(0); // Clear the table
	    for (AudioBook audioBook : audioBooks) {
	        dtmFilteredAudioBooks.addRow(new Object[]{audioBook.getBarcode(), audioBook.getTitle(), audioBook.getLanguage(), audioBook.getGenre(), audioBook.getReleaseDate(), audioBook.getQuantity(),audioBook.getRetailPrice(), audioBook.getListeningLength(), audioBook.getFormat()});
	    }
	}
	/*This method checks if any of the provided string fields are empty and returns a boolean indicating the result.*/
	private boolean isAnyFieldEmpty(String... fields) {
	    for (String field : fields) {
	        if (field.isEmpty()) {
	            return true;
	        }
	    }
	    return false;
	}
	/*This method clears all the customer-related fields in the interface, including the search table, filtered audio books 
	 * table, and shopping basket table. It also resets the text of the basket total label.*/
	private void clearCustomerFields() {
	    // Clear tables
	    dtmSearchByBarcode.setRowCount(0);
	    dtmFilteredAudioBooks.setRowCount(0);
	    dtmShoppingBasket.setRowCount(0);
        lblBasketTotal.setText("Basket Total: £0.0");
	}
	/*This method updates the text of the basket total label to reflect the current total price of the basket.*/
	private void updateBalanceAndTotalLabels() {
	    lblBasketTotal.setText("Basket Total: £" + basket.getTotalPrice());
	}
}
