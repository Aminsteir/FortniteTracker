package kodoom.com.fortnitetrackernewrelease;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class upcomingItemsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    final String UPCOMING_URL = "https://fortnite-public-api.theapinetwork.com/prod09/upcoming/get";

    final String API_ID = "a38733c595bcff8adbef7fa2854b8fc5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_items);

        mRecyclerView = findViewById(R.id.upcomingItemsRecyclerView);

        getUpcomingItems();
    }

    private void getUpcomingItems() {
        Log.d("Tracker", "getUpcomingItems() function called");

        RequestParams params = new RequestParams();

        letsDoSomeNetworking(params);
    }

    private void letsDoSomeNetworking(RequestParams params) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Authorization", API_ID);
        asyncHttpClient.post(UPCOMING_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                Log.d("Tracker", "onSuccess() function received");

                UpcomingItemsDataModel upcomingItemsDataModel = UpcomingItemsDataModel.fromJson(response);

                if (upcomingItemsDataModel == null) {
                    Throwable throwable = new Throwable();
                    onFailure(1, headers, throwable, response);
                    return;
                }

                setListView(upcomingItemsDataModel.getUpcomingItems());

            }

            @Override
            public void onFailure (int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e("Tracker", "Fail " + e.toString());
                Log.d("Tracker", "Status Code " + statusCode);
                Toast.makeText(upcomingItemsActivity.this, "Upcoming Items Request Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setListView(ArrayList<UpcomingItems> upcomingItems) {
        UpcomingItemsListAdapter upcomingItemsListAdapter = new UpcomingItemsListAdapter(this, upcomingItems);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(upcomingItemsListAdapter);
    }
}
