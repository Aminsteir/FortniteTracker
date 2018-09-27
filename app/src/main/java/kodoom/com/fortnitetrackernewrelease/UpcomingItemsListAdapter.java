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

public class UpcomingItemsListAdapter extends RecyclerView.Adapter<UpcomingItemsListAdapter.MyViewHolder> {

    private Context mContext ;
    private List<UpcomingItems> mData ;


    public UpcomingItemsListAdapter(Context mContext, List<UpcomingItems> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.activity_fortnite_upcoming_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.itemName.setText(mData.get(position).getItemName());
        loadImageFromURI(mData.get(position).getImageURI(), holder.itemImage);
        holder.itemType.setText(mData.get(position).getItemType());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemType;
        ImageView itemImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.upcomingItemName);
            itemType = itemView.findViewById(R.id.upcomingItemsType);
            itemImage = itemView.findViewById(R.id.upcomingItemImage);


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
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
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
//import java.util.List;
//
//public class UpcomingItemsListAdapter extends ArrayAdapter<UpcomingItems> {
//
//    private Context mContext;
//    int mResource;
//
//    public UpcomingItemsListAdapter(Context context, int resource, ArrayList<UpcomingItems> objects) {
//        super(context, resource, objects);
//        mContext = context;
//        mResource = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        String imageURI = getItem(position).getImageURI();
//        String itemName = getItem(position).getItemName();
//        String itemType = getItem(position).getItemType();
//
//        UpcomingItems upcomingItems = new UpcomingItems(imageURI,itemName,itemType);
//
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        convertView = layoutInflater.inflate(mResource,parent,false);
//
//        ImageView upcomingItemImage = convertView.findViewById(R.id.upcomingItemImage);
//        TextView upcomingItemName = convertView.findViewById(R.id.upcomingItemName);
//        TextView upcomingItemType = convertView.findViewById(R.id.upcomingItemsType);
//
//        upcomingItemName.setText(itemName);
//        upcomingItemType.setText(itemType);
//
//        loadImageFromURL(imageURI, upcomingItemImage);
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



