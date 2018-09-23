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

public class StoreListAdapter extends ArrayAdapter<Store>{

    private Context mContext;
    int mResource;

    public StoreListAdapter(Context context, int resource, ArrayList<Store> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imageURI = getItem(position).getImageURI();
        String itemName = getItem(position).getitemName();
        String itemCost = getItem(position).getitemCost();

        Store store = new Store(imageURI,itemName,itemCost);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView storeImage = convertView.findViewById(R.id.storeItemImage);
        TextView newsHeadline = convertView.findViewById(R.id.storeItemName);
        TextView newsDescription = convertView.findViewById(R.id.storeItemPrice);

        newsHeadline.setText(itemName);
        newsDescription.setText(itemCost);

        loadImageFromURL(imageURI, storeImage);

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
