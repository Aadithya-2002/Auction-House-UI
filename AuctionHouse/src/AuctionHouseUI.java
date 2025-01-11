import javax.swing.*; // Swing library for GUI components
import java.awt.*; // AWT library for layout management
import javax.swing.table.*; // Library for working with JTable and table models
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections; // Utility for Sorting collections
import java.util.Comparator; // Utility for  creating comparators


public class
AuctionHouseUI {
    private JFrame frame; // Main application window
    private JTable collectiblesTable;  // Table to display collectibles
    private CollectionList collection;  // Collection of all collectibles
    private DefaultTableModel tableModel;  // Table Model for dynamic data updates

    // Constructor to initialize the UI with a collectionList
    public AuctionHouseUI(CollectionList collection) {
        this.collection = collection;
        initializeUI();
    }

    // Initializing the main user interface
    private void initializeUI() {
        frame = new JFrame("Auction House");   //Creating the main windoww
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setLayout(new BorderLayout());  // using BorderLayout for organizing components

        // Panel for the table
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // TableModel with column headers
        tableModel = new DefaultTableModel(new String[]{"Title","Type","Year Estimate", "Id", "Price", "Condition", "Owner"}, 0);
        collectiblesTable = new JTable(tableModel);
        collectiblesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(collectiblesTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        refreshTable();


        //Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // To show more info
        JButton moreInfoButton = new JButton("More Info");
        moreInfoButton.addActionListener(e -> showMoreInfo());
        buttonsPanel.add(moreInfoButton);

        // To edit
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editCollectible());
        buttonsPanel.add(editButton);

        // To sort items
        JButton sortButton = new JButton("Sort Items");
        sortButton.addActionListener(e -> sortItems());
        buttonsPanel.add(sortButton);

        // to generate statistics
        JButton statsButton = new JButton("Generate Statistics");
        statsButton.addActionListener(e -> generateStatistics());
        buttonsPanel.add(statsButton);

        // To Search
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchItems());
        buttonsPanel.add(searchButton);

        // To add item
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> addCollectible());
        buttonsPanel.add(addButton);

        // To Load file
        JButton loadButton = new JButton("Load File");
        loadButton.addActionListener(e -> loadFileData());
        buttonsPanel.add(loadButton);

        // Adding panels to the frame
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);

    }
    // Refresh the table with current location
    private void refreshTable() {
        tableModel.setRowCount(0);  // Clear the table
        for (Collectible collectible : collection.getCollectibles()){
            tableModel.addRow(new Object[] {
                    collectible.getTitle(),
                    collectible.getClass().getSimpleName(),
                    collectible.getYearEstimate().toString(),
                    collectible.getUniqueId(),
                    collectible.getOwner(),
                    collectible.getCondition(),
                    collectible.getBiddingPrice()
            });
        }
    }

    // Display detailed information about the selected collectible
    private void showMoreInfo() {
        int selectedRow = collectiblesTable.getSelectedRow();
        if (selectedRow >= 0) {
            Collectible selectedItem = collection.getCollectibles().get(selectedRow);
            JOptionPane.showMessageDialog(frame, selectedItem.toString(), "More Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Open a dialog to edit the selected collectible
    private void editCollectible() {
        int selectedRow = collectiblesTable.getSelectedRow();
        if (selectedRow >= 0) {
            Collectible selectedItem = collection.getCollectibles().get(selectedRow);
            EditCollectibleDialog editDialog = new EditCollectibleDialog(frame, selectedItem);
            editDialog.setVisible(true);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frame, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Sort items based on user-selected criteria
    private void sortItems() {
        String[] options = {"ID", "Price", "YearEstimate"};
        String choice = (String) JOptionPane.showInputDialog(
                frame, "Sort by: ", "Sort Items", JOptionPane.QUESTION_MESSAGE, null, options, options[0]
        );
        if (choice != null) {
            Comparator<Collectible> comparator;
            switch (choice) {
                case "ID":
                    comparator = Comparator.comparing(Collectible::getUniqueId);
                    break;
                case "Price":
                    comparator = Comparator.comparing(Collectible::getBiddingPrice);
                    break;
                default:
                    comparator = Comparator.comparing(c -> c.getYearEstimate().getLowEstimate());
                    break;
            }

            Collections.sort(collection.getCollectibles(), comparator);
            refreshTable();
        }
    }

    // Generate statistics for the collection
    private void generateStatistics() {
        // Generate statistics for all collectibles
        String stats = collection.generateStatistics();
        // Display the statistics in a scrollable dialog
        JTextArea textArea = new JTextArea(stats);
        textArea.setEditable(false); // Making the text area readable only
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Statistics generated successfully.", JOptionPane.INFORMATION_MESSAGE);
    }

    // Open a dialog to add new collectible
    private void addCollectible() {
        AddCollectibleDialog addDialog = new AddCollectibleDialog(frame, collection);
        addDialog.setVisible(true);
        refreshTable();
    }
    // Searching items by title and display the results in the table
    private void searchItems() {
        String searchItem = JOptionPane.showInputDialog(frame, "Enter Search Item: ", "Search", JOptionPane.QUESTION_MESSAGE);
        if (searchItem != null && !searchItem.trim().isEmpty()){
            DefaultTableModel searchModel = new DefaultTableModel(new String[]{"Title", "Type","Id", "Price", "Condition", "Owner"}, 0);
            for (Collectible collectible : collection.getCollectibles()) {
                if (collectible.getTitle().toLowerCase().contains(searchItem.toLowerCase())) {
                    searchModel.addRow(new Object[]{
                            collectible.getTitle(),
                            collectible.getClass().getSimpleName(),
                            collectible.getUniqueId(),
                            collectible.getBiddingPrice(),
                            collectible.getCondition(),
                            collectible.getOwner(),
                    });
                }
            }
            collectiblesTable.setModel(searchModel); // Update the table with search results
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a search item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFileData(){
        // Opening a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Read the file and update the collection
            collection.readFromCSV(filePath);
            // Refresh the table to display the updated contents
            refreshTable();

            // Notifying the user
            JOptionPane.showMessageDialog(frame, "File Loaded Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "No file selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Entry point to run the application
    public static void main(String[] args) {
        CollectionList collection = new CollectionList();
        collection.loadSampleData(); // Loading sample data to the collection
        javax.swing.SwingUtilities.invokeLater(() -> new AuctionHouseUI(collection));
        collection.listAll();
        new AuctionHouseUI(collection); // launching the UI
    }
}
