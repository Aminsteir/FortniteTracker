package kodoom.com.fortnitetrackernewrelease;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class activity_location extends AppCompatActivity {

    TextView mLocation;
    Button locationButton;
    ArrayList<String> locationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLocation = findViewById(R.id.randomLocation);
        locationButton = findViewById(R.id.getLocationButton);

        locationList.add("Dusty Divot");
        locationList.add("Fatal Fields");
        locationList.add("Flush Factory");
        locationList.add("Greasy Grove");
        locationList.add("Haunted Hills");
        locationList.add("Junk Junction");
        locationList.add("Lazy Links");
        locationList.add("Lonely Lodge");
        locationList.add("Loot Lake");
        locationList.add("Lucky Landing");
        locationList.add("Paradise Palms");
        locationList.add("Pleasant Park");
        locationList.add("Retail Row");
        locationList.add("Risky Reels");
        locationList.add("Salty Springs");
        locationList.add("Shifty Shafts");
        locationList.add("Snobby Shores");
        locationList.add("Tilted Towers");
        locationList.add("Tomato Temple");
        locationList.add("Wailing Woods");

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomLocation();
            }
        });
    }

    private void getRandomLocation () {
        Random random = new Random();
        int randomNumber = random.nextInt(20);
        mLocation.setText(locationList.get(randomNumber));
    }
}
