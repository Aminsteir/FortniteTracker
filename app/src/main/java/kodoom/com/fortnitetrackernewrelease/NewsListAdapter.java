package kodoom.com.fortnitetrackernewrelease;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsListAdapter extends ArrayAdapter<News>{

    private Context mContext;
    int mResource;

    public NewsListAdapter(Context context, int resource, ArrayList<News> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imageURI = getItem(position).getImageURI();
        String headline = getItem(position).getHeadline();
        String description = getItem(position).getDescription();

        News news = new News(imageURI,headline,description);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView newsImage = convertView.findViewById(R.id.newsImage);
        TextView newsHeadline = convertView.findViewById(R.id.newsHeadline);
        TextView newsDescription = convertView.findViewById(R.id.newsDescription);

        newsHeadline.setText(headline);
        newsDescription.setText(description);

        loadImageFromURL(imageURI, newsImage);

        return convertView;
    }

    public void loadImageFromURL (String url, ImageView imageView) {
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView, new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
