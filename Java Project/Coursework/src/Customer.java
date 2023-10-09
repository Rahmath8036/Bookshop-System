public class Customer extends User{
	// Declare attributes for Customer class
	private double creditBalance;

	// Attributes for Customer class
	public Customer(String userID, String userName, String surname, Address addr, double creditBalance/*, ArrayList<String> shoppingBasket*/) {
		super(userID, userName, surname, addr);
		this.creditBalance=creditBalance;
		}

	/* Getters and setters for Customer class*/
	public double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}

	@Override
	public String toString() {
		return ("Customer ID: " + getUserID() + ", " + "Name: "+ getUserName() + ", " + getSurname() + ", " +
				"Address: "+getAddr()+", "+ "Credit balance: "+getCreditBalance());
	 }
}
