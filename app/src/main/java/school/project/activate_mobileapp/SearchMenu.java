package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchMenu extends AppCompatActivity implements View.OnClickListener{

    //UI components
    CheckBox checkBox3, checkBox8, checkBox7, checkBox9, checkBox11; //liikunta3, Hyvinvointi8, Kulttuuri7, Elamys9, Muu11
    EditText editTextDate;

    //Static props so can be reached within other classes
    public static DatabaseReference databaseReference;
    public static ArrayList<String> filteredList = new ArrayList<>();
    public static String SelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.searchmenu);
        //get database instance
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //Initiate UI components
        checkBox3 = findViewById(R.id.checkBox3); //liikunta
        checkBox8 = findViewById(R.id.checkBox8); //Hyvinvointi
        checkBox7 = findViewById(R.id.checkBox7); //Kulttuuri
        checkBox9 = findViewById(R.id.checkBox9); // Elamys
        checkBox11 = findViewById(R.id.checkBox11); // MUU
        editTextDate = findViewById(R.id.editTextDate);
        findViewById(R.id.button).setOnClickListener(this);
    }

            public void onClick(View view) {
                Button button;
                filteredList.clear();
                SelectedDate = editTextDate.getText().toString();
                if (view instanceof Button) {
                    button = (Button) view;

                if (button.getId() == R.id.button){
                     Intent intent = new Intent(this, SearchResults.class);

                //Collect chosen filters from if blocks
                if (checkBox3.isChecked()) {
                    filteredList.add("Liikunta");
                }
                if (checkBox8.isChecked()) {
                    filteredList.add("Hyvinvointi");
                }
                if (checkBox7.isChecked()) {
                    filteredList.add("Kulttuuri");
                }
                if (checkBox9.isChecked()) {
                    filteredList.add("Elamys");
                }
                if (checkBox11.isChecked()) {
                    filteredList.add("Muu");
                }
                startActivity(intent);
                }
            }
        }
}





