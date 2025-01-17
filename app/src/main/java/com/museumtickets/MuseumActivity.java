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

import java.util.Locale;
import java.util.Objects;

/**
 * This class is essential to perform the methods used for the MuseumActivity.
 * @author David Testa & Gabriel Marques-Zacarias
 */
public class MuseumActivity extends AppCompatActivity {

    int pos;

    /**
     * This method is essential as it is the first method called when the app begins.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        this.setTitle(getString(R.string.museumTitle));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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


        EditText ticketPrice = findViewById(R.id.editTextNumber);
        EditText salesTax = findViewById(R.id.editTextNumber2);
        EditText totalPrice = findViewById(R.id.editTextNumber3);

        ticketPrice.setKeyListener(null);
        salesTax.setKeyListener(null);
        totalPrice.setKeyListener(null);

    }

    /**
     * This method allows the different museums to switch between images as well as the text titles.
     */
    private void switchMuseum(int position) {
        final TextView title = findViewById(R.id.ticketTitle);
        final ImageView picture = findViewById(R.id.imageView);
        final TextView adultText = findViewById(R.id.adult);
        final TextView seniorText = findViewById(R.id.senior);
        final TextView studentText = findViewById(R.id.student);

        pos = position;

        TextView textView = new TextView(this);

        switch (position) {
            case 0:
                title.setText(getString(R.string.museum1));
                picture.setImageResource(R.drawable.metropolitan);
                adultText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.adultPrice),  MagicNumbers.MMA_ADULT));
                seniorText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.seniorPrice), MagicNumbers.MMA_SENIOR));
                studentText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.studentPrice), MagicNumbers.MMA_STUDENT));
                break;
            case 1:
                title.setText(getString(R.string.museum2));
                picture.setImageResource(R.drawable.modern_art);
                adultText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.adultPrice),  MagicNumbers.MOMA_ADULT));
                seniorText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.seniorPrice), MagicNumbers.MOMA_SENIOR));
                studentText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.studentPrice), MagicNumbers.MOMA_STUDENT));
                break;
            case 2:
                title.setText(getString(R.string.museum3));
                picture.setImageResource(R.drawable.american);
                adultText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.adultPrice),  MagicNumbers.AMN_ADULT));
                seniorText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.seniorPrice), MagicNumbers.AMN_SENIOR));
                studentText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.studentPrice), MagicNumbers.AMN_STUDENT));
                break;
            case 3:
                title.setText(getString(R.string.museum4));
                picture.setImageResource(R.drawable.solomon);
                adultText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.adultPrice),  MagicNumbers.SGM_ADULT));
                seniorText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.seniorPrice), MagicNumbers.SGM_SENIOR));
                studentText.setText(String.format(Locale.ENGLISH,"%s %.0f", getString(R.string.studentPrice), MagicNumbers.SGM_STUDENT));
                break;
            default:
                break;
        }
    }

    /**
     * This method is an action on selection of the image to open the appropriate URL for each museum.
     */
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

    /**
     * This method allows the user to calculate the costs of each ticket price for each museum.
     */
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

        ticketPrice.setText(String.format(Locale.ENGLISH, "%s %.2f", getString(R.string.ticketPriceMsg), museumPrice));
        salesTax.setText(String.format(Locale.ENGLISH, "%s %.2f",getString(R.string.taxPriceMsg), totalMuseumTax));
        totalPrice.setText(String.format(Locale.ENGLISH, "%s %.2f", getString(R.string.totalPriceMsg), totalMuseumPrice));
    }

    /**
     * This method is required to display the toast message to the user on each second activity screen.
     */
    private void callToast(){
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.toaster);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}