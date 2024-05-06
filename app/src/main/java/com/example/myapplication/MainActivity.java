package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner countrySpinner;
    TextView anthemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countrySpinner = findViewById(R.id.country_spinner);
        anthemText = findViewById(R.id.anthem_textview);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = parent.getItemAtPosition(position).toString();
                displayAnthem(selectedCountry);
                changeBackground(selectedCountry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayAnthem(String country) {
        int anthemResId = getResources().getIdentifier(country.toLowerCase() + "_anthem", "string", getPackageName());
        int regionResId = getResources().getIdentifier("region_" + country.toLowerCase(), "string", getPackageName());

        if(anthemResId != 0 && regionResId != 0) {
            anthemText.setText(getString(anthemResId));
            String region = getString(regionResId);
            TextView regionTextView = findViewById(R.id.region_text);
            regionTextView.setText(region);
        } else {
            anthemText.setText("");
        }
    }

    private void changeBackground(String country) {
        int flagResId = getResources().getIdentifier("flag_" + country.toLowerCase(), "drawable", getPackageName());
        if(flagResId != 0) {
            findViewById(android.R.id.content).setBackgroundResource(flagResId);
        } else {
            findViewById(android.R.id.content).setBackgroundResource(android.R.color.white);
        }
    }
}