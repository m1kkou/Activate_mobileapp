package school.project.activate_mobileapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

    public class Adapter extends RecyclerView.Adapter<Adapter.ActivityView> {

        // List with Activity type
        private List<BaseClasses.Activity> list;

        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class ActivityView extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView headerTextView;
            TextView descriptionTextView;
            TextView priceTextView;

            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            public ActivityView(View view) {
                super(view);

                imageView = (ImageView) view
                        .findViewById(R.id.image);
                headerTextView = (TextView) view
                        .findViewById(R.id.header);
                descriptionTextView = (TextView) view
                        .findViewById(R.id.description);
                priceTextView = (TextView) view
                        .findViewById(R.id.price);
            }
        }

        // Constructor for adapter class
        // which takes a list of String type
        public Adapter(List<BaseClasses.Activity> horizontalList) {
            this.list = horizontalList;
        }

        // Override onCreateViewHolder which deals
        // with the inflation of the card layout
        // as an item for the RecyclerView.
        @Override
        public ActivityView onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

            View itemView
                    = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item,
                            parent,
                            false);

            return new ActivityView(itemView);
        }

        // Override onBindViewHolder which deals
        // with the setting of different data
        // and methods related to clicks on
        // particular items of the RecyclerView.
        @Override
        public void onBindViewHolder(final ActivityView holder,
                                     final int position) {


            holder.imageView.setImageBitmap(getBitmapFromURL(list.get(position).getImageURL()));
            holder.headerTextView.setText(list.get(position).getName());
            holder.descriptionTextView.setText(list.get(position).getDescription());
            holder.priceTextView.setText(list.get(position).getPrice());
        }

        // Override getItemCount which Returns
        // the length of the RecyclerView.
        @Override
        public int getItemCount() {
            return list.size();
        }
        public Bitmap getBitmapFromURL(String src) {
            try {
                Log.e("src",src);
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                Log.e("Bitmap","returned");
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Exception",e.getMessage());
                return null;
            }
        }

    }


