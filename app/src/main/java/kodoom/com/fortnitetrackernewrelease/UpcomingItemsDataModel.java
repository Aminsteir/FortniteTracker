package kodoom.com.fortnitetrackernewrelease;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpcomingItemsDataModel {

    private ArrayList<String> upcomingItemNames = new ArrayList<>();
    private ArrayList<String> upcomingItemURIS = new ArrayList<>();
    private ArrayList<String> upcomingItemTypes = new ArrayList<>();
    private ArrayList<UpcomingItems> mUpcomingItems = new ArrayList<>();

    public static UpcomingItemsDataModel fromJson (JSONObject jsonObject) {
        try {
            UpcomingItemsDataModel upcomingItemsDataModel = new UpcomingItemsDataModel();
            for (int i = 0;i < jsonObject.getJSONArray("items").length(); i++) {
                upcomingItemsDataModel.upcomingItemNames.add(jsonObject.getJSONArray("items").getJSONObject(i).getString("name"));
                upcomingItemsDataModel.upcomingItemURIS.add(jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("item").getJSONObject("images").getString("background"));
                upcomingItemsDataModel.upcomingItemTypes.add(jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("item").getString("type"));
                UpcomingItems upcomingItems = new UpcomingItems(jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("item").getJSONObject("images").getString("background"), jsonObject.getJSONArray("items").getJSONObject(i).getString("name"), "Type: " + jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("item").getString("type"));
                upcomingItemsDataModel.mUpcomingItems.add(upcomingItems);
            }
            return upcomingItemsDataModel;
        } catch (JSONException e) {
            Log.e("Tracker", "Error retrieving data");
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<String> getUpcomingItemNames() {
        return upcomingItemNames;
    }

    public ArrayList<String> getUpcomingItemURIS() {
        return upcomingItemURIS;
    }

    public ArrayList<String> getUpcomingItemTypes() {
        return upcomingItemTypes;
    }

    public ArrayList<UpcomingItems> getUpcomingItems() {
        return mUpcomingItems;
    }

}
