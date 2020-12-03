package com.museumtickets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MuseumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        this.setTitle("Ticket Price Calculator");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String value = getIntent().getStringExtra("Position");
        int position;
        int value = getIntent().getIntExtra("Position", 0);

        switchMuseum(value);

        //Instance needed for spinner values
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);
    }

    private void switchMuseum(int position) {
        final TextView title = (TextView) findViewById(R.id.ticketTitle);
        final ImageView picture = (ImageView) findViewById(R.id.imageView);

        switch (position) {
            case 0:
                title.setText("The Metropolitan Museum of Art");
                picture.setImageResource(R.drawable.metropolitan);

                break;
            case 1:
                title.setText("The Museum of Modern Art");
                picture.setImageResource(R.drawable.modern_art);
                break;
            case 2:
                title.setText("American Museum of Natural History");
                picture.setImageResource(R.drawable.american);
                break;
            case 3:
                title.setText("Solomon R. Guggenheim Museum");
                picture.setImageResource(R.drawable.solomon);
                break;
            default:
                title.setText("Incoming...");
                break;
        }
    }

    public void open(View view) {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.metmuseum.org/"));
        startActivity(browserIntent);
    }

}