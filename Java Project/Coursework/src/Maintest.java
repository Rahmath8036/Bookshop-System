//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Scanner;
//
//
//public class Maintest {
//	public static boolean isValidBookType(String bookType) {
//        return "paperback".equalsIgnoreCase(bookType) || "ebook".equalsIgnoreCase(bookType) || "audiobook".equalsIgnoreCase(bookType);
//    }
//	
//	private static void displayHeadings() {
//	    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	    System.out.printf("%-10s %-40s %-10s %-20s %-12s %-10s %-10s %-10s %-12s %-12s %-17s %-17s %-17s %n", "Barcode", "Title", "Language", "Genre", "Release Date", "Quantity", "Price", "Book Type", "Condition", "Pages", "Ebook Format", "Audio Length", "Audio Format");
//	    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	}
//	
//	private static void displayBook(Book book) {
//	    String bookType = "-";
//	    String condition = "-";
//	    String pages = "-";
//	    String ebookFormat = "-";
//	    String length = "-";
//	    String audioFormat = "-";
//
//	    if (book instanceof PaperBack) {
//	        bookType = "paperback";
//	        condition = ((PaperBack) book).getCondition();
//	        pages = String.valueOf(((PaperBack) book).getNumberOfPages());
//	    } else if (book instanceof Ebook) {
//	        bookType = "ebook";
//	        pages = String.valueOf(((Ebook) book).getNumberOfPages());
//	        ebookFormat = ((Ebook) book).getFormat();
//	    } else if (book instanceof AudioBook) {
//	        bookType = "audiobook";
//	        length = String.valueOf(((AudioBook) book).getListeningLength());
//	        audioFormat = ((AudioBook) book).getFormat();
//	    }
//
//	    System.out.printf("%-10d %-40s %-10s %-20s %-12s %-10d %-10.2f %-10s %-12s %-12s %-17s %-17s %-17s %n", book.getBarcode(), book.getTitle(), book.getLanguage(), book.getGenre(), book.getReleaseDate(), book.getQuantity(), book.getRetailPrice(), bookType, condition, pages, ebookFormat, length, audioFormat);
//	}
//	
//	private static void addToBasket(BookStorage bookStorage, int barcode, ShoppingBasket basket) {
//	    Book book = bookStorage.getBookByBarcode(barcode);
//	    if (book != null && book.getQuantity() > 0) {
//	        basket.addItem(book);
//	        System.out.println("Book added to your basket: " + book.getTitle());
//	    } else {
//	        System.out.println("Book not available for purchase.");
//	    }
//	}
//
//
//
//
//	public static void main(String[] args) {
//		
//		
//		
//	        BookStorage bookStorage = new BookStorage();
//	        bookStorage.readBooksFromFile("Stock.txt");
//
//	        int someBarcode = 11223344; // Replace this with the desired barcode
//	        Book book = bookStorage.getBook(someBarcode);
//	        System.out.println(book);
//
//	        List<Book> allBooks = bookStorage.getAllBooks();
//	        System.out.println(allBooks);
//
//	        UserAccountsStorage userAccountsStorage = new UserAccountsStorage();
//	        userAccountsStorage.readUserAccountsFromFile("UserAccounts.txt");
//
//	        String someUserID = "101"; 
//	        User user = userAccountsStorage.getUser(someUserID);
//	        List<User> allUsers = userAccountsStorage.getAllUsers();
//
//	        System.out.println(user); 
//	        System.out.println(allUsers); 
//	        
//	        
//	        boolean runApp = true;
//	        while (runApp) {
//
//
//	        Scanner scanner = new Scanner(System.in);
//	        System.out.println("Enter admin/customer user ID:");
//	        String someUserId = scanner.nextLine();
//	        User user1 = userAccountsStorage.getUser(someUserId);
//	        
//	        
//
//	     // ...
//	        if (user1 != null && user1 instanceof Customer) {
//	            boolean exit = false;
//                ShoppingBasket basket = new ShoppingBasket(); // Declare and initialize the basket variable outside the switch statement
//
//	            while (!exit) {
//	                System.out.println("\nChoose an option:");
//	                System.out.println("1. View all books sorted by retail price");
//	                System.out.println("2. Add a new book");
//	                System.out.println("3. View shopping basket");
//	                System.out.println("4. Proceed to checkout");
//	                System.out.println("5. Empty your basket");
//	                System.out.println("6. Search a book by barcode");
//	                System.out.println("7. Filter audiobooks by listening length");
//	                System.out.println("8. Log out");
//	                System.out.println("9. Exit application");
//
//
//	                String choice = scanner.nextLine();
//
//	            switch (choice) {
//	            case "1":
//	            List<Book> sortedBooks = bookStorage.getAllBooksSortedByPrice();
//	            System.out.println("All books sorted by price:");
//	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	            System.out.printf("%-10s %-40s %-10s %-20s %-12s %-10s %-10s %-10s %-12s %-12s %-17s %-17s %-17s %n", "Barcode", "Title", "Language", "Genre", "Release Date", "Quantity", "Price", "Book Type", "Condition", "Pages", "Ebook Format", "Audio Length", "Audio Format");
//	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	            for (Book book1 : sortedBooks) {
//    	                String bookType = "-";
//    	                String condition = "-";
//    	                String pages = "-";
//    	                String ebookFormat = "-";
//    	                String length = "-";
//    	                String audioFormat = "-";
//
//    	                if (book1 instanceof PaperBack) {
//    	                    bookType = "paperback";
//    	                    condition = ((PaperBack) book1).getCondition();
//    	                    pages = String.valueOf(((PaperBack) book1).getNumberOfPages());
//    	                } else if (book1 instanceof Ebook) {
//    	                    bookType = "ebook";
//    	                    pages = String.valueOf(((Ebook) book1).getNumberOfPages());
//    	                    ebookFormat = ((Ebook) book1).getFormat();
//    	                } else if (book1 instanceof AudioBook) {
//    	                    bookType = "audiobook";
//    	                    length = String.valueOf(((AudioBook) book1).getListeningLength());
//    	                    audioFormat = ((AudioBook) book1).getFormat();
//    	                }
//
//    	                System.out.printf("%-10d %-40s %-10s %-20s %-12s %-10d %-10.2f %-10s %-12s %-12s %-17s %-17s %-17s %n", book1.getBarcode(), book1.getTitle(), book1.getLanguage(), book1.getGenre(), book1.getReleaseDate(), book1.getQuantity(), book1.getRetailPrice(), bookType, condition, pages, ebookFormat, length, audioFormat);
//    	            }
//    	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//    	            break;
//	            case "2":
//	                  while (true) {
//	                      System.out.println("Enter the barcode of the book you want to add to your basket (or enter -1 to exit):");
//	                      int inputBarcode = scanner.nextInt();
//	                      scanner.nextLine(); 
//
//
//	                      if (inputBarcode == -1) {
//	                          break;
//	                      }
//
//	                      Book book1 = bookStorage.getBook(inputBarcode);
//
//	                      if (book1 != null && book1.getQuantity() > 0) {
//	                          basket.addItem(book1);
//	                          System.out.println("Book added to your basket: " + book1.getTitle());
//	                      } else {
//	                          System.out.println("Book not found or out of stock.");
//	                      }
//	                  }
//
//	                  break;
//	            case "3":
//	                if (basket.getItems().isEmpty()) {
//	                    System.out.println("Your shopping basket is empty.");
//	                } else {
//	                    System.out.println("-------------------------------------------------------------------------------------");
//	                    System.out.printf("%-10s %-20s %-10s %-10s %-14s %-10s %n", "Barcode", "Title", "Language", "Genre", "Release Date", "Price");
//	                    System.out.println("-------------------------------------------------------------------------------------");
//	                    for (Book basketBook : basket.getItems()) {
//	                        System.out.printf("%-10d %-20s %-10s %-10s %-14s %-10.2f %n", basketBook.getBarcode(), basketBook.getTitle(), basketBook.getLanguage(), basketBook.getGenre(), basketBook.getReleaseDate(), basketBook.getRetailPrice());
//	                    }
//	                }
//	                break;
//	            case "4":
//	                if (basket.getItems().isEmpty()) {
//	                    System.out.println("Your shopping basket is empty. Please add items to your basket before proceeding to checkout.");
//	                } else {
//	            	boolean allBooksInStock= true;
//	            	
//	                for (Book basketBook : basket.getItems()) {
//	                    Book bookInStock = bookStorage.getBook(basketBook.getBarcode());
//	                    if (bookInStock == null || bookInStock.getQuantity() <= 0) {
//	                        allBooksInStock = false;
//	                        System.out.println("The book " + basketBook.getTitle() + " is not in stock. Please remove it from your basket.");
//	                        break;
//	                    }
//	                }
//	                if (allBooksInStock) {
//	                	
//	                }
//	                double totalPrice = basket.getTotalPrice();
//	                Customer customer = (Customer) user1;
//
//	                if (customer.getCreditBalance() >= totalPrice) {
//	                	for (Book purchasedBook : basket.getItems()) {
//	                	    bookStorage.updateQuantity(purchasedBook.getBarcode(), 1);
//	                	}
//	                	bookStorage.saveAllBooksToFile("Stock.txt"); 
//	                	
//	                	
//	                    double remainingCredit = customer.getCreditBalance() - totalPrice;
//	                    userAccountsStorage.updateUserCredit(Integer.parseInt(customer.getUserID()), remainingCredit);
//
//	                    basket.emptyBasket();
//	                
//
//	                    System.out.printf("Thank you for the purchase! £%.2f paid and your remaining credit balance is £%.2f. Your delivery address is %s.%n",
//	                        totalPrice, remainingCredit, customer.getAddr());
//	                } else {
//	                    System.out.println("You don't have enough credit to pay for the items in your basket.");
//	                }
//	                }
//	
//	                break;
//
//
//	            case "5": 
//	            	
//	                cancelShoppingBasket(basket);
//	                break;
//	            case "6":
//	                System.out.println("Enter the barcode to search for:");
//	                int barcode = Integer.parseInt(scanner.nextLine());
//	                Book book1 = bookStorage.getBookByBarcode(barcode);
//	                if (book1 != null) {
//	                    displayHeadings();
//	                    displayBook(book1);
//	                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//
//	                    System.out.println("Do you want to add this book to your basket? Enter 'yes' to confirm or any other key to cancel.");
//	                    String addConfirmation = scanner.nextLine();
//	                    if (addConfirmation.equalsIgnoreCase("yes")) {
//	                        addToBasket(bookStorage, barcode, basket);
//	                    } else {
//	                        System.out.println("Adding to basket canceled.");
//	                    }
//	                } else {
//	                    System.out.println("Book not found.");
//	                }
//	                break;
//	            case "7":
//	                System.out.println("Enter the minimum listening length (in hours):");
//	                try {
//	                    double minLength = Double.parseDouble(scanner.nextLine());
//
//	                    List<AudioBook> audioBooks = bookStorage.getAudioBooksByLength(minLength);
//	                    if (!audioBooks.isEmpty()) {
//	                        System.out.println("Audio books with listening length greater than " + minLength + " hours:");
//	                        displayHeadings();
//
//	                        for (AudioBook audioBook : audioBooks) {
//	                            displayBook(audioBook);
//	                        }
//	                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//
//	                        System.out.println("Enter the barcode of the audio book you want to add to your basket or '0' to cancel:");
//	                        int addBarcode = Integer.parseInt(scanner.nextLine());
//	                        if (addBarcode != 0) {
//	                            addToBasket(bookStorage, addBarcode, basket);
//	                        } else {
//	                            System.out.println("Adding to basket canceled.");
//	                        }
//	                    } else {
//	                        System.out.println("No audio books found with listening length greater than " + minLength + " hours.");
//	                    }
//	                } catch (NumberFormatException e) {
//	                    System.out.println("Invalid input. Please enter a valid number and try again.");
//	                }
//	                break;
//
//
//
//	                
//
//	            case "8":
//	                System.out.println("Logging out");
//	                exit = true;
//	                break;
//	            case "9":
//	                System.out.println("Exiting the application");
//	                exit = true;
//	                runApp = false;
//	                break;
//
//
//
//	            }
//	            }
//
//	        }
//
//
//
//
//	        else if (user1 instanceof Admin) {
//	            boolean exit = false;
//	            adminMenu: while (!exit) {
//	                System.out.println("\nChoose an option:");
//	                System.out.println("1. View all books sorted by quantity");
//	                System.out.println("2. Add a new book");
//	                System.out.println("3. Log out");
//	                System.out.println("4. Exit application");
//
//	                String choice = scanner.nextLine();
//
//	                switch (choice) {
//	                    case "1":
//	                    	List<Book> allBooksSortedByQuantity = bookStorage.getAllBooksSortedByQuantity();
//
//	        	            System.out.println("Books sorted by quantity in stock (ascending):");
//	        	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	        	            System.out.printf("%-10s %-40s %-10s %-20s %-12s %-10s %-10s %-10s %-12s %-12s %-17s %-17s %-17s %n", "Barcode", "Title", "Language", "Genre", "Release Date", "Quantity", "Price", "Book Type", "Condition", "Pages", "Ebook Format", "Audio Length", "Audio Format");
//	        	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	        	            for (Book book1 : allBooksSortedByQuantity) {
//	        	                String bookType = "-";
//	        	                String condition = "-";
//	        	                String pages = "-";
//	        	                String ebookFormat = "-";
//	        	                String length = "-";
//	        	                String audioFormat = "-";
//
//	        	                if (book1 instanceof PaperBack) {
//	        	                    bookType = "paperback";
//	        	                    condition = ((PaperBack) book1).getCondition();
//	        	                    pages = String.valueOf(((PaperBack) book1).getNumberOfPages());
//	        	                } else if (book1 instanceof Ebook) {
//	        	                    bookType = "ebook";
//	        	                    pages = String.valueOf(((Ebook) book1).getNumberOfPages());
//	        	                    ebookFormat = ((Ebook) book1).getFormat();
//	        	                } else if (book1 instanceof AudioBook) {
//	        	                    bookType = "audiobook";
//	        	                    length = String.valueOf(((AudioBook) book1).getListeningLength());
//	        	                    audioFormat = ((AudioBook) book1).getFormat();
//	        	                }
//
//	        	                System.out.printf("%-10d %-40s %-10s %-20s %-12s %-10d %-10.2f %-10s %-12s %-12s %-17s %-17s %-17s %n", book1.getBarcode(), book1.getTitle(), book1.getLanguage(), book1.getGenre(), book1.getReleaseDate(), book1.getQuantity(), book1.getRetailPrice(), bookType, condition, pages, ebookFormat, length, audioFormat);
//	        	            }
//	        	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//	        	          {
//	        	            
//	        	        }
//
//	                        break;
//	                    case "2":
//	                    	
//	                    	userAccountsStorage = new UserAccountsStorage();
//
//	                    	
//	                    	userAccountsStorage.readUserAccountsFromFile("UserAccounts.txt");
//
//	                    	System.out.println("Enter book type (paperback, ebook, audiobook):");
//	                    	String bookType = scanner.nextLine();
//
//	                    	while (!isValidBookType(bookType)) {
//	                    	    System.out.println("Invalid book type. Enter book type (paperback, ebook, audiobook):");
//	                    	    bookType = scanner.nextLine();
//	                    	}
//
//	                    	System.out.println("Enter book information in the following format:");
//	                    	System.out.println("barcode, title, language, genre, release_date, quantity_in_stock, retail_price, additional_info_1, additional_info_2");
//	                    	String bookInfo = scanner.nextLine();
//	                    	String[] bookData = bookInfo.split(",");
//
//	                    	if (bookData.length != 9) {
//	                    	    System.out.println("Invalid input format. Please follow the instructions and try again.");
//	                    	} else {
//	                    	    try {
//	                    	        
//	                    	        int barcode = Integer.parseInt(bookData[0].trim());
//	                    	        int quantityInStock = Integer.parseInt(bookData[5].trim());
//	                    	        double retailPrice = Double.parseDouble(bookData[6].trim());
//	                    	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//	                    	        dateFormat.setLenient(false);
//	                    	        java.util.Date utilDate = dateFormat.parse(bookData[4].trim());
//	                    	        java.sql.Date releaseDate = new java.sql.Date(utilDate.getTime());
//
//	                    	        String language = bookData[2].trim();
//	                    	        if (!language.equalsIgnoreCase("English") && !language.equalsIgnoreCase("French")) {
//	                    	            System.out.println("Error: Invalid book language. Only English or French is allowed.");
//	                    	            
//	                    	            continue adminMenu;
//	                    	        }
//
//	                    	        
//	                    	        boolean bookUpdated = bookStorage.updateQuantityAdmin(barcode, quantityInStock);
//	                    	        if (bookUpdated) {
//	                    	            System.out.println("Book already exists. Updated the quantity.");
//	                    	        } else {
//	                    	            
//	                    	        	
//	                    	        	Book newbook=bookStorage.addNewBook(
//	                    	        		    bookType.toLowerCase(),
//	                    	        		    barcode,
//	                    	        		    bookData[1].trim(),
//	                    	        		    bookData[2].trim(),
//	                    	        		    bookData[3].trim(),
//	                    	        		    bookData[4].trim(),
//	                    	        		    quantityInStock,
//	                    	        		    retailPrice,
//	                    	        		    (int) ("paperback".equals(bookType.toLowerCase()) || "ebook".equals(bookType.toLowerCase()) ? Integer.parseInt(bookData[7].trim()) : Double.parseDouble(bookData[7].trim())),
//	                    	        		    bookData[8].trim()
//	                    	        		);
//
//	                    	        	if (newbook != null) {
//	                    	        	    bookStorage.saveBookToFile(newbook, "Stock.txt");
//	                    	        	    System.out.println("New book added successfully.");
//	                    	        	} else {
//	                    	        	    System.out.println("Error adding the new book.");
//	                    	        	}
//
//	                    	        }
//
//	                    	        
//	                    	        bookStorage.saveAllBooksToFile("Stock.txt");
//
//	                    	    } catch (NumberFormatException e) {
//	                    	        System.out.println("Invalid input for barcode, quantity, price, or number of pages. Please enter valid numbers and try again.");
//	                    	    } catch (ParseException e) {
//	                    	        System.out.println("Invalid date format. Please enter the date in the format dd-MM-yyyy and try again.");
//	                    	    }
//	                    	}
//	                    	break;
//
//
//
//	                    case "3":
//	                        System.out.println("Logging out...");
//	                        exit = true;
//	                        break;
//	                    case "4":
//	                        System.out.println("Exiting the application...");
//	                        exit = true;
//	                        runApp = false;
//	                        break;
//
//
//	                    default:
//	                        System.out.println("Invalid choice. Please try again.");
//	                        break;
//	                }
//	            }
//	        } else {
//	            System.out.println("Only admins/ customers can access this functionality.");
//	        }
//	        }
//	}
//	public static void cancelShoppingBasket(ShoppingBasket shoppingBasket) {
//	    shoppingBasket.emptyBasket();
//	    System.out.println("Your shopping basket has been emptied.");
//	}
//
//	}