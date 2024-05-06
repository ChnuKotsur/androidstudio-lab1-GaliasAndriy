package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReceiveMessageActivity extends Activity {
    public class AnthemAndRegion {
        private String anthem;
        private String region;

        public AnthemAndRegion(String anthem, String region) {
            this.anthem = anthem;
            this.region = region;
        }

        public String getAnthem() {
            return anthem;
        }

        public String getRegion() {
            return region;
        }
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        Intent intent = getIntent();
        String country = intent.getStringExtra("country");

        AnthemAndRegion anthemAndRegion = getAnthemByCountry(country);
        String anthem = anthemAndRegion.getAnthem();
        String region = anthemAndRegion.getRegion();

        TextView messageView = findViewById(R.id.message);
        messageView.setText(anthem);

        TextView regionTextView = findViewById(R.id.region_text);
        regionTextView.setText(region);

        LinearLayout layout = findViewById(R.id.receive_layout);
        String formattedCountryName = country.toLowerCase().replaceAll("\\s+", ""); // Remove whitespace from country name
        int flagResource = getResources().getIdentifier("flag_" + formattedCountryName, "drawable", getPackageName());
        layout.setBackgroundResource(flagResource);
    }

    private AnthemAndRegion getAnthemByCountry(String country){
        switch (country){
            case "Mongolia":
                return new AnthemAndRegion(getString(R.string.anthem_mongolia), getString(R.string.region_mongolia));
            case "Colombia":
                return new AnthemAndRegion(getString(R.string.anthem_colombia), getString(R.string.region_colombia));
            case "Canada":
                return new AnthemAndRegion(getString(R.string.anthem_canada), getString(R.string.region_canada));
            case "United Kingdom":
                return new AnthemAndRegion(getString(R.string.anthem_uk), getString(R.string.region_united_kingdom));
            default:
                return new AnthemAndRegion("", "");
        }
    }
}