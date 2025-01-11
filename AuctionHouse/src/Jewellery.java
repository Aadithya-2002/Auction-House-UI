public class Jewellery extends Collectible{
    private String type;
    private String material;
    private String gems;

    // Constructor
    public Jewellery(String title, YearEstimate yearEstimate, int UniqueId, String owner, String Condition, double biddingPrice, String type, String material, String gems) {
        super(title, yearEstimate, UniqueId, owner, Condition, biddingPrice);
        this.type = type;
        this.material = material;
        this.gems = gems;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGems() {
        return gems;
    }
    public void setGems(String gems) {
        this.gems = gems;
    }

    // Factory method
    public static Jewellery fromCSV(String[] data) throws IllegalArgumentException{
        try {
            String title = data[1];
            YearEstimate yearEstimate = new YearEstimate(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            int UniqueId = Integer.parseInt(data[3]);
            String owner = data[4];
            String condition = data[5];
            double biddingPrice = Double.parseDouble(data[6]);
            String type = data[7];
            String material = data[8];
            String gems = data[9];
            return new Jewellery(title, yearEstimate, UniqueId, owner, condition, biddingPrice, type, material, gems);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Error parsing Jewellery data: " + e.getMessage());
        }
    }

    @Override
    public Object[] toTableRow(){
        return new Object[]{
                "Jewellery",
                getTitle(),
                getYearEstimate() != null ? getYearEstimate().toString() : "N/A",
                getUniqueId(),
                getOwner(),
                getCondition(),
                getBiddingPrice(),
                type,
                material,
                gems
        };
    }

}
