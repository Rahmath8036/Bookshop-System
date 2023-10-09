
public class Admin extends User{

	// Constructor for Admin class
	public Admin(String userID, String userName, String surname, Address addr) {
		super(userID, userName, surname, addr);
	}

	@Override
	public String toString() {
		return ("Admin ID: " + getUserID() + ", " + "Name: "+ getUserName() + ", " + getSurname() + ", " +
				"Address: "+getAddr());
	 }
}
