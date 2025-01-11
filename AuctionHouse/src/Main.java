import java.util.ArrayList; //used to store elements
import java.util.Scanner; //used to take the user inputs

public class Main{

    public static void main(String[] args) {

        System.out.println("Hello Auction House");

        CollectionList collection = new CollectionList();  //CollectionList instance

        // Load sample data into the collection
        collection.loadSampleData();

        // Launching the AuctionHouseUI with the collection List
        javax.swing.SwingUtilities.invokeLater(() -> new AuctionHouseUI(collection));

        // Listing all collectibles
        System.out.println("List of collectibles in the collection: ");
        collection.listAll();

        // Scanner for user input to provide CSV file path
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to your CSV File: ");
        String path = scanner.nextLine();

        // Reading CSV files to add collectibles
        collection.readFromCSV(path);

        // Listing updated collection after reading CSV
        System.out.print("\nUpdated list of collectibles in the collection: ");
        collection.listAll();

        // Search Books
        System.out.print("\nEnter the title of the book: ");
        String bookTitle = scanner.nextLine();
        ArrayList<Books> foundBooksByTitle = collection.findBooksByTitle(bookTitle);
        displayBookResults(foundBooksByTitle, bookTitle, "Title");

        System.out.println("\nEnter the author of the book: ");
        String bookAuthor = scanner.nextLine();
        ArrayList<Books> foundBooksByAuthor = collection.findBooksByAuthor(bookAuthor);
        displayBookResults(foundBooksByAuthor, bookAuthor, "Author");

        System.out.println("\nEnter the genre of the book: ");
        String bookGenre = scanner.nextLine();
        ArrayList<Books> foundBooksByGenre = collection.findBooksByGenre(bookGenre);
        displayBookResults(foundBooksByGenre, bookGenre,  "Genre");

        // Search Cars
        System.out.println("\nEnter the title of the Car: ");
        String carTitle = scanner.nextLine();
        ArrayList<Car> foundCarsByTitle = collection.findCarsByTitle(carTitle);
        displayCarResults(foundCarsByTitle, carTitle, "title");

        System.out.println("\nEnter the model of the car: ");
        String carModel = scanner.nextLine();
        ArrayList<Car> foundCarsByModel = collection.findCarsByModel(carModel);
        displayCarResults(foundCarsByModel, carModel,  "Model");

        System.out.println("\nEnter the year of the car: ");
        int caryear = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Car> foundCarsByYear = collection.findCarsByYear(caryear);
        displayCarResults(foundCarsByYear, Integer.toString(caryear), "year");

        //Search Paintings
        System.out.println("\nEnter the title of the Painting: ");
        String paintingTitle = scanner.nextLine();
        ArrayList<Painting> foundPaintingsByTitle = collection.findPaintingsByTitle(paintingTitle);
        displayPaintingResults(foundPaintingsByTitle, paintingTitle, "title");

        System.out.println("\nEnter the style of the Painting: ");
        String paintingStyle = scanner.nextLine();
        ArrayList<Painting> foundPaintingsByStyle = collection.findPaintingsByStyle(paintingStyle);
        displayPaintingResults(foundPaintingsByStyle, paintingStyle, "style");

        // Search Jewellery
        System.out.println("\nEnter the title of the Jewellery: ");
        String jewelleryTitle = scanner.nextLine();
        ArrayList<Jewellery> foundJewelleryByTitle = collection.findJewelleriesByTitle(jewelleryTitle);
        displayJewelleryResults(foundJewelleryByTitle,jewelleryTitle, "title");

        System.out.println("\nEnter the material of the Jewellery: ");
        String jewelleryMaterial = scanner.nextLine();
        ArrayList<Jewellery> foundJewelleryByMaterial = collection.findJewelleriesByMaterial(jewelleryMaterial);
        displayJewelleryResults(foundJewelleryByMaterial, jewelleryMaterial, "material");

        System.out.println("\nEnter the type of the Jewellery: ");
        String jewelleryType = scanner.nextLine();
        ArrayList<Jewellery> foundJewelleryByType = collection.findJewelleriesByType(jewelleryType);
        displayJewelleryResults(foundJewelleryByType, jewelleryType, "type");

        // Generate Statistics
        System.out.println("\nGenerating Statistics: ");
        collection.generateStatistics();

        scanner.close();

    }

    // Display methods for each type
    private static void displayBookResults(ArrayList<Books> books, String query, String searchType) {
        if (books.isEmpty()) {
            System.out.println("No books found" + searchType + "\"" + query + "\".");
        } else {
            System.out.println("Books Found with " + searchType + "\"" + query + "\":");
            for (Books book : books) {
                System.out.println("Title: " + book.getTitle() + " Author: " + book.getAuthor() + " Genre: " + book.getGenre());
            }
        }
    }

    private static void displayCarResults(ArrayList<Car> cars, String query, String searchType) {
        if (cars.isEmpty()) {
            System.out.println("No cars found" + searchType + "\"" + query + "\".");
        } else {
            System.out.println("Cars Found with " + searchType + "\"" + query + "\":");
            for (Car car : cars) {
                System.out.println("Title: " + car.getTitle() + ",Model: " + car.getModel() + ", Year: ");
            }
        }
    }

    private static void displayPaintingResults(ArrayList<Painting> paintings, String query, String searchType) {
        if (paintings.isEmpty()) {
            System.out.println("No paintings found" + searchType + "\"" + query + "\".");
        } else {
            System.out.println("Paintings Found with " + searchType + "\"" + query + "\":");
            for (Painting painting : paintings) {
                System.out.println("Title: " + painting.getTitle() + ", Style: " + painting.getStyle());
            }
        }
    }

    private static void displayJewelleryResults(ArrayList<Jewellery> jewellerys, String query, String searchType) {
        if (jewellerys.isEmpty()) {
            System.out.println("No jewelleries found" + searchType + "\"" + query + "\".");
        } else {
            System.out.println("Jewelleries Found with " + searchType + "\"" + query + "\":");
            for (Jewellery jewellery : jewellerys) {
                System.out.println("Title: " + jewellery.getTitle() + ", Material: " + jewellery.getMaterial() + ", Type: " + jewellery.getType());
            }
        }
    }

}