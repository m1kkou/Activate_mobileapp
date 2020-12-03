package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;


public class BookingView extends AppCompatActivity implements View.OnClickListener {
    public static DatabaseReference databaseReference;

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextGSM;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bookingview);

            findViewById(R.id.button4).setOnClickListener(this);
            findViewById(R.id.button5).setOnClickListener(this);
            editTextName = (EditText) findViewById(R.id.editTextName);
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextGSM = (EditText) findViewById(R.id.editTextGSM);
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
                    String name = editTextName.toString();
                    String email = editTextEmail.toString();
                    String GSM = editTextGSM.toString();
                    String customerID = "1234";
                    BaseClasses.Customer c = new BaseClasses.Customer(name, email, GSM, customerID);
                    Log.d("Customer", c.toString());

                    c.SaveCustomer(c);
                    Intent intent = new Intent(this, OrderView.class);
                    startActivity(intent);
                }


            }
        }
}

