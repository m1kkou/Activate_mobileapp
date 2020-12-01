package school.project.activate_mobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static DatabaseReference databaseReference;
    public static DataSnapshot dataSnapshot;
    public static ArrayList<BaseClasses.Activity> activitylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.button10).setOnClickListener(this);
    }

    public void onClick(View view) {
        Button button;

        if (view instanceof Button) {
            button = (Button) view;
            if (button.getId() == R.id.button10) {
                for (int i = 0; i < activitylist.size(); i += 1) {
                    BaseClasses.Activity a = new BaseClasses.Activity();
                    a = activitylist.get(i);
                    String aDesc = a.getDescription();
                    String aPrice = a.getPrice();
                    String aName = a.getName();
                    String activity0 = aName + " " + aDesc + " " + aPrice + ",00";
                    Log.d("test", activity0);
                }
                Intent intent = new Intent(this, SearchMenu.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot = snapshot;
                getData(dataSnapshot);
                Log.d("test", "database saved in snapshot");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getData(DataSnapshot datasnapshot){
        Log.d("test", "getData called");
        activitylist.clear();
        DataSnapshot activitysnapshot = datasnapshot.child("Activities");
        int Index = 0;
        for(DataSnapshot ds : activitysnapshot.getChildren()){
            activitylist.add(ds.getValue(BaseClasses.Activity.class));
            activitylist.get(Index).setActivityID(ds.getKey());
            Index++;
        }
        Log.d("test", activitylist.get(0).getActivityID());
        String activity0 = activitylist.get(0).getName() + " " + activitylist.get(0).getDescription();
        Log.d("test", String.valueOf(activitylist.size()));
        Log.d("test", activity0);
    }
}

