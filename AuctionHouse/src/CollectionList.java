import javax.swing.*;
import java.io.IOException;
import java.util.stream.Collectors;
import java.io.*;
import java.util.*;


public class CollectionList {
    private ArrayList<Collectible> collectibles = new ArrayList<>();

    // Method to retrieve all collectibles
    public List<Collectible> getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    // Method to parse CSV files and Collectibles
    /*public void readFromCSV(String filePath) { //To read the path to csv file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            boolean errorsFound = false;
            while ((line = br.readLine()) != null){

                lineNumber++;
                String[] data = line.split(",");
                System.out.println("Reading from file: " + data);
                if (data.length > 10){
                    JOptionPane.showMessageDialog(null,"Error: Missing fields in line  " + lineNumber + ".\n" + line, "CSV Error", JOptionPane.ERROR_MESSAGE);
                    errorsFound = true;
                    continue;
                }
                // Validating data before creating a collectible object
                if (!isValidData(data)){
                    JOptionPane.showMessageDialog(null, "Error: Missing fields in line " + lineNumber + ".\n" + line, "CSV Error", JOptionPane.ERROR_MESSAGE);
                    errorsFound = true;
                    continue;
                }
                Collectible collectible = createCollectible(data);
                if (collectible != null){
                    addCollectible(collectible);
                }

            }
            if (!errorsFound){
                JOptionPane.showMessageDialog(null, "CSV file loaded successfully!", "CSV Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error reading CSV file!" + e.getMessage(), "CSV Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/


    public void readFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            boolean errorsFound = false;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 1) {
                    continue;
                }
                // Split the line by commas to get individual column values
                String[] data = line.split(",");

               /* // Debugging: Print the split data
                System.out.println("Reading line " + lineNumber + ": " + Arrays.toString(data));

                // Validate column count (replace '10' with your expected column count)
                /*if (data.length < 11) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error: Missing fields in line " + lineNumber + ".\n" + line,
                            "CSV Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    errorsFound = true;
                    continue;
                }



                // Validate individual data fields (you can customize isValidData)
                if (!isValidData(data)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error: Invalid fields in line " + lineNumber + ".\n" + line,
                            "CSV Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    errorsFound = true;
                    continue;
                }
                */
                // Create and add a Collectible object from the parsed data
                Collectible collectible = createCollectible(data);
                if (collectible != null) {
                    addCollectible(collectible); // Adds the object to the collection
                }
            }

           /* // Notify the user if no errors were found
            if (!errorsFound) {
                JOptionPane.showMessageDialog(
                        null,
                        "CSV file loaded successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }*/
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error reading CSV file! " + e.getMessage(),
                    "CSV Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean isValidData(String[] data){
        try {
            // Ensuring numeric fields can be parsed correctly
            double biddingPrice = Double.parseDouble(data[6]);
            int lowYear = Integer.parseInt(data[2]);
            int highYear = Integer.parseInt(data[2]);
            if (lowYear > highYear){
                return false;
            }
            for (String field: data){
                if (field == null || field.trim().isEmpty()){
                    return false;
                }
            }
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private Collectible createCollectible(String[] data) {
        String type = data[0].toLowerCase();

        switch (type) {
            case "book":
                return Books.fromCSV(data);
            case "car":
                return Car.fromCSV(data);
            case "painting":
                return Painting.fromCSV(data);
            case "jewellery":
               return Jewellery.fromCSV(data);

            default:
               // Handle unknown collectible type
               JOptionPane.showMessageDialog(null, "Warning: Unknown Collectible Type" + type + "' found in CSV, Skipping this entry.", "Unknown type", JOptionPane.WARNING_MESSAGE);
               return null;
        }
    }

    // Method to add a collectible to the list
    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);
        System.out.println("Added: " + collectible.getTitle());
    }

    // Method to populate the collection with sample data
    public void loadSampleData() {
        // Sample data for books
        YearEstimate bookYear = new YearEstimate(1813, 1813);
        collectibles.add(new Books("Pride and Prejudice", bookYear, 759, "Olga", "Mint", 9.50, "Jane Austen", 5, "Historical Fiction"));

        bookYear = new YearEstimate(1943, 1946);
        collectibles.add(new Books("The Little Princess", bookYear, 101,"John", "Good", 15.00, "Antoine de Saint", 1,"Children's Fiction"));

        // Sample data for Paintings
        YearEstimate paintingYear = new YearEstimate(1503, 1506);
        collectibles.add(new Painting("Mona Lisa", paintingYear, "Louvre Museum", "Restored", 850.00, 112, "Renaissance", 18.5,10));

        paintingYear = new YearEstimate(1803, 1812);
        collectibles.add(new Painting("Starry Night", paintingYear, "Museum of Modern Art","Excellent", 1200.50,98,"Post-Impressionism", 25.0,21.0));

        // Sample data for Car
        YearEstimate carYear = new YearEstimate(1989, 1987);
        collectibles.add(new Car("Ferrari 250 GTO", carYear, 125,"Diana", "Good", 3000.00, "Ferrari", "250 GTO", true));

        carYear = new YearEstimate(1963, 1967);
        collectibles.add(new Car("Ford Mustang", carYear,125,"Diana", "Good", 3000.00,"Ford", "Mustang", false));

        // Sample data for Jewellery
        YearEstimate jewelleryYear = new YearEstimate(1650, 1703);
        collectibles.add(new Jewellery("Butterfly Ruby", jewelleryYear, 112,"Francis", "Mint",9850.00, "Necklace", "Ruby", "Red Ruby Stone"));

        jewelleryYear = new YearEstimate(1920, 1930);
        collectibles.add(new Jewellery("Art Deco Brooch", jewelleryYear, 150, "Sophia", "Fair", 1500.00,"Brooch", "Gold", "Emerald"));
    }

    // Method to list all elements
    public void listAll(){
        for(Collectible collectible : collectibles){
            System.out.println(collectible);
            System.out.println("-------------------------------------------------------------");
        }
    }

    // Book search method by Title, Author and genre
    public ArrayList<Books> findBooksByTitle(String title){
        ArrayList<Books> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Books && item.getTitle().equalsIgnoreCase(title)){
                results.add((Books)item);
            }
        }
        return results;
    }

    public ArrayList<Books> findBooksByAuthor(String author){
        ArrayList<Books> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Books && ((Books) item).getAuthor().equalsIgnoreCase(author)){
                results.add((Books)item);
            }
        }
        return results;
    }


    public ArrayList<Books> findBooksByGenre(String genre){
        ArrayList<Books> results = new ArrayList<>();
        for(Collectible item : collectibles) {
            if (item instanceof Books && ((Books) item).getGenre().equalsIgnoreCase(genre)) {
                results.add((Books) item);
            }
        }
        return results;
    }

    // Search methods for car By Title, Model, Year
    public ArrayList<Car> findCarsByTitle(String title){
        ArrayList<Car> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Car && item.getTitle().equalsIgnoreCase(title)){
                results.add((Car)item);
            }
        }
        return results;
    }
    public ArrayList<Car> findCarsByModel(String model){
        ArrayList<Car> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Car && ((Car) item).getModel().equalsIgnoreCase(model)){
                results.add((Car)item);
            }
        }
        return results;
    }
    public ArrayList<Car> findCarsByYear(int year){
        ArrayList<Car> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Car && ((Car) item).getYearEstimate().getLowEstimate() <= year
            && ((Car) item).getYearEstimate().getHighEstimate() >= year){
                results.add((Car)item);
            }
        }
        return results;
    }

    // Search methods for Paintings by Title, Style
    public ArrayList<Painting> findPaintingsByTitle(String title){
        ArrayList<Painting> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Painting && item.getTitle().equalsIgnoreCase(title)){
                results.add((Painting)item);
            }
        }
        return results;
    }
    public ArrayList<Painting> findPaintingsByStyle(String style){
        ArrayList<Painting> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Painting && ((Painting) item).getStyle().equalsIgnoreCase(style)){
                results.add((Painting)item);
            }
        }
        return results;
    }

    // Search methods for Jewellery by Title, Material, type
    public ArrayList<Jewellery> findJewelleriesByTitle(String title){
        ArrayList<Jewellery> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Jewellery && item.getTitle().equalsIgnoreCase(title)){
                results.add((Jewellery)item);
            }
        }
        return results;
    }
    public ArrayList<Jewellery> findJewelleriesByMaterial(String material){
        ArrayList<Jewellery> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Jewellery && ((Jewellery) item).getMaterial().equalsIgnoreCase(material)){
                results.add((Jewellery)item);
            }
        }
        return results;
    }
    public ArrayList<Jewellery> findJewelleriesByType(String type){
        ArrayList<Jewellery> results = new ArrayList<>();
        for(Collectible item : collectibles){
            if(item instanceof Jewellery && ((Jewellery)item).getType().equalsIgnoreCase(type)){
                results.add((Jewellery)item);
            }
        }
        return results;
    }

    // Generating Statistics for each type of collectible
    public String generateStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append(generateTypeStatistics("Book", Books.class));
        stats.append(generateTypeStatistics("Car", Car.class));
        stats.append(generateTypeStatistics("Painting", Painting.class));
        stats.append(generateTypeStatistics("Jewellery", Jewellery.class));
        return stats.toString();
    }

    // Method to generate statistics for a specific collectible types
    public <T extends Collectible> String generateTypeStatistics(String typeName, Class<T> typeclass) {
        StringBuilder stats = new StringBuilder();
        stats.append("Generating Statistics for ").append(typeName).append("\n");

        // Filtering collectibles to only accept the specified type
        ArrayList<T> items = new ArrayList<>();
        for(Collectible collectible : collectibles){
            if(typeclass.isInstance(collectible)){
                items.add(typeclass.cast(collectible));
            }
        }

        if (items.isEmpty()){
            stats.append("No items found for type: ").append(typeName).append("\n");
            return null;
        }

        // Calculating total count
        stats.append("Total count: ").append(items.size()).append("\n");


    // Calculating oldest and newest items
    T oldestItem = Collections.min(items, (c1, c2) -> Integer.compare(
            c1.getYearEstimate().getLowEstimate(), c2.getYearEstimate().getLowEstimate()));
    T newestItem = Collections.max(items, (c1, c2) -> Integer.compare(
            c1.getYearEstimate().getHighEstimate(), c2.getYearEstimate().getHighEstimate()));

    stats.append("Oldest item: ").append(oldestItem.getTitle()).append("(")
            .append(oldestItem.getYearEstimate().getLowEstimate()).append(")\n ");
    stats.append("Newest item: ").append(newestItem.getTitle()).append("(")
            .append(newestItem.getYearEstimate().getHighEstimate()).append(")\n ");

    // Calculating most and least expensive items based on Bidding Price
    T cheapestItem = Collections.min(items, (c1, c2) -> Double.compare(
            c1.getBiddingPrice(), c2.getBiddingPrice()));
    T mostExpensiveItem = Collections.max(items, (c1, c2) -> Double.compare(
            c1.getBiddingPrice(), c2.getBiddingPrice()));

    stats.append("Cheapest item: ").append(cheapestItem.getTitle()).append("(")
             .append(cheapestItem.getBiddingPrice()).append(")\n ");
    stats.append("Most expensive item: ").append(mostExpensiveItem.getTitle()).append("(")
             .append(mostExpensiveItem.getBiddingPrice()).append(")\n ");

    // Calculating average and standard deviation for Bidding Price
    double totalPrice = items.stream().mapToDouble(Collectible::getBiddingPrice).sum();
    double averagePrice = totalPrice / items.size();
    double variance = items.stream().mapToDouble(item -> Math.pow(item.getBiddingPrice() - averagePrice, 2 )).sum() / items.size();
    double standardDeviation = Math.sqrt(variance);
    stats.append("Average Bidding price: $").append(String.format("%2f", averagePrice)).append("\n");
    stats.append("Standard deviation: $").append(String.format("%.2f", standardDeviation)).append("\n");
    return stats.toString();
    }

    public List<Collectible> getTop3YearEstimateDifference(){
        return collectibles.stream()
                .filter(c -> c.getYearEstimate() != null)
                .sorted((c1, c2) -> {
                    int diff1 = Math.abs(c1.getYearEstimate().getHighEstimate() - c1.getYearEstimate().getLowEstimate());
                    int diff2 = Math.abs(c2.getYearEstimate().getHighEstimate() - c2.getYearEstimate().getLowEstimate());
                    return Integer.compare(diff1, diff2);
                })
                .limit(3)
                .collect(Collectors.toList());
    }

}
