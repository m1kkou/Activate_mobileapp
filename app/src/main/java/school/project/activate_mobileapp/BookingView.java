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
    //Static props from other classes:
    public static DatabaseReference databaseReference = MainActivity.databaseReference;
    public static String TimeID;
    public static String ChosenTime;
    public static BaseClasses.Time time;

    //Different lists:
    ArrayList<String> strTimesList = new ArrayList<>();
    BaseClasses.Activity activity = new BaseClasses.Activity();
    ArrayList<BaseClasses.Time> availableTimes = new ArrayList<>();

    //UI components:
    TextView activityDescription;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextGSM;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.orderview);

            //Getting data from the SearchResults -class:
            Intent intent = getIntent();
            //Index value of the chosen activity
            activity = SearchResults.source.get(intent.getIntExtra("activityIndex", 0));
            String ActivityID = activity.getActivityID();

            //Services method used to return the chosen Activity objects available times:
            ArrayList<BaseClasses.Time> oTimesList = Services.getActivityTimes(ActivityID);
            this.strTimesList.clear();
            availableTimes.clear();

            for(BaseClasses.Time t : oTimesList){
                if(t.isAvailable()){
                    this.strTimesList.add(t.getTime());
                    availableTimes.add(t);
                }
            }

            //Set UI components:
            findViewById(R.id.button4).setOnClickListener(this);
            findViewById(R.id.button5).setOnClickListener(this);
            activityDescription = (TextView) findViewById(R.id.textViewActivityDescription);
            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextGSM = (EditText) findViewById(R.id.editTextGSM);

            String descriptionText = activity.getName() + "\n\r" + activity.getDescription();
            activityDescription.setText(descriptionText);

            //Spinner object to show available time intervals:
            Spinner spin = (Spinner) findViewById(R.id.spinner);
            spin.setOnItemSelectedListener(this);

            //Array adapter to hold the time values shown in the spinner
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, this.strTimesList);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spin.setAdapter(aa);
        }

        public void onClick(View view) {


            Button button;
            //Return to search results
            if (view instanceof Button) {
                button = (Button) view;
                if (button.getId() == R.id.button4) {
                    Intent intent = new Intent(this, SearchResults.class);
                    startActivity(intent);
                }

            }
            //Complete the order
            if (view instanceof Button) {
                button = (Button) view;
                if (button.getId() == R.id.button5) {
                    String name = editTextName.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String GSM = editTextGSM.getText().toString();

                    //Initiate new customer object
                    BaseClasses.Customer customer = new BaseClasses.Customer(name, email, GSM);
                    //Initiate new order object that holds Activity, Customer and Time -objects
                    BaseClasses.Order order = new BaseClasses.Order(activity, customer,time);
                    //Save customer info:
                    customer.SaveCustomer(customer);

                    //Set availabletime value to 0 in db so the chosen time
                    // is not shown in the search results after it is ordered
                    databaseReference.child("Activities").child(order.activity.getActivityID()).child("AvailableTimes").child(TimeID).child("AvailableTime").setValue(0);

                    //Message shown in the ordercompletedview
                    String windowtext =
                            "Kiitos varauksesta!" + "\n\r"+ "\n\r" +
                            "Varaamasi aktiviteetti: " + order.activity.getName() + "\n\r"
                            + "Päivämäärä: " + order.time.Date + "\n\r" + "Aika: " + order.time.getTime()
                            + "\n\r"+ "\n\r"+ "\n\r" +"Varausvahvistus on lähetetty sähköpostiisi, maksa " +
                            "varaus paikanpäällä. Kiitos, että asioit meidän kauttamme."+ "\n\r" + "\n\r"+ "\n\r"
                            +"Activaten henkilökunta toivottaa nautinnollisia hetkiä aktiviteettien parissa!";

                    //Intent to ordercompletedview/OrderView class
                    Intent intent = new Intent(this, OrderView.class);
                    //Set the windowtext as an extra for exporting it to the next class
                    intent.putExtra("Order", windowtext);
                    startActivity(intent);
                }
            }
        }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //SAVE the data chosen from the Spinner
        ChosenTime = arg0.getItemAtPosition(position).toString();
        TimeID = availableTimes.get(position).getTimeID();
        time = availableTimes.get(position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        //IF nothing is selected take the first element
        ChosenTime = arg0.getItemAtPosition(0).toString();
        TimeID = availableTimes.get(0).getTimeID();
        time = availableTimes.get(0);
    }
}

