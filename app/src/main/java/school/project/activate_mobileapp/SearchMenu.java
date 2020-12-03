package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchMenu extends AppCompatActivity implements View.OnClickListener{

    CheckBox checkBox3, checkBox8, checkBox7, checkBox9, checkBox11; //liikunta3, Hyvinvointi8, Kulttuuri7, Elamys9, Muu11
    public static DataSnapshot dss = MainActivity.dataSnapshot;
    public static DatabaseReference databaseReference;
    public static DataSnapshot dataSnapshot;
    public static ArrayList<BaseClasses.Activity> activitylist = new ArrayList<>();
    //Button button;

    ArrayList<BaseClasses.Activity> listWithAllActivities;
    public static ArrayList<String> filteredlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchmenu);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        checkBox3 = findViewById(R.id.checkBox3); //liikunta
        checkBox8 = findViewById(R.id.checkBox8); //Hyvinvointi
        checkBox7 = findViewById(R.id.checkBox7); //Kulttuuri
        checkBox9 = findViewById(R.id.checkBox9); // Elamys
        checkBox11 = findViewById(R.id.checkBox11); // MUU
        findViewById(R.id.button).setOnClickListener(this);
    }

            public void onClick(View view) {
                Button button;
                filteredlist.clear();
             if (view instanceof Button) {
                button = (Button) view;
                 if (button.getId() == R.id.button){
                     Intent intent = new Intent(this, SearchResults.class);

                if (checkBox3.isChecked()) {
                    filteredlist.add("Liikunta");
                }
                if (checkBox8.isChecked()) {
                    filteredlist.add("Hyvinvointi");

                }
                if (checkBox7.isChecked()) {
                    filteredlist.add("Kulttuuri");

                }
                if (checkBox9.isChecked()) {
                    filteredlist.add("Elamys");

                }
                if (checkBox11.isChecked()) {
                    filteredlist.add("Muu");

                }
                    startActivity(intent);
                }


            }




        }
}





