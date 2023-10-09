
public abstract class User {

	//Declare attributes for User class
	private String userID;
	private String userName;
	private String surname;
	private Address addr;

	//Constructor for User class
	public User(String userID, String userName, String surname, Address addr) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.surname = surname;
		this.addr = addr;
	}
	
	/*Listed below are getters and some setters to access the 
	 * User class attributes*/
	
	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getSurname() {
		return surname;
	}

	public Address getAddr() {
		return addr;
	}

	@Override
	public abstract String toString();


}
