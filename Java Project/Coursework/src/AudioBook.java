
public class AudioBook extends Book{
	
	// Declare attributes for AudioBook class
	private double listeningLength;
	private String format;

	// Constructor
	public AudioBook(String barcode, String title, String language, String genre, String releaseDate, int quantity,
			double retailPrice, double listeningLength, String format) {
		super(barcode, title, language, genre, releaseDate, quantity, retailPrice);
		this.listeningLength=listeningLength;
		this.format=format;
        this.bookType = "AudioBook";

		}
	/* Getters for AudioBook class*/
	public double getListeningLength() {
		return listeningLength;
	}

	public String getFormat() {
		return format;
	}

    @Override
    public String toString() {
        return "Paperbook{" +
                "barcode='" + getBarcode() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", releaseDate='" + getReleaseDate() + '\'' +
                ", quantityInStock=" + getQuantity() +
                ", retailPrice=" + getRetailPrice() +
                ", listeningLength=" + listeningLength +
                ", format='" + format + '\'' +
        '}';
    }
    
    @Override
    public String toFileString() {
        return String.join(", ", getBarcode(), "audiobook", getTitle(), getLanguage(), getGenre(), getReleaseDate(), Integer.toString(getQuantity()), String.format("%.2f", getRetailPrice()), String.format("%.2f", listeningLength), format);
    }
    

}
