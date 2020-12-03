package com.museumtickets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MuseumActivity extends AppCompatActivity {

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        this.setTitle("Ticket Price Calculator");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int value = getIntent().getIntExtra("Position", 0);
        switchMuseum(value);

        callToast();

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

        double museumPrice = 0;
        double totalMuseumTax;
        double totalMuseumPrice;

        switch(pos){
            case 0:
                museumPrice = ((adultPrice * MagicNumbers.MMA_ADULT) + (seniorPrice * MagicNumbers.MMA_SENIOR) + (studentPrice * MagicNumbers.MMA_STUDENT));
                break;
            case 1:
                museumPrice = ((adultPrice * MagicNumbers.MOMA_ADULT) + (seniorPrice * MagicNumbers.MOMA_SENIOR) + (studentPrice * MagicNumbers.MOMA_STUDENT));
                break;
            case 2:
                museumPrice = ((adultPrice * MagicNumbers.AMN_ADULT) + (seniorPrice * MagicNumbers.AMN_SENIOR) + (studentPrice * MagicNumbers.AMN_STUDENT));
                break;
            case 3:
                museumPrice = ((adultPrice * MagicNumbers.SGM_ADULT) + (seniorPrice * MagicNumbers.SGM_SENIOR) + (studentPrice * MagicNumbers.SGM_STUDENT));
                break;
            default:
                break;
        }

        totalMuseumTax = (museumPrice * MagicNumbers.NYC_TAX);
        totalMuseumPrice = (museumPrice * MagicNumbers.NYC_TOTAL);

        ticketPrice.setText(String.format("Ticket Price: $%.2f", museumPrice));
        salesTax.setText(String.format("Sales Tax: $%.2f", totalMuseumTax));
        totalPrice.setText(String.format("Ticket Total: $%.2f", totalMuseumPrice));

    }

    private void callToast(){
        Context context = getApplicationContext();
        CharSequence text = "Max 5 tickets per category!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}