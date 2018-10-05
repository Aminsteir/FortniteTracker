package kodoom.com.fortnitetrackernewrelease;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        final String imageURI = getItem(position).getImageURI();
        final String headline = getItem(position).getHeadline();
        final String description = getItem(position).getDescription();

        News news = new News(imageURI,headline,description);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        final ImageView newsImage = convertView.findViewById(R.id.newsImage);
        final TextView newsHeadline = convertView.findViewById(R.id.newsHeadline);
        TextView newsDescription = convertView.findViewById(R.id.newsDescription);

        final Dialog mDialog = new Dialog(mContext);

        newsHeadline.setText(headline);
        newsDescription.setText(description);

        loadImageFromURL(imageURI, newsImage);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView exitPopup;
                TextView newsHeadlinePopup;
                ImageView newsImageViewPopup;
                TextView newsDescriptionPopup;
                mDialog.setContentView(R.layout.fortnite_main_custom_popup);
                exitPopup = mDialog.findViewById(R.id.exitText);
                newsHeadlinePopup = mDialog.findViewById(R.id.newsHeadlinePopup);
                newsImageViewPopup = mDialog.findViewById(R.id.imageHeadlinePopup);
                newsDescriptionPopup = mDialog.findViewById(R.id.newsDescriptionPopup);

                exitPopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                });

                newsHeadlinePopup.setText(headline);

                loadImageFromURL(imageURI, newsImageViewPopup);

                newsDescriptionPopup.setText(description);

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();
            }
        });

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
