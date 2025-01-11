import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;

// Dialog for editing the attributes of a collectible
class EditCollectibleDialog extends JDialog {
    private JTextField priceField;  //TextField for editing the price
    private JTextField conditionField;  // Text field for editing the condition

    // Constructor to initialize the dialog
    public EditCollectibleDialog(JFrame parent, Collectible collectible) {
        super(parent, "Edit Collectible", true);  // Calling the JDialog constructor
        setLayout(new GridLayout(3, 2));  // Using a 3x2 grid layout for fields and buttons

        // Field for editing the price
        add(new JLabel("Price:"));
        NumberFormat doubleFormat = NumberFormat.getNumberInstance();
        priceField = new JTextField(String.valueOf(collectible.getBiddingPrice()));
        add(priceField);
        // Field for editing the condition
        add(new JLabel("Condition:"));
        conditionField = new JTextField(collectible.getCondition());
        add(conditionField);

        // Save button for applying changes
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {  // Adding event listener to handle button clicks
            try {
                // Parsing the new price and updating the collectible
                collectible.setBiddingPrice(Double.parseDouble(priceField.getText()));
                collectible.setCondition(conditionField.getText());  // updating the collectibles condition
                dispose();  // Closing the dialog after saving changes
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Invalid input!. Please Enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(saveButton); // Adding the save button to the Layout
        setSize(300, 150);
    }
}
