package kodoom.com.fortnitetrackernewrelease;

public class UpcomingItems {
    private String imageURI;
    private String itemName;
    private String itemType;

    public UpcomingItems (String imageURI, String itemName, String itemType) {
        this.imageURI = imageURI;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public String getImageURI() {
        return imageURI;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }
}
