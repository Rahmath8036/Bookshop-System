public class Ebook extends Book {

	// declare attributes for Ebook class
	private int numberOfPages;
	private String format;

	public Ebook(String barcode, String title, String language, String genre, String releaseDate, int quantity,
			double retailPrice, int numberOfPages, String format) {
		super(barcode, title, language, genre, releaseDate, quantity, retailPrice);
		this.bookType="Ebook";
		this.numberOfPages=numberOfPages;
		this.format=format;
	}
	/* getters for PaperBack class */
	public int getNumberOfPages() {
		return numberOfPages;
	}

	public String getFormat() {
		return format;
	}

    @Override
    public String toString() {
        return "Ebook{" +
                "barcode='" + getBarcode() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", releaseDate='" + getReleaseDate() + '\'' +
                ", quantityInStock=" + getQuantity() +
                ", retailPrice=" + getRetailPrice() +
                ", number Of Pages=" + numberOfPages +
                ", condition='" + format + '\'' +
        '}';
    }
    
    @Override
    public String toFileString() {
        return String.join(", ", getBarcode(), "ebook", getTitle(), getLanguage(), getGenre(), getReleaseDate(), Integer.toString(getQuantity()), String.format("%.2f", getRetailPrice()), Integer.toString(numberOfPages), format);
    }


}
