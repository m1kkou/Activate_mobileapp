package school.project.activate_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox checkBox;
    String tuloste = "";
    ArrayList<Aktiviteetti> aktiviteettilista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testiDatabase = FirebaseDatabase.getInstance().getReference();
        //muuttuja, johon tallennetaan paikallinen instanssiviite tietokantaan
        eka = findViewById(R.id.tekstiboksi);
        btn1 = findViewById(R.id.button1);
        checkBox = findViewById(R.id.checkBox1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tiedot", "klikattu");
                getData(snapshot1);
                //getData-metodi ottaa onStartissa tehdyn snapshotin, ja tallentaa sieltä aktiviteetit luokkina (aktiviteettilistaan)
                for (Aktiviteetti a : aktiviteettilista){
                    if(checkBox.isChecked()){
                        if(a.varaus == 0){
                            tuloste = tuloste + a.tiedot.Nimi + " " + a.tiedot.puhnro + "\n";
                        }
                    }
                    else {
                        tuloste = tuloste + a.tiedot.Nimi + " " + a.tiedot.puhnro + "\n";
                    }
                }
                eka.setText(tuloste);
                tuloste = "";
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
                // snapshot kopioidaan paikalliseen muuttujaan, jota voidaan käyttää getData-metodissa myöhemmin
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("tiedot", "database error!");
            }
        });
    }

    private void getData(DataSnapshot dataSnapshot){
        aktiviteettilista.clear();
        // lista tyhjäksi ettei joka painikkeen klikkauksella tule kopio koko tietokannasta
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Log.d("tiedot", ds.getKey());
            String avain = ds.getKey();
            aktiviteettilista.add(ds.getValue(Aktiviteetti.class));
            // Lisätään jokainen aktiviteetti listaan Aktiviteetti-luokkana, log alla virheiden löytämiseksi
            Log.d("tiedot_varaus", ds.child("varaus").getValue().toString());
            Log.d("tiedot_tyyppi", ds.child("aktiviteetin_tyyppi").getValue().toString());
            Log.d("tiedot_yhteyst", ds.child("tiedot").getValue().toString());
        }
    }
}
