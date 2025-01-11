public class Collectible {
    private String title;
    private YearEstimate yearEstimate;
    private String owner;
    private String condition;
    private double biddingPrice;
    private int uniqueId;


    // Constructor for collectible
    public Collectible(String title, YearEstimate yearEstimate, int uniqueId, String owner, String condition, double biddingPrice) {
        this.title = title;
        this.yearEstimate = yearEstimate;
        this.owner = owner;
        this.condition = condition;
        this.biddingPrice = biddingPrice;
        this.uniqueId = uniqueId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public YearEstimate getYearEstimate() {
        return yearEstimate;
    }

    public void setYearEstimate(YearEstimate yearEstimate) {
        this.yearEstimate = yearEstimate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    public double getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(double biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }
    // Converting to a table row
    public Object[] toTableRow(){
        return new Object[]{
                this.getClass().getSimpleName(),
                title,
                uniqueId,
                biddingPrice,
                condition,
                owner,
                yearEstimate != null ? yearEstimate.toString() : "N/A"
        };
    }
    // To string method for displaying collectible details
    @Override
    public String toString() {
        return "Collectible: " + title + "(" + uniqueId + ")\n" + "Owner: "+ owner + "\n" + "Condition: " + condition + "\n" + "Bidding Price: " + biddingPrice + "\n" +
                "Condition: " + condition + "\n" + "Bidding Price: $" + biddingPrice + "\n" + "Year Estimate: " + yearEstimate.toString();
    }



    }
