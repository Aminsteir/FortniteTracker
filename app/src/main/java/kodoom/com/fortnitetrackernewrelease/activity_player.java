package kodoom.com.fortnitetrackernewrelease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class activity_player extends AppCompatActivity {

    final String PLAYER_STATS_URL = "https://fortnite-public-api.theapinetwork.com/prod09/users/public/br_stats";
    final String PLAYER_ID_URL = "https://fortnite-public-api.theapinetwork.com/prod09/users/id";
    final String API_KEY = "a38733c595bcff8adbef7fa2854b8fc5";

    TextView mPlayerNameTextView;
    TextView mTotalWinsTextView;
    TextView mTotalKillsTextView;
    TextView mTotalMatchesTextView;
    TextView mTotalWinRatioTextView;
    TextView mSoloWinsTextView;
    TextView mSoloKillsTextView;
    TextView mSoloMatchesTextView;
    TextView mSoloWinRatioTextView;
    TextView mDuoWinsTextView;
    TextView mDuoKillsTextView;
    TextView mDuoMatchesTextView;
    TextView mDuoWinRatioTextView;
    TextView mSquadWinsTextView;
    TextView mSquadKillsTextView;
    TextView mSquadMatchesTextView;
    TextView mSquadWinRatioTextView;

    SwitchCompat soloSwitch;
    SwitchCompat duoSwitch;
    SwitchCompat squadSwitch;

    LinearLayout soloLayout;
    LinearLayout duoLayout;
    LinearLayout squadLayout;

    private String username = "Ninja";
    private String userID = "";
    private String platform = "pc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mPlayerNameTextView = findViewById(R.id.playerName);
        mTotalWinsTextView = findViewById(R.id.totalWinsTextView);
        mTotalKillsTextView = findViewById(R.id.totalKillsTextView);
        mTotalMatchesTextView = findViewById(R.id.totalMatchesPlayedTextView);
        mTotalWinRatioTextView = findViewById(R.id.totalWinRatio);
        mSoloWinsTextView = findViewById(R.id.soloWins);
        mSoloKillsTextView = findViewById(R.id.soloKills);
        mSoloMatchesTextView = findViewById(R.id.soloMatches);
        mSoloWinRatioTextView = findViewById(R.id.soloWinRatio);
        mDuoWinsTextView = findViewById(R.id.duoWins);
        mDuoKillsTextView = findViewById(R.id.duoKills);
        mDuoMatchesTextView = findViewById(R.id.duoMatches);
        mDuoWinRatioTextView = findViewById(R.id.duoWinRatio);
        mSquadWinsTextView = findViewById(R.id.squadWins);
        mSquadKillsTextView = findViewById(R.id.squadKills);
        mSquadMatchesTextView = findViewById(R.id.squadMatches);
        mSquadWinRatioTextView = findViewById(R.id.squadWinRatio);

        soloSwitch = findViewById(R.id.soloSwitch);
        duoSwitch = findViewById(R.id.duoSwitch);
        squadSwitch = findViewById(R.id.squadSwitch);

        soloLayout = findViewById(R.id.soloLayout);
        duoLayout = findViewById(R.id.duoLayout);
        squadLayout = findViewById(R.id.squadLayout);

        soloSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!soloSwitch.isChecked()) {
                    soloLayout.setVisibility(View.GONE);
                } else {
                    soloLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        duoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!duoSwitch.isChecked()) {
                    duoLayout.setVisibility(View.GONE);
                } else {
                    duoLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        squadSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!squadSwitch.isChecked()) {
                    squadLayout.setVisibility(View.GONE);
                } else {
                    squadLayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent myIntent = getIntent();
        username = myIntent.getStringExtra("playerName");
        platform = myIntent.getStringExtra("platform");

        /*if (!soloSwitch.isChecked()) {
            soloLayout.setVisibility(View.GONE);
        }

        if (!duoSwitch.isChecked()) {
            duoLayout.setVisibility(View.GONE);
        }

        if (!squadSwitch.isChecked()) {
            squadLayout.setVisibility(View.GONE);
        }*/

        getPlayerStats();
    }

    private void getPlayerStats () {
        RequestParams params = new RequestParams();
        params.put("username", username);
        getPlayerUID(params, PLAYER_ID_URL);
    }

    private void letsDoSomeNetworking(RequestParams params, String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", API_KEY);
        client.post(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                Log.d("Tracker", "Success! JSON: " + response.toString());

                PlayerDataModel playerDataModel = PlayerDataModel.fromJson(response);

                if (playerDataModel.getPlayerName() == null) {
                    Toast.makeText(activity_player.this, "Person Not Found", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_player.this, playerStatsActivity.class);
                    startActivity(intent);
                    return;
                } else if (playerDataModel.getTotalMatches() == "0") {
                    Toast.makeText(activity_player.this, "Person Not Found", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_player.this, playerStatsActivity.class);
                    startActivity(intent);
                    return;
                }

                updateUI(playerDataModel);
            }

            @Override
            public void onFailure (int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e("Tracker", "Fail " + e.toString());
                Log.d("Tracker", "Status Code " + statusCode);
                Toast.makeText(activity_player.this, "Player Stats Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPlayerUID (RequestParams params, String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", API_KEY);
        client.post(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                Log.d("Tracker", "Success! JSON: " + response.toString());

                userID = PlayerDataModel.fromJsonUID(response);

                RequestParams newParams = new RequestParams();
                newParams.put("user_id", userID);
                newParams.put("platform", platform);
                newParams.put("window", "alltime");

                letsDoSomeNetworking(newParams, PLAYER_STATS_URL);
            }

            @Override
            public void onFailure (int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e("Tracker", "Fail " + e.toString());
                Log.d("Tracker", "Status Code " + statusCode);
                Toast.makeText(activity_player.this, "Player Stats Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI (PlayerDataModel playerDataModel) {
        mPlayerNameTextView.setText(playerDataModel.getPlayerName());
        mTotalWinsTextView.setText("Total Wins: " + playerDataModel.getTotalWins());
        mTotalKillsTextView.setText("Total Kills: " + playerDataModel.getTotalKills());
        mTotalMatchesTextView.setText("Total Matches: " + playerDataModel.getTotalMatches());
        mTotalWinRatioTextView.setText("Total Win Ratio: " + playerDataModel.getTotalWinRatio());
        mSoloWinsTextView.setText("Wins: " + playerDataModel.getSoloWins());
        mSoloKillsTextView.setText("Kills: " + playerDataModel.getSoloKills());
        mSoloMatchesTextView.setText("Matches: " + playerDataModel.getSoloMatches());
        mSoloWinRatioTextView.setText("Win Ratio: " + playerDataModel.getSoloWinRatio());
        mDuoWinsTextView.setText("Wins: " + playerDataModel.getDuoWins());
        mDuoKillsTextView.setText("Kills: " + playerDataModel.getDuoKills());
        mDuoMatchesTextView.setText("Matches: " + playerDataModel.getDuoMatches());
        mDuoWinRatioTextView.setText("Win Ratio: " + playerDataModel.getDuoWinRatio());
        mSquadWinsTextView.setText("Wins: " + playerDataModel.getSquadWins());
        mSquadKillsTextView.setText("Kills: " + playerDataModel.getSquadKills());
        mSquadMatchesTextView.setText("Matches: " + playerDataModel.getSquadMatches());
        mSquadWinRatioTextView.setText("Win Ratio: " + playerDataModel.getSquadWinRatio());
    }

}
