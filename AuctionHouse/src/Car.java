public class Car extends Collectible {
    private String make;
    private String model;
    private boolean isServiced;

    // Constructor
    public Car(String title, YearEstimate yearEstimate, int uniqueId, String owner, String condition, double biddingPrice, String make, String model, boolean isServiced) {
        super(title, yearEstimate, uniqueId, owner, condition, biddingPrice);
        this.make = make;
        this.model = model;
        this.isServiced = isServiced;
    }

    // Getters and setters
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public boolean isIsServiced() {
        return isServiced;
    }
    public void setIsServiced(boolean isServiced) {
        this.isServiced = isServiced;
    }

    // Factory method
    public static Car fromCSV(String[] data) throws IllegalArgumentException {
        try {
            String title = data[1];
            YearEstimate yearEstimate = new YearEstimate(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            int uniqueId = Integer.parseInt(data[3]);
            String owner = data[4];
            String condition = data[5];
            double biddingPrice = Double.parseDouble(data[6]);
            String make = data[7];
            String model = data[8];
            boolean isServiced = Boolean.parseBoolean(data[9]);
            return new Car(title, yearEstimate, uniqueId, owner, condition, biddingPrice, make, model, isServiced);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException("Error parsing Car Data: " + e.getMessage());
    }
    }

    @Override
    public Object[] toTableRow(){
        return new Object[]{
                "Car",
                getTitle(),
                getYearEstimate() != null ? getYearEstimate().toString() : "N/A",
                getUniqueId(),
                getOwner(),
                getCondition(),
                getBiddingPrice(),
                make,
                model,
                isServiced
        };
    }
}
