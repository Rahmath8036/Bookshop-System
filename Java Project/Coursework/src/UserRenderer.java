import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.*;

/*This class is a custom renderer for a JComboBox that contains User objects.
 * It extends BasicComboBoxRenderer, which is the basic Swing renderer for a JComboBox.*/

public class UserRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // Checking if the value is an instance of User
        if (value instanceof User) {
        	// Casting the value to User
            User user = (User) value;
            
            /*Checking if the user is an instance of Admin
             * If true, userType is set to "Admin", else "Customer"*/
            
            String userType = user instanceof Admin ? "Admin" : "Customer";
            setText(user.getUserID() + ": " + userType);
            setText(userType + " ID: " + user.getUserID());
        }
        return this;
    }
}
