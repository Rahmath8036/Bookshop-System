
public class PaperBack extends Book {

	//Declare attributes for PaperBack class
	private int numberOfPages;
	private String condition;


	// Constructor for PaperBack class
	public PaperBack(String barcode, String title, String language,String genre,String releaseDate,int quantity,
			double retailPrice, String condition, int numberOfPages) {
		super( barcode,title,language,genre,releaseDate,quantity,retailPrice);
		this.numberOfPages=numberOfPages;
		this.condition=condition;
        this.bookType = "PaperBack";
}
	/* Listed below are getters for the PaperBack class attributes*/
	public int getNumberOfPages() {
		return numberOfPages;
	}

	public String getCondition() {
		return condition;
	}

    @Override
    public String toString() {
        return "Paperback{" +
                "barcode='" + getBarcode() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", genre='" + getGenre() + '\'' +
                ", releaseDate='" + getReleaseDate() + '\'' +
                ", quantityInStock=" + getQuantity() +
                ", retailPrice=" + getRetailPrice() +
                ", numberOfPages=" + numberOfPages +
                ", condition='" + condition + '\'' +
                '}';
    }
    
    @Override
    public String toFileString() {
        return String.join(", ", getBarcode(), "paperback", getTitle(), getLanguage(), getGenre(), getReleaseDate(), Integer.toString(getQuantity()), String.format("%.2f", getRetailPrice()), Integer.toString(numberOfPages), condition);
    }

}
