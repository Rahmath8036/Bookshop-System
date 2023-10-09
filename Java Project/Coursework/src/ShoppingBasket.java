// Import the ArrayList class
import java.util.ArrayList;

//Define the ShoppingBasket class
public class ShoppingBasket {
	
    private ArrayList<Book> items;

    // Constructor for the ShoppingBasket class
    public ShoppingBasket() {
        items = new ArrayList<>();
    }

    // Method to add a Book object to the items ArrayList
    public void addItem(Book book) {
        items.add(book);
    }
    // Method to clear all items from the items ArrayList (empty the shopping basket)
    public void emptyBasket() {
        items.clear();
    }

    // Method to calculate the total price of all Book objects in the items ArrayList
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Book book : items) {
            totalPrice += book.getRetailPrice();
        }
        return totalPrice;
    }
    
    // Method to return the items ArrayList (get all items in the shopping basket)
    public ArrayList<Book> getItems() {
        return items;
    }
    
    // Method to get the quantity of a specific Book object in the items ArrayList
    public int getQuantity(Book basketBook) {
        int quantity = 0;

        for (Book book : items) {
            if (book.equals(basketBook)) {
                quantity++;
            }
        }

        return quantity;
    }


}



