// Import Statements
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//Class Declaration
public class BookStorage {
    private List<Book> books;
    
	 // Constructor
    public BookStorage() {
        books = new ArrayList<>();
    }
    // Method to Read Books from a File
    public void readBooksFromFile( String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] bookData = line.split(",");
                addBook(createBookFromData(bookData));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to Update Book Quantity (Admin)
    public boolean updateQuantityAdmin(String barcode, int quantityInStock) {
      for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
            book.setQuantity(book.getQuantity() + quantityInStock);
             return true;
          }
      }
       return false;
    }
    // Method to Update Book Quantity 
    public void updateQuantity(String barcode, int quantityChange) {
        Book book = getBook(barcode);
        if (book != null) {
            int newQuantity = book.getQuantity() - quantityChange;
            if (newQuantity < 0) {
                newQuantity = 0;
            }
            book.setQuantity(newQuantity);
        }
    }
    
    // Helper Methods to Validate Conditions and Formats
    private boolean isValidCondition(String condition) {
        return "new".equalsIgnoreCase(condition) || "used".equalsIgnoreCase(condition);
    }

    private boolean isValidEbookFormat(String format) {
        return "pdf".equalsIgnoreCase(format) || "epub".equalsIgnoreCase(format) || "mobi".equalsIgnoreCase(format);
    }

    private boolean isValidAudioFormat(String format) {
        return "mp3".equalsIgnoreCase(format) || "wma".equalsIgnoreCase(format) || "aac".equalsIgnoreCase(format);
    }


    // Method to Save All Books to a File
    public void saveAllBooksToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Book book : books) {
                writer.write(book.toFileString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to Create a Book From Data
    private Book createBookFromData(String[] bookData) {
        String barcode = bookData[0].trim();
        String bookType = bookData[1].trim();
        String title = bookData[2].trim();
        String language = bookData[3].trim();
        String genre = bookData[4].trim();
        String releaseDate = bookData[5].trim();
        int quantityInStock = Integer.parseInt(bookData[6].trim());
        double retailPrice = Double.parseDouble(bookData[7].trim());
        String additionalInfo1 = bookData[8].trim();
        String additionalInfo2 = bookData[9].trim();

        // Rest of the method remains the same

        Book book;

        switch (bookType.toLowerCase()) {
            case "paperback":
                int numberOfPages = Integer.parseInt(additionalInfo1);
                String condition = additionalInfo2;
                book = new PaperBack(barcode, title, language, genre, releaseDate, quantityInStock, retailPrice, condition, numberOfPages);
                break;
            case "ebook":
                numberOfPages = Integer.parseInt(additionalInfo1);
                String format = additionalInfo2;
                book = new Ebook(barcode, title, language, genre, releaseDate, quantityInStock, retailPrice, numberOfPages, format);
                break;
            case "audiobook":
                double listeningLength = Double.parseDouble(additionalInfo1);
                format = additionalInfo2;
                book = new AudioBook(barcode, title, language, genre, releaseDate, quantityInStock, retailPrice, listeningLength, format);
                break;
            default:
                throw new IllegalArgumentException("Invalid book type: " + bookType);
        }

        return book;
    }
    // Method to Get All Books Sorted by Price
    public List<Book> getAllBooksSortedByPrice() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingDouble(Book::getRetailPrice));
        return sortedBooks;
    }
    // Method to Get AudioBooks by Length
    public List<AudioBook> getAudioBooksByLength(double minLength) {
        List<AudioBook> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book instanceof AudioBook) {
                AudioBook audioBook = (AudioBook) book;
                if (audioBook.getListeningLength() > minLength) {
                    filteredBooks.add(audioBook);
                }
            }
        }
        return filteredBooks;
    }
    // Method to Get Book by Barcode
    public Book getBookByBarcode(String barcode) {
        for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
                return book;
            }
        }
        return null;
    }

    // Method to Add a Book to the List
    public void addBook(Book book) {
        books.add(book);
    }
    // Method to Get a Book by its Barcode
    public Book getBook(String barcode) {
        for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
                return book;
            }
        }
        return null;
    }
    // Method to Get All Books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

// Method to Get All Books Sorted by Quantity
	public List<Book> getAllBooksSortedByQuantity() {
	    List<Book> sortedBooks = new ArrayList<>(books);
	    sortedBooks.sort(Comparator.comparingInt(Book::getQuantity));
	    return sortedBooks;
	}
	
	//Method to Add a New Book
	public Book addNewBook(String bookType, String barcode, String title, String language, String genre, String releaseDate, int quantityInStock, double retailPrice, Object additionalInfo1, String additionalInfo2) {
	    Book book;
	
	    switch (bookType.toLowerCase()) {
	        case "paperback":
	        	int numberOfPages = ((Number) additionalInfo1).intValue();
	            //int numberOfPages = (int) additionalInfo1;
	            String condition = additionalInfo2;
	            if (!isValidCondition(condition)) {
	                System.out.println("Error: Invalid book condition. Only 'new' or 'used' are allowed.");
	                return null;
	            }
	            book = new PaperBack(barcode, title, language, genre, releaseDate, quantityInStock, retailPrice, condition, numberOfPages);
	            break;
	        case "ebook":
	        	int numberOfPages1 = ((Number) additionalInfo1).intValue();
	            //int numberOfPages1 = (int) additionalInfo1;
	            //numberOfPages = additionalInfo1;
	            String format = additionalInfo2;
	            if (!isValidEbookFormat(format)) {
	                System.out.println("Error: Invalid ebook format. Only 'pdf', 'epub', or 'mobi' are allowed.");
	                return null;
	            }
	            book = new Ebook(barcode, title, language, genre, releaseDate, quantityInStock, retailPrice, numberOfPages1, format);
	            break;
	        case "audiobook":
	            double listeningLength = (double) additionalInfo1;
	            format = additionalInfo2;
	            if (!isValidAudioFormat(format)) {
	                System.out.println("Error: Invalid audio format. Only 'mp3', 'wav', or 'm4b' are allowed.");
	                return null;
	            }
	            book = new AudioBook(barcode, title, language, genre, releaseDate, quantityInStock, retailPrice, listeningLength, format);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid book type: " + bookType);
	    }
	
	    addBook(book);
	    return book;
	}
	//Method to Save a Single Book to a File
	public void saveBookToFile(Book book, String filePath) {
	    try (FileWriter writer = new FileWriter(filePath, true)) {
	        writer.write(book.toFileString() + "\n");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
