package kodoom.com.fortnitetrackernewrelease;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class fortniteStoreActivity extends AppCompatActivity {

    ListView storeListView;

    final String storeURL = "https://fortnite-public-api.theapinetwork.com/prod09/store/get";

    final String API_ID = "a38733c595bcff8adbef7fa2854b8fc5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortnite_store);

        storeListView = findViewById(R.id.storeListView);

        getCurrentStore();
    }

    private void getCurrentStore() {
        Log.d("Tracker", "getCurrentStore() function called");

        RequestParams params = new RequestParams();

        letsDoSomeNetworking(params);
    }

    private void letsDoSomeNetworking(RequestParams params) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Authorization", API_ID);
        asyncHttpClient.post(storeURL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess (int statusCode, Header[] headers, JSONObject response) {
                Log.d("Tracker", "onSuccess() function received");

                StoreDataModel storeDataModel = StoreDataModel.fromJson(response);

                if (storeDataModel == null) {
                    Throwable throwable = new Throwable();
                    onFailure(1, headers, throwable, response);
                    return;
                }

                setListView(storeDataModel.getStore());

            }

            @Override
            public void onFailure (int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e("Tracker", "Fail " + e.toString());
                Log.d("Tracker", "Status Code " + statusCode);
                Toast.makeText(fortniteStoreActivity.this, "Store Request Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setListView(ArrayList<Store> store) {
        StoreListAdapter storeListAdapter = new StoreListAdapter(this, R.layout.fortnite_store_adapter, store);
        storeListView.setAdapter(storeListAdapter);
    }

}
