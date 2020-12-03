package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<BaseClasses.Activity> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    Adapter adapter;
    LinearLayoutManager HorizontalLayout;


    int RecyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.searchresults);

        source = Services.getFilteredActivities(SearchMenu.filteredlist);

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
        findViewById(R.id.button2).setOnClickListener(this);
    }

    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList(ArrayList<BaseClasses.Activity> filteredList)
    {
        source = filteredList;

        //source.add(test_activity);
    }
    @Override
    public void onClick(View view) {
        Button button;
        Intent intent = new Intent(this, BookingView.class);
        if (view instanceof Button) {
            button = (Button) view;
            if (button.getId() == R.id.button2) {
                startActivity(intent);
            }
        }
    }

}



