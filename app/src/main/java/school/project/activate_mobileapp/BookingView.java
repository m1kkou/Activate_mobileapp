package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class BookingView extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static DatabaseReference databaseReference = MainActivity.databaseReference;
    public static String TimeID;
    public static String ChosenTime;

    ArrayList<String> availableTimes = new ArrayList<>();

    BaseClasses.Activity activity = new BaseClasses.Activity();
    ArrayList<BaseClasses.Time> availableActivity = new ArrayList<>();

    TextView activityDescription;

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextGSM;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.orderview);

            Intent intent = getIntent();
            activity = SearchResults.source.get(intent.getIntExtra("activityIndex", 0));
            Log.d("BookingView intent", Integer.toString(intent.getIntExtra("activityIndex", 0)));
            String ActivityID = activity.getActivityID();
            Log.d("testi", ActivityID);
            ArrayList<BaseClasses.Time> timeslist = Services.getActivityTimes(ActivityID);
            availableTimes.clear();
            availableActivity.clear();
            for(BaseClasses.Time t : timeslist){
                if(t.isAvailable()){
                    availableTimes.add(t.getTime());
                    availableActivity.add(t);
                }
            }
            Log.d("testi", timeslist.toString());

            //availableTimes.add("testi");
            Log.d("testi", Integer.toString(timeslist.size()));

         /*   int j = 0;
            for(int i = 0; i < activity.getAvailableTimes().size(); i+=1){
                BaseClasses.Time t = activity.getAvailableTimes().get(i);
                Log.d("availableTimesArray: ", "in for loop");
                Log.d("availableTimesArray: ", t.getTime());
                if(t.isAvailable()){
                    availableTimes.add(t.getTime());
                    availableActivity.add(t);
                    Log.d("availableTimesArray: ", availableTimes.get(j).toString());
                    j += 1;
                }
            }*/


            findViewById(R.id.button4).setOnClickListener(this);
            findViewById(R.id.button5).setOnClickListener(this);
            activityDescription = (TextView) findViewById(R.id.textViewActivityDescription);
            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextGSM = (EditText) findViewById(R.id.editTextGSM);

            String descriptionText = activity.getName() + "\n\r" + activity.getDescription();

            activityDescription.setText(descriptionText);

            Spinner spin = (Spinner) findViewById(R.id.spinner);
            spin.setOnItemSelectedListener(this);

            //Creating the ArrayAdapter instance
            //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,availableTimes);
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,availableTimes);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spin.setAdapter(aa);
        }

        public void onClick(View view) {


            Button button;

            if (view instanceof Button) {
                button = (Button) view;
                if (button.getId() == R.id.button4) {
                    Intent intent = new Intent(this, SearchResults.class);
                    startActivity(intent);
                }

            }

            if (view instanceof Button) {
                button = (Button) view;
                if (button.getId() == R.id.button5) {
                    String name = editTextName.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String GSM = editTextGSM.getText().toString();



                    BaseClasses.Customer customer = new BaseClasses.Customer(name, email, GSM);
                    BaseClasses.Order order = new BaseClasses.Order(activity, customer);
                    Log.d("Order", order.activity.getName());
                    Log.d("Order", order.customer.getName());

                    customer.SaveCustomer(customer);
                    databaseReference.child("Activities").child(order.activity.getActivityID()).child("AvailableTimes").child(TimeID).child("AvailableTime").setValue(0);
                    Intent intent = new Intent(this, OrderView.class);
                    startActivity(intent);
                }
            }
        }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        ChosenTime = arg0.getItemAtPosition(position).toString();
        Log.d("testi", arg0.getItemAtPosition(position).toString());
        Log.d("testi", availableActivity.toString());
        TimeID = availableActivity.get(position).getTimeID();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

