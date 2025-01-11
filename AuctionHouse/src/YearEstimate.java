public class YearEstimate {
    private int lowEstimate;
    private int highEstimate;

    // Constructor
    public YearEstimate(int lowEstimate, int highEstimate) {
        this.lowEstimate = lowEstimate;
        this.highEstimate = highEstimate;
    }

     // Getters and setters
    public int getLowEstimate() {
        return lowEstimate;
    }

    public int getHighEstimate() {
        return highEstimate;
    }

    public int getMiddleEstimate() {
        return (lowEstimate + highEstimate) / 2;
    }

    // To String Method
    @Override
    public String toString() {
        return "Year Range: " + lowEstimate + " - " + highEstimate;
    }
}
