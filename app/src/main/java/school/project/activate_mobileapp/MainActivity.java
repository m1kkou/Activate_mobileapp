package school.project.activate_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.IccOpenLogicalChannelResponse;
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
    //databasereferenceä tarvitaan, jotta voidaan lukea tai kirjoittaa firebaseen
    TextView eka;
    DataSnapshot snapshot1;
    //data firebasesta saadaan aina datasnapshot-objektina
    Button btn1;
    CheckBox checkBox;
    String tuloste = "";
    ArrayList<Activity> aktiviteettilista = new ArrayList<>();
    // aktiviteettilistan tallennetaan Aktiviteetti-objekteja

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
                for (Activity a : aktiviteettilista){
                            // Tässä voitaisiin myös poistaa halutut aktiviteetit, esim aktiviteettilista.remove(a);
                            // ja filtteröidystä aktiviteettilistasta ottaa aktiviteetti-objektit ja näyttää ne halutulla tavalla näytöllä.
                        tuloste = tuloste + a.getName() + " " + a.getDescription() +"\n";
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
        DataSnapshot aktiviteetit = dataSnapshot.child("Activities");
        for(DataSnapshot ds : aktiviteetit.getChildren()){
            aktiviteettilista.add(ds.getValue(Activity.class));
        }
    }
}
