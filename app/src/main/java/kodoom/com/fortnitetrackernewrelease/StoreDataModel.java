package kodoom.com.fortnitetrackernewrelease;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoreDataModel {

    private ArrayList<String> storeItemNames = new ArrayList<>();
    private ArrayList<String> storeItemImageURLS = new ArrayList<>();
    private ArrayList<String> storeItemCosts = new ArrayList<>();
    private ArrayList<Store> mStore = new ArrayList<>();

    public static StoreDataModel fromJson (JSONObject jsonObject) {
        try {
            StoreDataModel storeDataModel = new StoreDataModel();
            for (int i = 0;i < jsonObject.getJSONArray("items").length(); i++) {
                storeDataModel.storeItemNames.add(jsonObject.getJSONArray("items").getJSONObject(i).getString("name"));
                storeDataModel.storeItemImageURLS.add(jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("item").getJSONObject("images").getString("background"));
                storeDataModel.storeItemCosts.add(jsonObject.getJSONArray("items").getJSONObject(i).getString("cost"));
                Store store = new Store(jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("item").getJSONObject("images").getString("background"), jsonObject.getJSONArray("items").getJSONObject(i).getString("name"), "Cost: " + jsonObject.getJSONArray("items").getJSONObject(i).getString("cost"));
                storeDataModel.mStore.add(store);
            }
            return storeDataModel;
        } catch (JSONException e) {
            Log.e("Tracker", "Error retrieving data");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<String> getStoreItemNames() {
        return storeItemNames;
    }

    public ArrayList<String> getStoreItemImageURLS() {
        return storeItemImageURLS;
    }

    public ArrayList<String> getStoreItemCosts() {
        return storeItemCosts;
    }

    public ArrayList<Store> getStore() {
        return mStore;
    }

}
