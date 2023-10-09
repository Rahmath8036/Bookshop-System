
public class Address {
	
	// Declare attributes for Address class
	private int number;
	private String postcode;
	private String city;
	public Address (int number, String postcode, String city) {
	   this.number = number;
	   this.postcode = postcode;
	   this.city=city;
	 }
	@Override
	public String toString() {
	return (this.number + ", " + this.postcode+", "+this.city);
	}
	
	/* Declare getters for Address class*/
	public int getNumber() {
		return number;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public String getCity() {
		return city;
	}

}
