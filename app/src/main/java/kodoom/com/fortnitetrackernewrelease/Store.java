package kodoom.com.fortnitetrackernewrelease;

public class Store {
    private String imageURI;
    private String itemName;
    private String itemCost;

    public Store(String imageURI, String itemName, String itemCost) {
        this.imageURI = imageURI;
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getitemName() {
        return itemName;
    }

    public void setitemName(String itemName) {
        this.itemName = itemName;
    }

    public String getitemCost() {
        return itemCost;
    }

    public void setitemCost(String itemCost) {
        this.itemCost = itemCost;
    }
}
