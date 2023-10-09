
public abstract class Book {
	
	// Declare attributes for Book class
    protected String bookType;
	private String barcode;
	private String title;
	private String language;
	private String genre;
	private String releaseDate;
	private int quantity;
	private double retailPrice;

	// Constructor 
	public Book(String barcode2, String title, String language,String genre,String releaseDate,int quantity,double retailPrice){
		this.barcode=barcode2;
		this.title=title;
		this.language=language;
		this.genre=genre;
		this.releaseDate=releaseDate;
		this.quantity=quantity;
		this.retailPrice=retailPrice;
	
	}
	/*Getters and setters for Book class*/
	public String getBarcode() {
		return barcode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getRetailPrice() {
		return retailPrice;
	}


    @Override
    public String toString() {
        return "Book" +
                "barcode='" + barcode + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", quantityInStock=" + quantity +
                ", retailPrice=" + retailPrice;
    }
    
    public abstract String toFileString();

}

