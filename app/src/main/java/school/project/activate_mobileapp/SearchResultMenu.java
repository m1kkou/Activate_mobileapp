package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchResultMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresultmenu);

        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    public void onClick(View view) {
        Button button;

        if (view instanceof Button) {
            button = (Button) view;
            if (button.getId() == R.id.button3) {
                Intent intent = new Intent(this, SearchMenu.class);
                startActivity(intent);
            }
            if (view instanceof Button) {
                button = (Button) view;
                if (button.getId() == R.id.button2) {
                    Intent intent = new Intent(this, OrderView.class);
                    startActivity(intent);
                }

            }
        }
    }
}
