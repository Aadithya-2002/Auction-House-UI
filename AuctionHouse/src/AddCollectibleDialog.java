import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddCollectibleDialog extends JDialog {
    private JComboBox<String> typeComboBox;  // Dropdown for selecting the type of collectible
    private JTextField titleField, priceField, conditionField, ownerField, yearField, extraField1, extraField2, extraField3;
    private CollectionList collection; // Reference to the collection being updated

    // Constructor to initialize the dialog
    public AddCollectibleDialog(JFrame parent, CollectionList collection) {
        super(parent, "Add Collectible", true); // Make the dialog model
        this.collection = collection; // Store the reference to the collection

        // Setting up the layout for the dialog
        setLayout(new GridLayout(10, 2));

        // DropDown for selecting the collectible type
        add(new JLabel("Type: "));
        typeComboBox = new JComboBox<>(new String[]{"Book", "Painting", "Car", "Jewellery"});
        add(typeComboBox);

        // Common Fields
        add(new JLabel("Title: "));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Price: "));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Condition: "));
        conditionField = new JTextField();
        add(conditionField);

        add(new JLabel("Owner: "));
        ownerField = new JTextField();
        add(ownerField);

        add(new JLabel("Year Estimate: "));
        yearField = new JTextField();
        add(yearField);

        // Extra fields for specific attributes
        extraField1 = new JTextField();
        extraField2 = new JTextField();
        extraField3 = new JTextField();

        // Fields label will be updated dynamically
        add(new JLabel("Field 1: "));
        add(extraField1);

        add(new JLabel("Field 2: "));
        add(extraField2);

        add(new JLabel("Field 3: "));
        add(extraField3);

        // Buttons for saving / cancelling
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveCollectible());
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
        // Action listener for the typecombobox to update form fields dynamically
        typeComboBox.addActionListener(e -> updateFormFields());

        // Initializing teh form fields based on the selected type
        updateFormFields();
        setSize(400, 400);
    }
    // Dynamically updating fields based on teh collectible type
    private void updateFormFields(){
        String selectedType = (String) typeComboBox.getSelectedItem();
        if (selectedType == null) return;
        // Updating field based on the collectible type
        switch (selectedType) {
            case "Book":
                ((JLabel)getContentPane().getComponent(12)).setText("Author:");
                ((JLabel)getContentPane().getComponent(14)).setText("Edition:");
                ((JLabel)getContentPane().getComponent(16)).setText("Genre:");
                break;

                case "Painting":
                    ((JLabel)getContentPane().getComponent(12)).setText("Style:");
                    ((JLabel)getContentPane().getComponent(14)).setText("Width:");
                    ((JLabel)getContentPane().getComponent(16)).setText("Height:");
                    break;

                    case "Car":
                        ((JLabel)getContentPane().getComponent(12)).setText("Make:");
                        ((JLabel)getContentPane().getComponent(14)).setText("Model:");
                        ((JLabel)getContentPane().getComponent(16)).setText("Serviced (Yes/No):");
                        break;
                        case "Jewellery":
                            ((JLabel)getContentPane().getComponent(12)).setText("Type:");
                            ((JLabel)getContentPane().getComponent(14)).setText("Material:");
                            ((JLabel)getContentPane().getComponent(16)).setText("Gems:");
                            break;
        }
    }
    // Saving the collectible to the collection
    private void saveCollectible(){
        try {
            String title = titleField.getText();
            String[] years = yearField.getText().split("-");
            if (years.length !=2){
                throw new IllegalArgumentException("Year Estimate must be in the format 'year1-year2'.");
            }
            int lowYear = Integer.parseInt(years[0].trim());
            int highYear = Integer.parseInt(years[1].trim());
            YearEstimate yearEstimate = new YearEstimate(lowYear, highYear);

            double price = Double.parseDouble(priceField.getText().trim());
            String condition = conditionField.getText().trim();
            String owner = ownerField.getText().trim();


            String selectedType = (String) typeComboBox.getSelectedItem();
            if (selectedType == null) return;
            // Type specific logic for creating the collectible
            switch (selectedType) {
                case "Book":
                    String author = extraField1.getText().trim();
                    int edition = Integer.parseInt(extraField2.getText().trim());
                    String genre = extraField3.getText().trim();
                    collection.addCollectible(new Books(title, yearEstimate, collection.getCollectibles().size() + 1, owner, condition, price, author, edition, genre));
                    break;

                case "Painting":
                    String style = extraField1.getText().trim();
                    int width = Integer.parseInt(extraField2.getText().trim());
                    int height = Integer.parseInt(extraField3.getText().trim());
                    collection.addCollectible(new Painting(title, yearEstimate, owner, condition, price, collection.getCollectibles().size() + 1, style, width, height));
                    break;

                case "Car":
                    String make = extraField1.getText().trim();
                    String model = extraField2.getText().trim();
                    boolean serviced = Boolean.parseBoolean(extraField3.getText().trim());
                    collection.addCollectible(new Car(title, yearEstimate, collection.getCollectibles().size() + 1, owner, condition, price, make, model, serviced));
                    break;

                case "Jewellery":
                    String type = extraField1.getText().trim();
                    String material = extraField2.getText().trim();
                    String gems = extraField3.getText().trim();
                    collection.addCollectible(new Jewellery(title, yearEstimate, collection.getCollectibles().size() + 1, owner, condition, price, type, material, gems));
                    break;
            }
            JOptionPane.showMessageDialog(this, "Collectible added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input, Please check all fields and try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
