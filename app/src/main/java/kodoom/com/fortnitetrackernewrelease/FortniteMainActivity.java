package kodoom.com.fortnitetrackernewrelease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import kodoom.com.fortnitetrackernewrelease.News;
import kodoom.com.fortnitetrackernewrelease.NewsDataModel;
import kodoom.com.fortnitetrackernewrelease.NewsListAdapter;
import kodoom.com.fortnitetrackernewrelease.R;
import kodoom.com.fortnitetrackernewrelease.activity_location;
import kodoom.com.fortnitetrackernewrelease.fortniteStoreActivity;
import kodoom.com.fortnitetrackernewrelease.playerStatsActivity;
import kodoom.com.fortnitetrackernewrelease.upcomingItemsActivity;


public class FortniteMainActivity extends AppCompatActivity {

    //    final String playerStats = "https://fortnite-public-api.theapinetwork.com/prod09/users/public/br_stats/";
//    final String fortniteStore = "https://fortnite-public-api.theapinetwork.com/prod09/store/get";
//    final String upcomingItems = "https://fortnite-public-api.theapinetwork.com/prod09/upcoming/get";
//    final String challenges = "https://fortnite-public-api.theapinetwork.com/prod09/challenges/get";
    final String newsURI = "https://fortnite-public-api.theapinetwork.com/prod09/br_motd/get";
    final String API_KEY = "a38733c595bcff8adbef7fa2854b8fc5";
    final String newsLink = "https://www.epicgames.com/fortnite/en-US/news";

    Button playerStatsButton;
    Button fortniteStoreButton;
    Button upcomingItemsButton;
    Button randomLocationButton;
    ListView newsListView;
    ImageView newsImage;
    TextView newsHeadline;
    TextView newsDescription;

    ArrayList<News> newsList = new ArrayList<>();

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortnite_main);

        /*MobileAds.initialize(this, "ca-app-pub-1989206618089867~6787489885");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1989206618089867/9394731107");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded () {
                Log.d("Tracker", "ad loaded");
                mInterstitialAd.show();
            }
        });*/

        //TODO: WORK ON MOBILE ADS
        //TODO: GOOD LINK: https://developers.google.com/admob/android/interstitial
        //TODO: ADMOB INTERSTITIAL TAKES FOREVER TO LOAD (commented for now)


        playerStatsButton = findViewById(R.id.playerStatsButton);
        fortniteStoreButton = findViewById(R.id.currentStoreButton);
        upcomingItemsButton = findViewById(R.id.upcomingItemsButton);
        randomLocationButton = findViewById(R.id.randomLocationButton);
        newsListView = findViewById(R.id.newsListView);
        newsImage = findViewById(R.id.newsImage);
        newsHeadline = findViewById(R.id.newsHeadline);
        newsDescription = findViewById(R.id.newsDescription);

        playerStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FortniteMainActivity.this, playerStatsActivity.class);
                startActivity(intent);
            }
        });

        fortniteStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FortniteMainActivity.this, fortniteStoreActivity.class);
                startActivity(intent);
            }
        });

        upcomingItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FortniteMainActivity.this, upcomingItemsActivity.class);
                startActivity(intent);
            }
        });

        randomLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FortniteMainActivity.this, activity_location.class);
                startActivity(intent);
            }
        });

        getNews();
    }

    private void getNews () {
        Log.d("Tracker", "getNews() function called");

        RequestParams params = new RequestParams();

        letsDoSomeNetworking(params);
    }

    private void letsDoSomeNetworking (RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", API_KEY);
        client.post(newsURI, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                Log.d("Tracker", "Success! JSON: " + response.toString());

                NewsDataModel newDataModel = NewsDataModel.fromJson(response);

                ArrayList<News> news = newDataModel.getNews();

                setListView(news);
            }

            @Override
            public void onFailure (int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e("Tracker", "Fail " + e.toString());
                Log.d("Tracker", "Status Code " + statusCode);
                Toast.makeText(FortniteMainActivity.this, "News Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListView(ArrayList<News> news) {
        NewsListAdapter newsListAdapter = new NewsListAdapter(this, R.layout.fortnite_main_adapter, news);
        newsListView.setAdapter(newsListAdapter);
    }

}
