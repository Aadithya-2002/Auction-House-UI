public class Painting extends Collectible{
    private String style;
    private double height;
    private double width;

    // Constructor
    public Painting(String title, YearEstimate yearEstimate, String owner, String condition, double biddingPrice, int uniqueId, String style, double height, double width) {
        super(title, yearEstimate, uniqueId, owner, condition, biddingPrice);
        this.style = style;
        this.height = height;
        this.width = width;

    }

    // Getters and Setters for attributes
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    // Factory Method
    public static Painting fromCSV(String[] data) throws IllegalArgumentException {
        try {
            String title = data[1];
            YearEstimate yearEstimate = new YearEstimate(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            int uniqueId = Integer.parseInt(data[3]);
            String owner = data[4];
            String condition = data[5];
            double biddingPrice = Double.parseDouble(data[6]);
            String style = data[7];
            double height = Double.parseDouble(data[8]);
            double width = Double.parseDouble(data[9]);
            return new Painting(title, yearEstimate, owner, condition, biddingPrice, uniqueId, style, height, width);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Error parsing line: " + e.getMessage());
        }
    }

    @Override
    public Object[] toTableRow(){
        return new Object[]{
                "Painting",
                getTitle(),
                getYearEstimate() != null ? getYearEstimate().toString() : "N/A",
                getOwner(),
                getCondition(),
                getBiddingPrice(),
                getUniqueId(),
                style,
                height,
                width
        };
    }
}