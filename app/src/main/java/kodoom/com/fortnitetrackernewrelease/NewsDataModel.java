package kodoom.com.fortnitetrackernewrelease;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsDataModel {

    private ArrayList<String> newsHeadline = new ArrayList<>();
    private ArrayList<String> newsImageURL = new ArrayList<>();
    private ArrayList<String> newsBody = new ArrayList<>();
    private ArrayList<News> mNews = new ArrayList<>();

    public static NewsDataModel fromJson (JSONObject jsonObject) {
        try {
            NewsDataModel newsDataModel = new NewsDataModel();
            for (int i = 0;i < jsonObject.getJSONArray("entries").length(); i++) {
                newsDataModel.newsHeadline.add(jsonObject.getJSONArray("entries").getJSONObject(i).getString("title"));
                newsDataModel.newsImageURL.add(jsonObject.getJSONArray("entries").getJSONObject(i).getString("image"));
                newsDataModel.newsBody.add(jsonObject.getJSONArray("entries").getJSONObject(i).getString("body"));
                News news = new News(jsonObject.getJSONArray("entries").getJSONObject(i).getString("image"), jsonObject.getJSONArray("entries").getJSONObject(i).getString("title"), jsonObject.getJSONArray("entries").getJSONObject(i).getString("body"));
                newsDataModel.mNews.add(news);
            }
            return newsDataModel;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<String> getNewsHeadline() {
        return newsHeadline;
    }

    public ArrayList<String> getNewsImageURL() {
        return newsImageURL;
    }

    public ArrayList<String> getNewsBody() {
        return newsBody;
    }

    public ArrayList<News> getNews() {
        return mNews;
    }
}
