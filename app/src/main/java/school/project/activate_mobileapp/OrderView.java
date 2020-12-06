package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OrderView extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordercompletedview);

        findViewById(R.id.button6).setOnClickListener(this);
    }

    public void onClick(View view) {
        Button button;

        if (view instanceof Button) {
            button = (Button) view;
            if (button.getId() == R.id.button6) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        }
    }
}