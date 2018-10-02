package kodoom.com.fortnitetrackernewrelease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class playerStatsActivity extends AppCompatActivity {

    EditText playerName;
    RadioButton pcRadio;
    RadioButton ps4Radio;
    RadioButton xboxRadio;
    Button getPlayer;

    boolean platformChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        playerName = findViewById(R.id.playerName);

        pcRadio = findViewById(R.id.pcRadio);
        ps4Radio = findViewById(R.id.ps4Radio);
        xboxRadio = findViewById(R.id.xboxRadio);

        getPlayer = findViewById(R.id.getPlayerButton);

        platformChecked = true;

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
        if (pcRadio.isChecked()) {
            intent.putExtra("platform", "pc");
        } else if (ps4Radio.isChecked()) {
            intent.putExtra("platform", "ps4");
        } else if (xboxRadio.isChecked()) {
            intent.putExtra("platform", "xbox");
        } else {
            return;
        }
        startActivity(intent);
    }
}
