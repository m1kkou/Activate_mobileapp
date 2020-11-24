package school.project.activate_mobileapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<BaseClasses.Activity> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    Adapter adapter;
    LinearLayoutManager HorizontalLayout;

    BaseClasses.Activity test_activity;

    View ChildView;
    int RecyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.searchresults);

        //TESTAUSTA VARTEN YKSI ACTIVITY OLIO:
        test_activity = new BaseClasses.Activity("Activity1", "Keilaus", "Keilaus elämys ja olutta on!\nTervetuloa tutustumaan!\n\n" ,"data:image/jpeg;base64,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", 1 , 1, 19);
        source = BaseClasses.activities;
        Log.d("test", "SearchResults.java : source(0).getName() etc:");
        Log.d("test", source.get(0).getName());
        Log.d("test", source.get(0).getDescription());
        Log.d("test", source.get(0).getImageURL());
        Log.d("test", source.get(0).getPrice());

        recyclerView
                = (RecyclerView)findViewById(
                R.id.recyclerview);
        RecyclerViewLayoutManager
                = new LinearLayoutManager(
                getApplicationContext());

        // Set LayoutManager on Recycler View
        recyclerView.setLayoutManager(
                RecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        //AddItemsToRecyclerViewArrayList();

        // calling constructor of adapter
        // with source list as a parameter
        adapter = new Adapter(source);

        // Set Horizontal Layout Manager
        // for Recycler view
        HorizontalLayout
                = new LinearLayoutManager(
                SearchResults.this,
                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);

        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList()
    {
        source = new ArrayList<BaseClasses.Activity>();
        source.add(test_activity);
    }
}

