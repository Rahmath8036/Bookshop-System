import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerDialog extends JDialog {

    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;

    private LocalDate selectedDate;

    public DatePickerDialog(JFrame parent) {
        super(parent, "Date Picker", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        Calendar currentDate = Calendar.getInstance();
        selectedDate = LocalDate.of(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1, currentDate.get(Calendar.DAY_OF_MONTH));


        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }

        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1);
        }
        
        String[] years = new String[51];  
        int currentYear = currentDate.get(Calendar.YEAR);
        for (int i = currentYear; i > currentYear - 51; i--) {
            years[currentYear - i] = Integer.toString(i);
        }

        dayComboBox = new JComboBox<>(days);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(dayComboBox, gridBagConstraints);

        monthComboBox = new JComboBox<>(months);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(monthComboBox, gridBagConstraints);

        yearComboBox = new JComboBox<>(years);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(yearComboBox, gridBagConstraints);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOk();
            }
        });

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(okButton, gridBagConstraints);

        getContentPane().add(panel);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    
    
    /* The method `onOk` is invoked when the user confirms their date selection. 
     * It attempts to parse the selected day, month, and year from the corresponding 
     * combo boxes into integers, and uses those to construct a LocalDate object. 
     * If the date is valid, the dialog is disposed, and the selected date can be 
     * retrieved using `getSelectedDate()`. However, if the constructed date is not 
     * valid (e.g., February 30), a DateTimeException is thrown. 
     * This exception is caught, and an error message dialog is displayed to the user.
     */
    private void onOk() {
        try {
        	int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
        int month = Integer.parseInt((String) monthComboBox.getSelectedItem());
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
        selectedDate = LocalDate.of(year, month, day);
        dispose();
        }
        catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid date. Please enter a valid date and try again.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
