package kodoom.com.fortnitetrackernewrelease;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Store> mData ;


    public StoreListAdapter(Context mContext, List<Store> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.fortnite_store_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.itemName.setText(mData.get(position).getitemName());
        loadImageFromURI(mData.get(position).getImageURI(), holder.itemImage);
        holder.itemPrice.setText(mData.get(position).getitemCost());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemPrice;
        ImageView itemImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.storeItemName);
            itemPrice = itemView.findViewById(R.id.storeItemPrice);
            itemImage = itemView.findViewById(R.id.storeItemImage);


        }
    }

    private void loadImageFromURI(String url, ImageView imageView){
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


//package kodoom.com.fortnitetrackernewrelease;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class StoreListAdapter extends ArrayAdapter<Store>{
//
//    private Context mContext;
//    int mResource;
//
//    public StoreListAdapter(Context context, int resource, ArrayList<Store> objects) {
//        super(context, resource, objects);
//        mContext = context;
//        mResource = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        String imageURI = getItem(position).getImageURI();
//        String itemName = getItem(position).getitemName();
//        String itemCost = getItem(position).getitemCost();
//
//        Store store = new Store(imageURI,itemName,itemCost);
//
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        convertView = layoutInflater.inflate(mResource,parent,false);
//
//        ImageView storeImage = convertView.findViewById(R.id.storeItemImage);
//        TextView newsHeadline = convertView.findViewById(R.id.storeItemName);
//        TextView newsDescription = convertView.findViewById(R.id.storeItemPrice);
//
//        newsHeadline.setText(itemName);
//        newsDescription.setText(itemCost);
//
//        loadImageFromURL(imageURI, storeImage);
//
//        return convertView;
//    }
//
//    public void loadImageFromURL (String url, ImageView imageView) {
//        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView, new com.squareup.picasso.Callback(){
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
//    }
//}
