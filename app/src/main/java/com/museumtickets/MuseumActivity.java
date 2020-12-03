package com.museumtickets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
        Spinner adultSpinner = findViewById(R.id.spinner1);
        Spinner seniorSpinner = findViewById(R.id.spinner2);
        Spinner studentSpinner = findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adultSpinner.setAdapter(adapter);
        seniorSpinner.setAdapter(adapter);
        studentSpinner.setAdapter(adapter);

        final EditText ticketPrice = findViewById(R.id.editTextNumber);
        final EditText salesTax = findViewById(R.id.editTextNumber2);
        final EditText totalPrice = findViewById(R.id.editTextNumber3);

        ticketPrice.setKeyListener(null);
        salesTax.setKeyListener(null);
        totalPrice.setKeyListener(null);

    }

    int pos;

    private void switchMuseum(int position) {
        final TextView title = findViewById(R.id.ticketTitle);
        final ImageView picture = findViewById(R.id.imageView);
        final TextView adultText = findViewById(R.id.adult);
        final TextView seniorText = findViewById(R.id.senior);
        final TextView studentText = findViewById(R.id.student);


        pos = position;

        switch (position) {
            case 0:
                title.setText("The Metropolitan Museum of Art");
                picture.setImageResource(R.drawable.metropolitan);
                adultText.setText("adult $25");
                seniorText.setText("senior $17");
                studentText.setText("student $12");
                break;
            case 1:
                title.setText("The Museum of Modern Art");
                picture.setImageResource(R.drawable.modern_art);
                adultText.setText("adult $25");
                seniorText.setText("senior $18");
                studentText.setText("student $14");
                break;
            case 2:
                title.setText("American Museum of Natural History");
                picture.setImageResource(R.drawable.american);
                adultText.setText("adult $23");
                seniorText.setText("senior $18");
                studentText.setText("student $18");
                break;
            case 3:
                title.setText("Solomon R. Guggenheim Museum");
                picture.setImageResource(R.drawable.solomon);
                adultText.setText("adult $25");
                seniorText.setText("senior $18");
                studentText.setText("student $18");
                break;
            default:
                title.setText("Incoming...");
                break;
        }
    }

    public void open(View view) {
        Intent browserIntent = null;

        switch(pos){
            case 0:
                browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.metmuseum.org/"));
                break;
            case 1:
                browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moma.org/"));
                break;
            case 2:
                browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amnh.org/"));
                break;
            case 3:
                browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.guggenheim.org/"));
                break;
            default:
                break;
        }

        startActivity(browserIntent);
    }

    public void onClick(View view) {

        Spinner adultSpinner = findViewById(R.id.spinner1);
        Spinner seniorSpinner = findViewById(R.id.spinner2);
        Spinner studentSpinner = findViewById(R.id.spinner3);

        final EditText ticketPrice = findViewById(R.id.editTextNumber);
        final EditText salesTax = findViewById(R.id.editTextNumber2);
        final EditText totalPrice = findViewById(R.id.editTextNumber3);

        int adultPrice = Integer.parseInt(adultSpinner.getSelectedItem().toString());
        int seniorPrice = Integer.parseInt(seniorSpinner.getSelectedItem().toString());
        int studentPrice = Integer.parseInt(studentSpinner.getSelectedItem().toString());
        int totalTicketPrice = ((adultPrice * MagicNumbers.MOMA_ADULT) + (seniorPrice * MagicNumbers.MOMA_SENIOR) + (studentPrice * MagicNumbers.MOMA_STUDENT));
        double totalSalesTax = (totalTicketPrice * MagicNumbers.MOMA_TAX);
        double ticketTotal = (totalTicketPrice * MagicNumbers.MOMA_TOTAL);


        switch(pos){
            case 0:
                ticketPrice.setText((String.valueOf(totalTicketPrice)));
                salesTax.setText((String.valueOf(totalSalesTax)));
                totalPrice.setText((String.valueOf(ticketTotal)));
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                break;
        }

    }

}