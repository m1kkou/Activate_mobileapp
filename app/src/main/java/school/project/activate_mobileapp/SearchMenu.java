package school.project.activate_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchMenu extends AppCompatActivity implements View.OnClickListener {

    ArrayList<BaseClasses.Activity> listWithAllActivities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchmenu);

        findViewById(R.id.button).setOnClickListener(this);
        //findViewById(R.id.button2).setOnClickListener(this);

        listWithAllActivities = BaseClasses.activities;
    }

    public void onClick(View view) {
        Button button;

        if (view instanceof Button) {
            button = (Button) view;
            if (button.getId() == R.id.button) {
                Intent intent = new Intent(this, SearchResults.class);
                startActivity(intent);
            }

        }
    }
    //IIRO:
    // Teetkö tämän funktion. Sen pitäisi ottaa sisään suodatus arvo esim. ActivityID
    // tai mikä tahansa merkkijono ja sitten sen pitäisi
    // palauttaa uusi lista mikä sisältää arvot, jotka vastaavat suodattimena annettua arvoa.
    // Kirjoitin alle vähän mallia millä voitaisiin toteuttaa suodatus ID:n mukaan.
    // Tämän tyyppisellä ratkaisulla voisi toteuttaa suodatuksen Aktiviteettityypin mukaan.

    //Tässä olisi siis tehtävä:
    // 1. Methodi, joka suodattaa listan AktiviteettiTyypin mukaan.
    // 2. Syntyvä lista pitäisi viedä SearchResults puolelle.
    // 3. Tarkasta, että aktiviteettityyppi saadaan aktiviteetti oliosta. (Huom! voi olla että joudut muuttamaan sen tietokantaan)
    // 4. Layoutin suodatusvaihtoehdot pitäisi antaa tuo String searchParam -merkkijono.

    public ArrayList<BaseClasses.Activity> FilterResultByActivityID(String searchParam) {
        ArrayList<BaseClasses.Activity> filteredList = new ArrayList<>();

        //Tässä esimerkkinä haku aktiviteettiID:n mukaan:
        for (BaseClasses.Activity a : listWithAllActivities) {
            if (a.getActivityID().equals(searchParam)) {
                filteredList.add(a);
            }
        }

        return filteredList;
    }

    //Tässä vähän enemmänkin pohtimista joten oleppa yhteydessä, jos tuntuu ettei joku asia ala ratkeamaan
    // -Mikko
}
