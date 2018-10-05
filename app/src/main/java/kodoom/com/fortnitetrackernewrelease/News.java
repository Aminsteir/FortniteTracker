package kodoom.com.fortnitetrackernewrelease;

import android.app.Dialog;

public class News {
    private String imageURI;
    private String headline;
    private String description;

    public News(String imageURI, String headline, String description) {
        this.imageURI = imageURI;
        this.headline = headline;
        this.description = description;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
