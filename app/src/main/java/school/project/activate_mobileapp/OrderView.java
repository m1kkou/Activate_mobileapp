package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderView extends AppCompatActivity implements View.OnClickListener {
    TextView orderCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize the UI components
        setContentView(R.layout.ordercompletedview);
        orderCompleted = findViewById(R.id.textboxordercompleted);

        //Get data from BookingView class through the intent
        Intent intent = getIntent();
        String windowText = intent.getStringExtra("Order") ;

        orderCompleted.setText(windowText);

        findViewById(R.id.button6).setOnClickListener(this);
    }

    public void onClick(View view) {
        Button button;

        if (view instanceof Button) {
            button = (Button) view;
            //Return to the start layout
            if (button.getId() == R.id.button6) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        }
    }
}