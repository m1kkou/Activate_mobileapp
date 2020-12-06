package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class BookingView extends AppCompatActivity implements View.OnClickListener {
    public static DatabaseReference databaseReference;

    BaseClasses.Activity activity = new BaseClasses.Activity();
    TextView header;

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

            findViewById(R.id.button4).setOnClickListener(this);
            findViewById(R.id.button5).setOnClickListener(this);
            header = (TextView) findViewById(R.id.textViewHeader);
            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextGSM = (EditText) findViewById(R.id.editTextGSM);

            header.setText(activity.getName());
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
                    Intent intent = new Intent(this, OrderView.class);
                    startActivity(intent);
                }


            }
        }
}

