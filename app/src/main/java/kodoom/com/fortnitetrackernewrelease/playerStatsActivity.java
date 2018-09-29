package kodoom.com.fortnitetrackernewrelease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class playerStatsActivity extends AppCompatActivity {

    EditText playerName;
    EditText platform;
    Button getPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        playerName = findViewById(R.id.playerName);
        //platform = findViewById(R.id.platform);
        getPlayer = findViewById(R.id.getPlayerButton);

        getPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPlayer();
            }
        });

        playerName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                getPlayer();
                return false;
            }
        });
    }

    private void getPlayer () {
        Intent intent = new Intent(playerStatsActivity.this, activity_player.class);
        intent.putExtra("playerName", playerName.getText().toString());
        //intent.putExtra("platform", platform.getText().toString());
        startActivity(intent);
    }
}
