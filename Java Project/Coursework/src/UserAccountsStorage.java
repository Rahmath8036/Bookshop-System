// Import required classes
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Define UserAccountsStorage class to store and manage User objects
public class UserAccountsStorage {
    private List<User> users;

    public UserAccountsStorage() {
        users = new ArrayList<>();
    }
    // Method to create a User object from a string array
    private User createUserFromData(String[] userData) {
        String userID = userData[0].trim();
        String userName = userData[1].trim();
        String surname = userData[2].trim();
        int houseNumber = Integer.parseInt(userData[3].trim());
        String postcode = userData[4].trim();
        String city = userData[5].trim();
        String role = userData[7].trim();
        
        // Creating Address object from parsed data
        Address addr = new Address(houseNumber, postcode, city);
        
        // Checking role to determine what type of User to create
        if ("Admin".equalsIgnoreCase(role)) {
            return new Admin(userID, userName, surname, addr);
        } else {
        	// If user is a Customer, parse the credit balance
            double creditBalance = Double.parseDouble(userData[6].trim());
            return new Customer(userID, userName, surname, addr, creditBalance);
        }
    }

    // Method to get a User by their ID
    public User getUserById(int userId) {
        for (User user : users) {
            if (Integer.parseInt(user.getUserID()) == userId) {
                return user;
            }
        }
        return null;
    }
    
    // Method to update the credit balance of a User
    public void updateUserCredit(int userId, double newCredit) {
        User user = getUserById(userId);
        if (user != null && user instanceof Customer) {
            ((Customer) user).setCreditBalance(newCredit);
            saveAllUserAccountsToFile("UserAccounts.txt"); 
        }
    }

    // Method to write all user data to a file
    public void saveAllUserAccountsToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (User user : users) {
                String userString = user.getUserID() + ", " +
                                    user.getUserName() + ", " +
                                    user.getSurname() + ", " +
                                    user.getAddr().getNumber() + ", " +
                                    user.getAddr().getPostcode() + ", " +
                                    user.getAddr().getCity() + ", ";

                if (user instanceof Customer) {
                    userString += String.format("%.2f", ((Customer) user).getCreditBalance()) + ", ";
                } else {
                    userString += ", ";
                }

                userString += user instanceof Admin ? "admin" : "customer";
                writer.write(userString + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to add a User to the list
    public void addUser(User user) {
        users.add(user);
    }
    
    // Method to get a User by their ID
    public User getUser(String userID) {
        for (User user : users) {
        	if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
    // Method to get all Users
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    // Method to read User data from a file
    public void readUserAccountsFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");
                addUser(createUserFromData(userData));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
