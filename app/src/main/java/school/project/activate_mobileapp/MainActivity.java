package school.project.activate_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference testiDatabase;
    TextView eka;
    DataSnapshot snapshot1;
    Button btn1;
    ArrayList<Aktiviteetti> aktiviteettilista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testiDatabase = FirebaseDatabase.getInstance().getReference();
        eka = findViewById(R.id.tekstiboksi);
        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tiedot", "klikattu!");
                getData(snapshot1);
                String tuloste = "";
                for (Aktiviteetti a : aktiviteettilista){
                    tuloste = tuloste + a.tiedot.Nimi+ " " + a.tiedot.puhnro + "\n";
                }
                eka.setText(tuloste);
            }
        });
    }

   @Override
    protected void onStart() {
        super.onStart();
        Log.d("tiedot", "onstart");
        testiDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("tiedot", "toimiiko?");
                snapshot1 = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("tiedot", "database error!");
            }
        });
    }

    private void getData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Log.d("tiedot", ds.getKey());
            String avain = ds.getKey();
            aktiviteettilista.add(ds.getValue(Aktiviteetti.class));
            Log.d("tiedot_varaus", ds.child("varaus").getValue().toString());
            Log.d("tiedot_tyyppi", ds.child("aktiviteetin_tyyppi").getValue().toString());
            Log.d("tiedot_yhteyst", ds.child("tiedot").getValue().toString());
        }
    }
}
