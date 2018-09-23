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

public class UpcomingItemsListAdapter extends ArrayAdapter<UpcomingItems> {

    private Context mContext;
    int mResource;

    public UpcomingItemsListAdapter(Context context, int resource, ArrayList<UpcomingItems> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imageURI = getItem(position).getImageURI();
        String itemName = getItem(position).getItemName();
        String itemType = getItem(position).getItemType();

        UpcomingItems upcomingItems = new UpcomingItems(imageURI,itemName,itemType);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView upcomingItemImage = convertView.findViewById(R.id.upcomingItemImage);
        TextView upcomingItemName = convertView.findViewById(R.id.upcomingItemName);
        TextView upcomingItemType = convertView.findViewById(R.id.upcomingItemsType);

        upcomingItemName.setText(itemName);
        upcomingItemType.setText(itemType);

        loadImageFromURL(imageURI, upcomingItemImage);

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

