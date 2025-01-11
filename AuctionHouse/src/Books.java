public class Books extends Collectible {
    private String author;
    private int edition;
    private String genre;


    //Overloaded constructor accepting individual parameters
    public Books(String title, YearEstimate yearEstimate, int uniqueId, String owner, String condition, double biddingPrice, String author, int edition, String genre) {
        super(title, yearEstimate, uniqueId, owner, condition, biddingPrice);
        this.author = author;
        this.edition = edition;
        this.genre = genre;
    }

    public String getAuthor() {  //gets the value for author
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {  //gets the value for edition
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getGenre() {  //gets the value for genre
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Static method to create Books instance from CSV
    public static Books fromCSV(String[] data) throws IllegalArgumentException {
        try {
            if(data.length < 10) {
                throw new ArrayIndexOutOfBoundsException("Insufficient data in CSV File");
            }
            String title = data[1];
            YearEstimate yearEstimate = new YearEstimate(Integer.parseInt(data[2]), Integer.parseInt(data[2]));
            int uniqueId = Integer.parseInt(data[3]);
            String owner = data[4];
            String condition = data[5];
            double biddingPrice = Double.parseDouble(data[6]);
            String author = data[7];
            int edition = Integer.parseInt(data[8]);
            String genre = data[9];
            return new Books(title, yearEstimate, uniqueId, owner, condition, biddingPrice, author, edition, genre);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Error parsing data: " + e.getMessage());

        }
    }

    @Override
    public Object[] toTableRow(){
        return new Object[]{
                "Book",
                getTitle(),
                getYearEstimate() != null ? getYearEstimate().toString(): "N?A",
                getUniqueId(),
                getOwner(),
                getCondition(),
                getBiddingPrice(),
                author,
                edition,
                genre

        };
    }

    }


