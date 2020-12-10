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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class BookingView extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static DatabaseReference databaseReference = MainActivity.databaseReference;
    public static String TimeID;
    public static String ChosenTime;
    public static BaseClasses.Time time;

    ArrayList<String> timeslist = new ArrayList<>();

    BaseClasses.Activity activity = new BaseClasses.Activity();
    ArrayList<BaseClasses.Time> availableTimes = new ArrayList<>();

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
            this.timeslist.clear();
            availableTimes.clear();
            for(BaseClasses.Time t : timeslist){
                if(t.isAvailable()){
                    this.timeslist.add(t.getTime());
                    availableTimes.add(t);
                }
            }
            Log.d("testi", timeslist.toString());

            //availableTimes.add("testi");
            Log.d("testi", Integer.toString(timeslist.size()));


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
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, this.timeslist);
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
                    BaseClasses.Order order = new BaseClasses.Order(activity, customer,time);
                    Log.d("Order", order.activity.getName());
                    Log.d("Order", order.customer.getName());

                    customer.SaveCustomer(customer);
                    databaseReference.child("Activities").child(order.activity.getActivityID()).child("AvailableTimes").child(TimeID).child("AvailableTime").setValue(0);
                    String windowtext = "Kiitos varauksesta!" + "\n\r"+ "\n\r" + "Varaamasi aktiviteetti: " + order.activity.getName() + "\n\r"
                            + "Päivämäärä: " + order.time.Date + "\n\r" + "Aika: " + order.time.getTime() + "\n\r"+ "\n\r"+ "\n\r" +"Varausvahvistus on lähetetty sähköpostiisi, maksa varaus paikanpäällä. Kiitos, että asioit meidän kauttamme."+ "\n\r" + "\n\r"+ "\n\r"+"Activaten henkilökunta toivottaa nautinnollisia hetkiä aktiviteettien parissa!";
                    Intent intent = new Intent(this, OrderView.class);
                    intent.putExtra("Order", windowtext);
                    startActivity(intent);
                }
            }
        }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        ChosenTime = arg0.getItemAtPosition(position).toString();
        Log.d("testi", arg0.getItemAtPosition(position).toString());
        Log.d("testi", availableTimes.toString());
        TimeID = availableTimes.get(position).getTimeID();
        time = availableTimes.get(position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

